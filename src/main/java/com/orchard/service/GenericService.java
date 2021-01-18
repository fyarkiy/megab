package com.orchard.service;

import java.util.List;

public interface GenericService <T> {
    T add(T item);

    List<T> getAll();

    T getById(Long id);

    boolean remove(Long id);

    T update(Long id, T item);
}
