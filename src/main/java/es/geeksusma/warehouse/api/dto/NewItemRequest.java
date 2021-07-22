package es.geeksusma.warehouse.api.dto;

import javax.validation.constraints.NotBlank;

public class NewItemRequest {

    @NotBlank(message = "The serial number is mandatory")
    private String serialNumber;
    @NotBlank(message = "The item is mandatory")
    private String name;
    private String description;
    private Integer stock;

    public NewItemRequest() {
    }

    public NewItemRequest(String serialNumber, String name, String description, Integer stock) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
        this.stock = stock;
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
