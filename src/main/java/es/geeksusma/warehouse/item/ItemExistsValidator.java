package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.core.Validator;

class ItemExistsValidator implements Validator<Item> {

    private final ItemExistsRepository itemExistsRepository;

    public ItemExistsValidator(final ItemExistsRepository itemExistsRepository) {

        this.itemExistsRepository = itemExistsRepository;
    }

    @Override
    public void validate(Item element) {
        String serialNumber = element.serialNumber;
        if (itemExistsRepository.isSerialNumberInUse(serialNumber)) {
            throw new SerialNumberDuplicityException(String.format("The serial number %s is already taken", serialNumber));
        }

    }
}
