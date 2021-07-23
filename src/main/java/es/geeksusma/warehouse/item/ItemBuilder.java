package es.geeksusma.warehouse.item;

public class ItemBuilder {
    private Long id;
    private String serialNumber;
    private String name;
    private String description;
    private Integer stock;

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public ItemBuilder id(Long id) {
        this.id = id;
        return this;
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
        return new Item(id, this.serialNumber, this.name, this.description, this.stock);
    }
}
