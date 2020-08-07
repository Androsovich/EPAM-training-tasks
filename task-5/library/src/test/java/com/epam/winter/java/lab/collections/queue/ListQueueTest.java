package com.epam.winter.java.lab.collections.queue;

import org.junit.Before;

public class ListQueueTest extends AbstractQueueTest {

    @Before
    public void setUp() {
        setStringQueue(new ListQueue<>());
        setIntegerQueue(new ListQueue<>());
    }

}