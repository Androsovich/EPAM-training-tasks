package com.epam.winter.java.lab.collections.stack;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStackTest {
    private Stack<String> stringStack;
    private Stack<Integer> integerStack;

    public void setStringStack(Stack<String> stringStack) {
        this.stringStack = stringStack;
    }

    public void setIntegerStack(Stack<Integer> integerStack) {
        this.integerStack = integerStack;
    }

    @Before
    public abstract void setUp() ;

    @After
    public void clearDate() {
        stringStack.clear();
        integerStack.clear();
    }

    @Test
    public void isEmpty() {
        assertEquals(0, stringStack.size());
        assertTrue(stringStack.isEmpty());
    }

    @Test
    public void testPeek() {
        stringStack.push("1");
        stringStack.push("2");
        stringStack.push("3");
        stringStack.push("4");
        assertEquals(4, stringStack.size());
        assertEquals("4", stringStack.peek());
        assertEquals("4", stringStack.peek());
        assertEquals("4", stringStack.peek());

    }

    @Test
    public void testPop() {
        stringStack.push("1");
        stringStack.push("2");
        stringStack.push("3");
        stringStack.push("4");
        assertEquals("stringStack.size() = " + stringStack.size(), 4, stringStack.size());
        assertEquals("4", stringStack.pop());
        assertEquals("3", stringStack.pop());
        assertEquals("2", stringStack.pop());
        assertEquals("1", stringStack.pop());
        assertTrue(stringStack.isEmpty());
    }

    @Test
    public void testPush() {
        stringStack.push("1");
        assertEquals(1, stringStack.size());
        assertEquals("1", stringStack.peek());
        assertEquals("1", stringStack.pop());
        assertEquals(0, stringStack.size());
        stringStack.push("1");
        stringStack.push("1");
        assertEquals(1, stringStack.size());
        stringStack.push("ABC");
        assertEquals(2, stringStack.size());
        assertEquals("ABC", stringStack.peek());
    }

    @Test
    public void testPushAllCollection() {
        List<Integer> integers = Arrays.asList(2, 4, 5, 1);
        for (Integer integer : integers) {
            integerStack.push(integer);
        }
        Collections.reverse(integers);
        for (Integer integer : integers) {
            assertEquals(integer, integerStack.pop());
        }
    }

    @Test
    public void testPushAllArray() {
        Integer[] actual = {2, 40, 50, 20};
        for (int i = actual.length - 1; i >= 0; i--) {
            integerStack.push(actual[i]);
        }
        assertEquals(actual.length, integerStack.size());
        for (int i = 0; i < actual.length; i++) {
            integerStack.push(actual[i]);
        }
        for (Integer integer : actual) {
            assertEquals(integer, integerStack.pop());
        }

    }

    @Test
    public void testSearch() {
        integerStack.push(1);
        integerStack.push(20);
        integerStack.push(10);
        integerStack.push(15);
        assertEquals(1, integerStack.search(20));
        assertEquals(0, integerStack.search(1));
        integerStack.sort(Integer::compareTo);
        assertEquals(1, integerStack.search(10));
        assertEquals(3, integerStack.search(20));
    }

    @Test
    public void testClear() {
        stringStack.push("1");
        stringStack.push("2");
        stringStack.push("3");
        stringStack.push("4");
        stringStack.clear();
        assertEquals(0, stringStack.size());
    }

    @Test
    public void testSize() {
        integerStack.push(1);
        integerStack.push(20);
        integerStack.push(10);
        assertEquals(3, integerStack.size());
        integerStack.pop();
        assertEquals(2, integerStack.size());
    }

    @Test
    public void testSort() {
        Integer[] actual = {1, 10, 15, 20};
        integerStack.push(1);
        integerStack.push(20);
        integerStack.push(10);
        integerStack.push(15);
        integerStack.sort(Integer::compareTo);
        for (int i = actual.length - 1; i >= 0; i--) {
            int test = integerStack.pop();
            assertEquals((int) actual[i], test);
        }
    }

    @Test
    public void testIteratorRemove() {
        integerStack.push(11);
        integerStack.push(54);
        integerStack.push(32);
        integerStack.push(21);
        CollectionIterator<Integer> iter =  integerStack.getIterator();
        while (iter.hasNext()) {
            Integer integer = iter.getNext();
            if (integer == 21)
                iter.remove();
            if (integer == 32)
                iter.remove();
            if (integer == 54)
                iter.remove();
            if (integer == 11)
                iter.remove();
        }
        assertTrue( integerStack.isEmpty());
    }

    @Test
    public void testIteratorAddBeforeAndAddAfter() {
        Integer[] actual = {26, 10, 25, 15, 24, 23};
        integerStack.clear();
        integerStack.push(23);
        integerStack.push(24);
        integerStack.push(25);
        integerStack.push(26);

        CollectionIterator<Integer> iterator = integerStack.getIterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.getNext();

            if (integer == 26) {
                iterator.addBefore(10);
            }
            if (integer == 24) {
                iterator.addAfter(15);
            }
        }
        for (Integer testValue : actual) {
           assertEquals(testValue, integerStack.pop());
        }

    }

}