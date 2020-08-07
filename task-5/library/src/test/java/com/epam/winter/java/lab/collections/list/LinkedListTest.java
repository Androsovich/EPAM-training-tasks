package com.epam.winter.java.lab.collections.list;

import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;

import static org.junit.Assert.*;

public class LinkedListTest extends AbstractListTest {

    @Before
    public void setUp() {
        Comparator<String> comparator = Comparator.reverseOrder();
        setComparator(comparator);
        setStringList(new LinkedList<>());
        setIntegerList(new LinkedList<>());
        setStringsWithComparator(new LinkedList<>(comparator));
    }

    @Test
    public void add() {
        List<String> stringList = new LinkedList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        stringList.add("E");
        assertEquals(5, stringList.size());
    }

    @Test
    public void remove() {
        List<String> stringList = new LinkedList<>();
        stringList.add("AA");
        stringList.add("CC");
        stringList.add("BB");
        stringList.remove(1);
        assertNotEquals(3, stringList.size());
        assertEquals("CC", stringList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testTrimAllNullElement() {
        List<String> stringList = new LinkedList<>();
        stringList.add(null);
        stringList.add(null);
        stringList.add(null);
        stringList.add(null);
        assertEquals(4, stringList.size());
        stringList.trim();
        assertEquals(0, stringList.size());
        stringList.get(0);
    }
}