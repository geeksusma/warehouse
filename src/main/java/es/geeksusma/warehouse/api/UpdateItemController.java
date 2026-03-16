package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.ItemDTO;
import es.geeksusma.warehouse.item.UpdateItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class UpdateItemController {

    private final UpdateItem updateItem;

    public UpdateItemController(UpdateItem updateItem) {
        this.updateItem = updateItem;
    }

    @Operation(summary = "Updates an existing Item in the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item was created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "No enough mandatory fields given or serial number taken",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tried to update a non existing item",
                    content = @Content)})
    @PutMapping("/{id}")
    ResponseEntity<Long> update(@PathVariable("id") Long id, @Valid @RequestBody ItemDTO toUpdate) {

        Long newItemId = updateItem.byId(id, toUpdate);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newItemId);
    }
}
