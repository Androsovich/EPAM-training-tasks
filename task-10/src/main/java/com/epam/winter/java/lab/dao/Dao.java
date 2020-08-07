package com.epam.winter.java.lab.dao;

import java.util.List;

public interface Dao<T> {
    T getById(Integer id);

    void save(T instance);

    void update(T instance);

    void delete(T instance);

    List<T> getAll();

}
