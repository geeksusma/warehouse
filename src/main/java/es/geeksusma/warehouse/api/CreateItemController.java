package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.NewItemRequest;
import es.geeksusma.warehouse.item.CreateItem;
import es.geeksusma.warehouse.item.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
class CreateItemController {

    private CreateItem createItem;

    CreateItemController(CreateItem createItem) {
        this.createItem = createItem;
    }

    @PostMapping
    ResponseEntity<Void> createItem(@Valid @RequestBody NewItemRequest request) {

        createItem.create(Item.ItemBuilder.builder().serialNumber(request.getSerialNumber())
                .description(request.getDescription()).name(request.getName()).stock(request.getStock()).build());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
