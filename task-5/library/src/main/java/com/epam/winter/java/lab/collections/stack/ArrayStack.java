package com.epam.winter.java.lab.collections.stack;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.*;
import java.util.stream.IntStream;

public class ArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayStack(int defaultCapacity) {
        elementData = new Object[defaultCapacity];
    }

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public CollectionIterator<E> getIterator() {
        return new CollectionIterator<E>() {
            private int prevIndex = size;

            @Override
            public boolean hasNext() {
                return prevIndex > 0;
            }

            @Override
            public void remove() {
                rangeCheck();
                System.arraycopy(elementData, prevIndex + 1, elementData, prevIndex, elementData.length - prevIndex - 1);
                size--;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E getNext() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return (E) elementData[--prevIndex];
            }

            @Override
            public void set(E element) {
                if (isElementInsert(element))
                    elementData[prevIndex] = element;
            }

            @Override
            public void addBefore(E element) {
                if (isElementInsert(element)) {
                    System.arraycopy(elementData, prevIndex, elementData, prevIndex + 1, size - prevIndex);
                    elementData[prevIndex] = element;
                    prevIndex++;
                    size++;
                }
            }

            @Override
            public void addAfter(E element) {
                if (isElementInsert(element)) {
                    final int indexInsert = prevIndex + 1;
                    System.arraycopy(elementData, indexInsert, elementData, indexInsert + 1, size - indexInsert);
                    elementData[indexInsert] = element;
                    size++;
                }
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        return isEmpty() ? null : (E) elementData[size - 1];
    }

    private void rangeCheck() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private boolean isEnsureCapacity() {
        return size < elementData.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        E result = isEmpty() ? null : (E) elementData[size - 1];
        if (Objects.nonNull(result)) {
            elementData[--size] = null;
        }
        return result;
    }

    @Override
    public void push(E element) {
        if (isElementInsert(element)) {
            elementData[size++] = element;
        }
    }

    private boolean isUnique(E element) {
        return !Arrays.asList(elementData).contains(element) && Objects.nonNull(element);
    }

    private boolean isElementInsert(E element) {
        return isUnique(element) && isEnsureCapacity();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void pushAll(Collection<E> collection) {
        pushAll((E[]) collection.toArray());
    }

    @Override
    public void pushAll(E[] array) {
        Arrays.stream(array).forEach(this::push);
    }

    @Override
    public int search(E e) {
        return IntStream.range(0, size)
                .filter(i -> elementData[i].equals(e))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public int clear() {
        final int numClearElement = size;
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
        return numClearElement;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public void sort(Comparator<E> comparator) {
        elementData = Arrays.stream(elementData)
                .map(x -> (E) x)
                .sorted(Comparator.nullsLast(comparator))
                .toArray(Object[]::new);
    }

}
