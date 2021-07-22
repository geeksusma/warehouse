package es.geeksusma.warehouse.item;

public interface ItemExistsRepository {
    boolean isSerialNumberInUse(final String serialNumber);
}
