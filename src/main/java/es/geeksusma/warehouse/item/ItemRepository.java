package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.data.ItemEntity;
import es.geeksusma.warehouse.data.ItemsDataSource;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


class ItemRepository implements ItemExistsRepository, SaveItemRepository, GetItemRepository {
    private final ItemsDataSource itemsDataSource;

    ItemRepository(ItemsDataSource itemsDataSource) {
        this.itemsDataSource = itemsDataSource;
    }

    @Override
    public boolean isSerialNumberInUse(String serialNumber) {
        Set<String> serialNumbersInUse = itemsDataSource.getAll().stream().map(i -> i.serialNumber).collect(Collectors.toSet());
        return serialNumbersInUse.contains(serialNumber);
    }

    @Override
    public Long save(Item newItem) {
        Long nextId = itemsDataSource.getNextId();
        this.itemsDataSource.persist(new ItemEntity(
                nextId,
                newItem.serialNumber,
                newItem.name,
                newItem.description,
                newItem.stock
        ));
        return nextId;
    }

    @Override
    public Optional<Item> getById(Long id) {

        return this.itemsDataSource.getAll().stream().filter(i -> i.id.equals(id)).findFirst().map(item -> Item.ItemBuilder.builder()
                .serialNumber(item.serialNumber)
                .name(item.name)
                .description(item.description)
                .stock(item.stock)
                .build());
    }
}
