package es.geeksusma.warehouse.data;

import java.util.Objects;

public class ItemEntity {

    public final Long id;
    public final String serialNumber;
    public final String name;
    public final String description;
    public final Integer stock;

    public ItemEntity(Long id, String serialNumber, String name, String description, Integer stock) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
        this.stock = stock;
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

