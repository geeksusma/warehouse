package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.NewItemRequest;
import es.geeksusma.warehouse.core.Mapper;
import es.geeksusma.warehouse.item.Item;
import es.geeksusma.warehouse.item.ItemBuilder;
import org.springframework.stereotype.Component;

@Component
public class ItemNewItemRequestMapper implements Mapper<Item, NewItemRequest> {
    @Override
    public Item map(NewItemRequest request) {
        return ItemBuilder.builder().serialNumber(request.getSerialNumber())
                .description(request.getDescription()).name(request.getName()).stock(request.getStock()).build();
    }
}
