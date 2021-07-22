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

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CreateItemControllerShould {

    @MockBean
    private CreateItem component;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void returnBadRequest_when_noSerialNumber() throws Exception {
        this.mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new NewItemRequest("", "test", "desc", 123))))
                .andDo(print()).andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new NewItemRequest(null, "test", "desc", 123))))
                .andDo(print()).andExpect(status().isBadRequest());

        //.andExpect(content().string(containsString("Serial number is mandatory")));
        then(component).shouldHaveNoInteractions();
    }

    @Test
    void returnBadRequest_when_noNameNumber() throws Exception {
        this.mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new NewItemRequest("123", "", "desc", 123))))
                .andDo(print()).andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new NewItemRequest("123", null, "desc", 123))))
                .andDo(print()).andExpect(status().isBadRequest());

        //.andExpect(content().string(containsString("Serial number is mandatory")));
        then(component).shouldHaveNoInteractions();
    }


    @Test
    void returnCreated_when_itemIsCreated() throws Exception {
        this.mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new NewItemRequest("123", "name", "desc", 123))))
                .andDo(print()).andExpect(status().isCreated());

        then(component).should().create(Item.ItemBuilder.builder()
                .serialNumber("123")
                .name("name")
                .description("desc")
                .stock(123)
                .build());
    }
}