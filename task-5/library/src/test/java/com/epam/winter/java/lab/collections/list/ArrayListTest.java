package com.epam.winter.java.lab.collections.list;

import org.junit.*;

import java.util.Comparator;

public class ArrayListTest extends AbstractListTest {

    @Before
    public void setUp() {
        Comparator<String> comparator = Comparator.reverseOrder();
        setComparator(comparator);
        setStringList(new ArrayList<>());
        setIntegerList(new ArrayList<>());
        setStringsWithComparator(new ArrayList<>(comparator));
    }



}