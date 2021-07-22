package es.geeksusma.warehouse.data;

public class ItemEntity {

    private final Long id;
    private final String serialNumber;
    private final String name;
    private final String description;

    public ItemEntity(Long id, String serialNumber, String name, String description) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
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
}

