package com.epam.winter.java.lab.collections.queue;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractQueueTest {
    private Queue<String> stringQueue;
    private Queue<Integer> integerQueue;


    public Queue<String> getStringQueue() {
        return stringQueue;
    }

    void setStringQueue(Queue<String> stringQueue) {
        this.stringQueue = stringQueue;
    }

    void setIntegerQueue(Queue<Integer> integerQueue) {
        this.integerQueue = integerQueue;
    }

    @Before
    public abstract void setUp();


    @After
    public void clearDate() {
        stringQueue.clear();
        integerQueue.clear();
    }


    @Test
    public void testIteratorRemove() {
        integerQueue.push(11);
        integerQueue.push(54);
        integerQueue.push(32);
        integerQueue.push(21);
        CollectionIterator<Integer> iter = integerQueue.getIterator();
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

            System.out.println(integer);
        }
        assertTrue(integerQueue.isEmpty());
    }

    @Test
    public void testIteratorAddBeforeAndAddAfter() {
        Integer[] actual = {10, 23, 24, 15, 25, 10, 26};
        integerQueue.clear();
        integerQueue.push(23);
        integerQueue.push(24);
        integerQueue.push(25);
        integerQueue.push(26);

        CollectionIterator<Integer> iterator = integerQueue.getIterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.getNext();
            if (integer == 23) {
                iterator.addBefore(10);
            }
            if (integer == 26) {
                iterator.addBefore(10);
            }
            if (integer == 24) {
                iterator.addAfter(15);
            }
        }

        for (Integer integer : actual) {
            assertEquals(integer, integerQueue.poll());
        }

    }

    @Test
    public void isEmpty() {
        assertEquals(0, stringQueue.size());
        assertTrue(stringQueue.isEmpty());
    }

    @Test
    public void peek() {
        stringQueue.push("A");
        stringQueue.push("B");
        stringQueue.push("C");
        assertEquals(3, stringQueue.size());
        assertEquals("A", stringQueue.peek());
        assertEquals("A", stringQueue.peek());
        assertEquals("A", stringQueue.peek());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeekResultNoSuchElementException() {
        stringQueue.peek();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPollResultNoSuchElementException() {
        stringQueue.push("ABC");
        stringQueue.push("ABC");
        stringQueue.push("ABC");
        stringQueue.poll();
        stringQueue.poll();
        stringQueue.poll();
        stringQueue.poll();
    }

    @Test
    public void poll() {
        stringQueue.push("AB");
        stringQueue.push("BC");
        stringQueue.push("qwerty");
        assertEquals("AB", stringQueue.poll());
        assertEquals("BC", stringQueue.poll());
        assertEquals(1, stringQueue.size());
    }

    @Test
    public void pull() {
        integerQueue.push(1);
        integerQueue.push(2);
        integerQueue.push(3);
        assertEquals(3, integerQueue.size());
        assertEquals(3, (int) integerQueue.pull());
        assertEquals(3, (int) integerQueue.pull());
        assertEquals(3, (int) integerQueue.pull());

    }

    @Test
    public void remove() {
        stringQueue.push("AB");
        stringQueue.push("BC");
        stringQueue.push("qwerty");
        stringQueue.push("qwerty");
        stringQueue.push("qwerty");
        assertEquals(5, stringQueue.size());
        assertEquals("qwerty", stringQueue.remove());
        assertEquals(4, stringQueue.size());
        assertEquals("qwerty", stringQueue.remove());
        assertEquals(3, stringQueue.size());
        assertEquals("qwerty", stringQueue.remove());
        assertEquals(2, stringQueue.size());
        assertEquals("BC", stringQueue.remove());
        assertEquals(1, stringQueue.size());
        assertEquals("AB", stringQueue.remove());
        assertTrue(stringQueue.isEmpty());
    }

    @Test
    public void pushAllArray() {
        Integer[] actual = {1, 3, 7, 8, -9};
        integerQueue.pushAll(actual);
        assertEquals(-9, (int) integerQueue.remove());
        assertEquals(8, (int) integerQueue.remove());
        assertEquals(7, (int) integerQueue.remove());
        assertEquals(3, (int) integerQueue.remove());
        assertEquals(1, (int) integerQueue.remove());
        assertTrue(stringQueue.isEmpty());
    }

    @Test
    public void pushAllCollection() {
        List<Integer> integers = Arrays.asList(2, 5, 89, 100);
        integerQueue.pushAll(integers);
        for (Integer integer : integers) {
            assertEquals(integer, integerQueue.poll());
        }
    }

    @Test
    public void search() {
        List<Integer> integers = Arrays.asList(2, 5, 89, 100);
        integerQueue.pushAll(integers);
        assertEquals(2, integerQueue.search(89));
        assertEquals(0, integerQueue.search(2));
        assertEquals(3, integerQueue.search(100));
        assertEquals(-1, integerQueue.search(1034));
    }

    @Test
    public void testClear() {
        List<Integer> integers = Arrays.asList(2, 5, 89, 100);
        integerQueue.pushAll(integers);
        integerQueue.clear();
        assertTrue(integerQueue.isEmpty());
    }

    @Test
    public void testSize() {
        List<Integer> integers = Arrays.asList(2, 5, 89, 100);
        integerQueue.pushAll(integers);
        assertEquals(4, integerQueue.size());
    }

}