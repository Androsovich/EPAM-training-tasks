package com.epam.winter.java.lab.collections.map.entity;

public interface IEntity<K, V> {
    K getKey();

    V getValue();

    int getHash();
}
