package com.epam.winter.java.lab.collections.map;

public class ArrayMapTest extends AbstractMapTest{

    @Override
    public void setUp() {
        setStringMap(new ArrayMap<>());
        setTestUserMap(new ArrayMap<>());
    }
}