package es.geeksusma.warehouse.item;


import es.geeksusma.warehouse.data.ItemEntity;
import es.geeksusma.warehouse.data.ItemsDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryShould {

    private static final String EXISTING_SERIAL_NUMBER = "123";
    private static final String FREE_SERIAL_NUMBER = "2345";
    private static final String NEW_ITEM_NAME = "NEW ITEM";
    private static final String NEW_ITEM_DESCRIPTION = "New Item Desc";
    private static final int NEW_ITEM_STOCK = 123;
    private ItemRepository itemRepository;
    private ItemsDataSource itemsDataSource;

    @BeforeEach
    void setUp() {

        itemsDataSource = new ItemsDataSource(new HashMap<>());
        itemsDataSource.persist(new ItemEntity(1L, EXISTING_SERIAL_NUMBER, "test", "test", 1));
        itemRepository = new ItemRepository(itemsDataSource);
    }

    @Test
    void returnTrue_when_serialNumberExists() {

        assertThat(itemRepository.isSerialNumberInUse(EXISTING_SERIAL_NUMBER)).isTrue();
    }

    @Test
    void returnFalse_when_serialNumberIsFree() {

        assertThat(itemRepository.isSerialNumberInUse(EXISTING_SERIAL_NUMBER + "test")).isFalse();
    }

    @Test
    void saveNewItem_when_persist() {

        Long newId = itemRepository.save(setUpNewItem());

        assertThat(this.itemsDataSource.getAll().size()).isEqualTo(2);
        assertThat(this.itemsDataSource.getAll().get(1)).isEqualTo(new ItemEntity(newId, FREE_SERIAL_NUMBER, NEW_ITEM_NAME, NEW_ITEM_DESCRIPTION, NEW_ITEM_STOCK));
    }

    private Item setUpNewItem() {
        return Item.ItemBuilder.builder()
                .serialNumber(FREE_SERIAL_NUMBER)
                .name(NEW_ITEM_NAME)
                .description(NEW_ITEM_DESCRIPTION)
                .stock(NEW_ITEM_STOCK)
                .build();

    }
}