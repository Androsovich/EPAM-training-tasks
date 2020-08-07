package com.epam.winter.java.lab.collections.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayQueueTest extends AbstractQueueTest {

    @Before
    public void setUp() {
        setStringQueue(new ArrayQueue<>());
        setIntegerQueue(new ArrayQueue<>());
    }

    @Test
    public void testPushDefaultCapacity() {
        Queue<String> stringQueue =  getStringQueue();
        stringQueue.push("AB");
        stringQueue.push("BC");
        stringQueue.push("qwerty");
        stringQueue.push("1");
        stringQueue.push("2");
        stringQueue.push("3");
        stringQueue.push("4");
        stringQueue.push("5");
        stringQueue.push("6");
        stringQueue.push("7");
        stringQueue.push("8");
        stringQueue.push("9");
        assertEquals(10, stringQueue.size());
        assertEquals("7", stringQueue.remove());
        assertEquals(9, stringQueue.size());
        assertEquals("6", stringQueue.pull());
    }
}