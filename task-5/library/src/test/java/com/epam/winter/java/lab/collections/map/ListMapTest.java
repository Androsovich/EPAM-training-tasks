package com.epam.winter.java.lab.collections.map;

public class ListMapTest extends AbstractMapTest {

    @Override
    public void setUp() {
        setStringMap(new ListMap<>());
        setTestUserMap(new ListMap<>());
    }
}