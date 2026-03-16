package es.geeksusma.warehouse.api.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class NewItemRequest {

    @NotBlank(message = "Serial number is mandatory")
    private String serial;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    private Integer stock;

    public NewItemRequest() {
    }

    public NewItemRequest(String serial, String name, String description, Integer stock) {
        this.serial = serial;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }
}
