package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.NewItemRequest;
import es.geeksusma.warehouse.core.Mapper;
import es.geeksusma.warehouse.item.CreateItem;
import es.geeksusma.warehouse.item.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final CreateItem createItem;
    private final Mapper<Item, NewItemRequest> itemNewItemRequestMapper;

    CreateItemController(CreateItem createItem, Mapper<Item, NewItemRequest> itemNewItemRequestMapper) {
        this.createItem = createItem;
        this.itemNewItemRequestMapper = itemNewItemRequestMapper;
    }

    @Operation(summary = "Creates a new Item in the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item was created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "No enough mandatory fields given or serial number taken",
                    content = @Content)})
    @PostMapping
    ResponseEntity<Long> createItem(@Valid @RequestBody NewItemRequest request) {

        Long newItemId = createItem.create(itemNewItemRequestMapper.map(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newItemId);
    }
}
