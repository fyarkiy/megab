package com.orchard.repository;

import java.util.List;

public interface GenericRepository<T> {
    T add (T item);

    List<T> getAll();

    T getById(Long id);

    boolean remove(T item);

    T update(T item);
}
