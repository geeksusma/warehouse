package es.geeksusma.warehouse.item;

interface ItemExistsRepository {
    boolean isSerialNumberInUse(final String serialNumber);
}
