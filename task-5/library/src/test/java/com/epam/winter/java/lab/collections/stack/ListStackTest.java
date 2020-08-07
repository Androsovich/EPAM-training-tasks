package com.epam.winter.java.lab.collections.stack;

public class ListStackTest   extends AbstractStackTest {

        public void setUp() {
        setStringStack(new ListStack<>());
        setIntegerStack(new ListStack<>());
    }
}