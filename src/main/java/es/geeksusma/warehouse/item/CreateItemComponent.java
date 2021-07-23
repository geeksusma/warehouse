package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.core.Validator;

import java.util.List;

class CreateItemComponent implements CreateItem {
    private final List<Validator<Item>> validators;
    private final SaveItemRepository saveItemRepository;

    public CreateItemComponent(final List<Validator<Item>> validators, SaveItemRepository saveItemRepository) {

        this.validators = validators;
        this.saveItemRepository = saveItemRepository;
    }

    @Override
    public Long create(Item newItem) {
        assertNewItem(newItem);
        validators.forEach(v -> v.validate(newItem));
        return saveItemRepository.save(newItem);
    }

    private void assertNewItem(Item newItem) {
        if (newItem == null || newItem.isEmpty()) {
            throw new IllegalArgumentException("Item can't be null or empty");
        }
    }
}
