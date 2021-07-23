package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.data.ItemEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemItemEntityMapperShould {

    private static final String SERIAL_NUMBER = "123";
    private static final String NAME = "Wheels";
    private static final String DESCRIPTION = "round and round";
    private static final int STOCK = 1231232;
    public static final long ID = 1234234L;

    @Test
    void returnExpected_when_map() {
        final ItemItemEntityMapper itemEntityMapper = new ItemItemEntityMapper();

        final Item item = itemEntityMapper.map(setUpEntity());

        assertThat(item).usingRecursiveComparison().isEqualTo(setUpExpectedItem());

    }

    private Item setUpExpectedItem() {
        return Item.ItemBuilder.builder()
                .id(ID)
                .serialNumber(SERIAL_NUMBER)
                .name(NAME)
                .description(DESCRIPTION)
                .stock(STOCK)
                .build();
    }

    private ItemEntity setUpEntity() {
        return new ItemEntity(ID, SERIAL_NUMBER, NAME, DESCRIPTION, STOCK);
    }
}