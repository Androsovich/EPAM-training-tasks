package com.epam.winter_java_lab.dao.json;

import java.util.List;

public interface JsonDao<T> {

    void save(T t);

    void save(String json);

    T parse();

    void clean();

    public <V> void addListToJsonArray(String jsonArrayName, List<V> values, Class<V> clazz);

}
