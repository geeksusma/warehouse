package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.ItemDTO;
import es.geeksusma.warehouse.core.Mapper;
import es.geeksusma.warehouse.item.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDTOItemMapper implements Mapper<ItemDTO, Item> {
    @Override
    public ItemDTO map(Item item) {
        return new ItemDTO(item.getId(), item.getSerialNumber(), item.getName(), item.getDescription(), item.getStock());
    }
}
