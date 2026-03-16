package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.api.dto.ItemDTO;

interface UpdateItemRepository {

    void save(Long id, ItemDTO toUpdate);
}
