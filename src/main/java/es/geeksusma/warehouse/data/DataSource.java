package es.geeksusma.warehouse.data;

import java.util.List;

public interface DataSource<T, Y> {

    Y getNextId();

    void persist(T element);

    List<T> getAll();

}
