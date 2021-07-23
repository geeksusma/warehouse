package es.geeksusma.warehouse.api;

import es.geeksusma.warehouse.api.dto.ItemDTO;
import es.geeksusma.warehouse.core.Mapper;
import es.geeksusma.warehouse.item.GetSingleItem;
import es.geeksusma.warehouse.item.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/items")
class GetItemController {

    private final GetSingleItem getSingleItem;
    private final Mapper<ItemDTO, Item> itemDTOItemMapper;

    GetItemController(GetSingleItem getSingleItem, Mapper<ItemDTO, Item> itemDTOItemMapper) {
        this.getSingleItem = getSingleItem;
        this.itemDTOItemMapper = itemDTOItemMapper;
    }

    @Operation(summary = "Retrieves an Item if exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item was found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {

        return ResponseEntity.ok(itemDTOItemMapper.map(getSingleItem.byId(id)));
    }
}
