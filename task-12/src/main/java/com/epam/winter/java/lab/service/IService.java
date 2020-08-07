package com.epam.winter.java.lab.service;

import java.util.List;

public interface IService<T> {
    T getById(Integer id);

    int save(T instance);

    void update(T instance);

    void delete(T instance);

    List<T> getAll();
}
