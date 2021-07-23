package es.geeksusma.warehouse.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.geeksusma.warehouse.api.dto.ItemDTO;
import es.geeksusma.warehouse.item.GetSingleItem;
import es.geeksusma.warehouse.item.Item;
import es.geeksusma.warehouse.item.ItemNotFound;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GetItemControllerShould {

    private static final String SERIAL_NUMBER = "123";
    private static final String NAME = "toy-box";
    private static final String DESCRIPTION = "for 2 years old kids";
    private static final int STOCK = 321;
    @MockBean
    private GetSingleItem component;

    private MockMvc mockMvc;

    @Autowired
    private GetItemController controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ApiErrorHandler())
                .build();
    }

    @Test
    void returnNotFound_when_itemNotFound() throws Exception {
        Long nonExistingId = 2132213L;
        given(component.byId(nonExistingId)).willThrow(new ItemNotFound("test"));

        doGet(nonExistingId).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void returnItem_when_itemFound() throws Exception {
        Long existingId = 2132213L;
        setUpExistingItem(existingId);

        String responseAsString = doGet(existingId).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


        assertThat(objectMapper.readValue(responseAsString, ItemDTO.class)).usingRecursiveComparison().isEqualTo(setUpExpectedItem(existingId));
    }

    private ItemDTO setUpExpectedItem(Long existingId) {
        return new ItemDTO(existingId, SERIAL_NUMBER, NAME, DESCRIPTION, STOCK);
    }

    private void setUpExistingItem(Long existingId) {
        Item item = Item.ItemBuilder.builder().id(existingId).serialNumber(SERIAL_NUMBER).name(NAME).description(DESCRIPTION).stock(STOCK).build();
        given(component.byId(existingId)).willReturn(item);
    }

    ResultActions doGet(Long id) throws Exception {
        return this.mockMvc.perform(get("/items/" + id)
                .contentType(MediaType.APPLICATION_JSON));
    }

}