package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.core.Mapper;
import es.geeksusma.warehouse.data.ItemEntity;

public class ItemItemEntityMapper implements Mapper<Item, ItemEntity> {
    @Override
    public Item map(ItemEntity source) {
        return ItemBuilder.builder()
                .id(source.id)
                .serialNumber(source.serialNumber)
                .name(source.name)
                .description(source.description)
                .stock(source.stock)
                .build();
    }
}
