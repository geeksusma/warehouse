package es.geeksusma.warehouse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemDTO {
    private Long id;
    private String serialNumber;
    private String name;
    private String description;
    private Integer stock;


}
