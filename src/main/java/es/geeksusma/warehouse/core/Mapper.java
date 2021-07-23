package es.geeksusma.warehouse.core;

public interface Mapper<T, S> {

    T map(S source);
}
