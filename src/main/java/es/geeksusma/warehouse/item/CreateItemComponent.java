package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.core.Validator;

import java.util.List;

class CreateItemComponent implements CreateItem {
    private final List<Validator<Item>> itemExistsValidator;
    private final SaveItemRepository saveItemRepository;

    public CreateItemComponent(final List<Validator<Item>> itemExistsValidator, SaveItemRepository saveItemRepository) {

        this.itemExistsValidator = itemExistsValidator;
        this.saveItemRepository = saveItemRepository;
    }

    @Override
    public Long create(Item newItem) {
        if (newItem == null || newItem.isEmpty()) {
            throw new IllegalArgumentException("Item can't be null or empty");
        }
        itemExistsValidator.forEach(v -> v.validate(newItem));
        return saveItemRepository.save(newItem);
    }
}
