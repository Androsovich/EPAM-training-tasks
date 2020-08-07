package com.epam.winter.java.lab.collections.stack;

public class ArrayStackTest extends AbstractStackTest {

    @Override
    public void setUp() {
        setIntegerStack(new ArrayStack<>());
        setStringStack(new ArrayStack<>());
    }
}