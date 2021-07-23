package es.geeksusma.warehouse.data;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemsDataSource implements DataSource<ItemEntity, Long> {

    private Map<Long, ItemEntity> items;

    public ItemsDataSource() {
        items = new HashMap<>();
    }

    public ItemsDataSource(Map<Long, ItemEntity> items) {
        this.items = items;
    }

    @PostConstruct
    void initialize() {
        items = new HashMap<>();
    }

    @Override
    public Long getNextId() {
        return (long) items.size() + 1;
    }

    @Override
    public void persist(ItemEntity element) {
        items.put(element.id, element);
    }

    @Override
    public List<ItemEntity> getAll() {
        return new LinkedList<>(this.items.values());
    }
}
