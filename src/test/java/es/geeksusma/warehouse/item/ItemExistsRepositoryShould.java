package es.geeksusma.warehouse.item;


import es.geeksusma.warehouse.data.ItemEntity;
import es.geeksusma.warehouse.data.ItemsDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class ItemExistsRepositoryShould {

    public static final String EXISTING_SERIAL_NUMBER = "123";
    private ItemExistsRepository itemExistsRepository;

    @BeforeEach
    void setUp() {
        ItemsDataSource itemsDataSource = new ItemsDataSource(new HashMap<>());
        itemsDataSource.persist(new ItemEntity(1L, EXISTING_SERIAL_NUMBER, "test", "test"));
        itemExistsRepository = new ItemExistRepositoryImpl(itemsDataSource);
    }

    @Test
    void returnTrue_when_serialNumberExists() {

        assertThat(itemExistsRepository.isSerialNumberInUse(EXISTING_SERIAL_NUMBER)).isTrue();
    }

    @Test
    void returnFalse_when_serialNumberIsFree() {

        assertThat(itemExistsRepository.isSerialNumberInUse(EXISTING_SERIAL_NUMBER + "test")).isFalse();
    }
}