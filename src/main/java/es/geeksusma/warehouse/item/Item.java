package es.geeksusma.warehouse.item;

import java.util.Objects;

public class Item {
    final String serialNumber;
    final String name;
    final String description;
    final Integer stock;

    public Item(String serialNumber, String name, String description, Integer stock) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    boolean isEmpty() {
        return this.serialNumber == null || "".equals(this.serialNumber);
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

    public Integer getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return serialNumber.equals(item.serialNumber) && name.equals(item.name) && Objects.equals(description, item.description) && Objects.equals(stock, item.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, name, description, stock);
    }

    public static class ItemBuilder {
        private String serialNumber;
        private String name;
        private String description;
        private Integer stock;

        public static ItemBuilder builder() {
            return new ItemBuilder();
        }

        public ItemBuilder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public ItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        public Item build() {
            return new Item(this.serialNumber, this.name, this.description, this.stock);
        }
    }
}
