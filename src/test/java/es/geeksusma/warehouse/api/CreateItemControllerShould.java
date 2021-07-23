package es.geeksusma.warehouse.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.geeksusma.warehouse.api.dto.NewItemRequest;
import es.geeksusma.warehouse.item.CreateItem;
import es.geeksusma.warehouse.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CreateItemControllerShould {

    @MockBean
    private CreateItem component;

    private MockMvc mockMvc;

    @Autowired
    private CreateItemController controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ApiErrorHandler())
                .build();
    }

    @Test
    void returnBadRequest_when_noSerialNumber() throws Exception {

        checkPostForBadRequest(new NewItemRequest(null, "test", "desc", 123), "Serial number is mandatory");

        checkPostForBadRequest(new NewItemRequest("", "test", "desc", 123), "Serial number is mandatory");

        then(component).shouldHaveNoInteractions();
    }


    @Test
    void returnBadRequest_when_noNameNumber() throws Exception {
        checkPostForBadRequest(new NewItemRequest("123", null, "desc", 123), "Name is mandatory");

        checkPostForBadRequest(new NewItemRequest("123", "", "desc", 123), "Name is mandatory");

        then(component).shouldHaveNoInteractions();
    }

    @Test
    void returnErrors_when_moreThanOneErrorFound() throws Exception {
        final String jsonResponse = this.doPost(new NewItemRequest("", null, "desc", 123))
                .andDo(print()).andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();


        assertThat(jsonResponse).contains("Name is mandatory", "Serial number is mandatory");

        then(component).shouldHaveNoInteractions();
    }


    @Test
    void returnCreated_when_itemIsCreated() throws Exception {
        this.doPost(new NewItemRequest("123", "name", "desc", 123))
                .andDo(print()).andExpect(status().isCreated());

        then(component).should().create(Item.ItemBuilder.builder()
                .serialNumber("123")
                .name("name")
                .description("desc")
                .stock(123)
                .build());
    }


    ResultActions doPost(NewItemRequest request) throws Exception {
        return this.mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    private void checkPostForBadRequest(final NewItemRequest request, final String expectedError) throws Exception {
        this.doPost(request)
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(expectedError)));
    }
}