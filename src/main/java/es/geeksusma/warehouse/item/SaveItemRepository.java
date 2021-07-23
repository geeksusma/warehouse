package es.geeksusma.warehouse.item;

interface SaveItemRepository {

    Long save(Item newItem);
}
