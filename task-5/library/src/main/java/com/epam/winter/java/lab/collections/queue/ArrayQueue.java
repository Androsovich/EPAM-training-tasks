package com.epam.winter.java.lab.collections.queue;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * очередь ограничена или DEFAULT_CAPACITY
 * или задается размер в конструкторе
 */
public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int HEAD_INDEX_QUEUE = 0;
    private int size;
    private Object[] elementData;

    public ArrayQueue(int defaultCapacity) {
        elementData = new Object[defaultCapacity];
    }

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public CollectionIterator<E> getIterator() {

        return new CollectionIterator<E>() {
            private int nextIndex = 0;
            private int lastIndex;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public void remove() {
                rangeCheck();
                System.arraycopy(elementData, lastIndex + 1  , elementData, lastIndex, elementData.length - lastIndex - 1);
                nextIndex--;
                size--;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E getNext() {
                if (!hasNext()) throw new NoSuchElementException();
                lastIndex = nextIndex++;
                return (E) elementData[lastIndex];
            }

            @Override
            public void set(E e) {
                if (Objects.nonNull(e))
                    elementData[lastIndex] = e;
            }

            @Override
            public void addBefore(E e) {
                if (Objects.nonNull(e) && isEnsureCapacity()) {
                    System.arraycopy(elementData, lastIndex, elementData, lastIndex + 1, size - lastIndex);
                    elementData[lastIndex] = e;
                    nextIndex++;
                    size++;
                }
            }

            @Override
            public void addAfter(E e) {
                if (Objects.nonNull(e) && isEnsureCapacity()) {
                    System.arraycopy(elementData, nextIndex, elementData, nextIndex + 1, size - nextIndex);
                    elementData[nextIndex] = e;
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
        rangeCheck();
        return (E) elementData[HEAD_INDEX_QUEUE];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E poll() {
        rangeCheck();
        E element = (E) elementData[HEAD_INDEX_QUEUE];
        elementData[HEAD_INDEX_QUEUE] = null;
        size--;
        if (!isEmpty()) {
            System.arraycopy(elementData, 1, elementData, 0, elementData.length - 1);
        }
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pull() {
        rangeCheck();
        return (E) elementData[size - 1];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove() {
        rangeCheck();
        E element = (E) elementData[size - 1];
        elementData[--size] = null;
        return element;
    }

    private void rangeCheck() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private boolean isEnsureCapacity() {
        return size < elementData.length;
    }

    @Override
    public void push(E e) {
        if (Objects.nonNull(e) && isEnsureCapacity())
            elementData[size++] = e;
    }

    @Override
    public void pushAll(E[] array) {
        Arrays.stream(array).forEach(this::push);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void pushAll(Collection<E> collection) {
        pushAll((E[]) collection.toArray());
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
        final int numClearElements = size;
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
        return numClearElements;
    }

    @Override
    public int size() {
        return size;
    }
}
