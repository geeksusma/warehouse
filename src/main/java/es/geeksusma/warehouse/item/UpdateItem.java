package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.api.dto.ItemDTO;

public interface UpdateItem {
    Long byId(Long id, ItemDTO toUpdate);
}
