package es.geeksusma.warehouse.api.dto;

public class ItemDTO {
    private Long id;
    private String serialNumber;
    private String name;
    private String description;
    private Integer stock;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String serialNumber, String name, String description, Integer stock) {
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

    public Integer getStock() {
        return stock;
    }
}
