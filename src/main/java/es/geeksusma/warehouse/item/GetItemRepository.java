package es.geeksusma.warehouse.item;

import java.util.Optional;

public interface GetItemRepository {

    Optional<Item> getById(final Long id);
}
