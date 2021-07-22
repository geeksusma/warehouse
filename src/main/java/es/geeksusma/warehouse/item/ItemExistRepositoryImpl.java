package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.data.ItemsDataSource;

import java.util.Set;
import java.util.stream.Collectors;


class ItemExistRepositoryImpl implements ItemExistsRepository {
    private final ItemsDataSource itemsDataSource;

    ItemExistRepositoryImpl(ItemsDataSource itemsDataSource) {
        this.itemsDataSource = itemsDataSource;
    }

    @Override
    public boolean isSerialNumberInUse(String serialNumber) {
        Set<String> serialNumbersInUse = itemsDataSource.getAll().stream().map(i -> i.getSerialNumber()).collect(Collectors.toSet());
        return serialNumbersInUse.contains(serialNumber);
    }
}
