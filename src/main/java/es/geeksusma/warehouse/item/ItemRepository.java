package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.data.ItemEntity;
import es.geeksusma.warehouse.data.ItemsDataSource;

import java.util.Set;
import java.util.stream.Collectors;


class ItemRepository implements ItemExistsRepository, SaveItemRepository {
    private final ItemsDataSource itemsDataSource;

    ItemRepository(ItemsDataSource itemsDataSource) {
        this.itemsDataSource = itemsDataSource;
    }

    @Override
    public boolean isSerialNumberInUse(String serialNumber) {
        Set<String> serialNumbersInUse = itemsDataSource.getAll().stream().map(ItemEntity::getSerialNumber).collect(Collectors.toSet());
        return serialNumbersInUse.contains(serialNumber);
    }

    @Override
    public void save(Item newItem) {
        this.itemsDataSource.persist(new ItemEntity(
                itemsDataSource.getNextId(),
                newItem.serialNumber,
                newItem.name,
                newItem.description,
                newItem.stock
        ));
    }
}
