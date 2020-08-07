package com.epam.winter.java.lab.dao;

import java.util.List;

public interface IDao<T> {

        T getById(Integer id);

        int save(T instance);

        void update(T instance);

        void delete(T instance);

        List<T> getAll();
}
