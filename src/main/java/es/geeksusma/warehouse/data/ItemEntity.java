package es.geeksusma.warehouse.data;

import java.util.Objects;

public class ItemEntity {

    private final Long id;
    private final String serialNumber;
    private final String name;
    private final String description;
    private final Integer stock;

    public ItemEntity(Long id, String serialNumber, String name, String description, Integer stock) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id.equals(that.id) && serialNumber.equals(that.serialNumber) && name.equals(that.name) && description.equals(that.description) && stock.equals(that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, name, description, stock);
    }
}

