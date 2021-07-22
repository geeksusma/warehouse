package es.geeksusma.warehouse.core;

public interface Validator<T> {

    void validate(T element);
}
