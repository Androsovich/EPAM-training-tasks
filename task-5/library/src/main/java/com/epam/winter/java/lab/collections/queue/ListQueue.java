package com.epam.winter.java.lab.collections.queue;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

public class ListQueue<E> implements Queue<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public CollectionIterator<E> getIterator() {
        return new CollectionIterator<E>() {
            private int nextIndex = 0;
            private Node<E> current;
            private Node<E> next = first;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public void remove() {
                if (current == null)
                    throw new IllegalStateException();
                unlink(current);
                nextIndex--;
            }

            @Override
            public E getNext() {
                if (!hasNext())
                    throw new NoSuchElementException();
                current = next;
                next = next.next;
                nextIndex++;
                return current.item;
            }

            @Override
            public void set(E element) {
                if (current == null)
                    throw new IllegalStateException();
                if (Objects.nonNull(element))
                    current.item = element;
            }

            @Override
            public void addBefore(E element) {
                if (Objects.nonNull(element)) {
                    if (current == null)
                        throw new IllegalStateException();
                    ListQueue.this.addBefore(element, current);
                    nextIndex++;
                }
            }

            @Override
            public void addAfter(E element) {
                if (Objects.nonNull(element)) {
                    if (current == null)
                        throw new IllegalStateException();
                    ListQueue.this.addAfter(element, current);
                    nextIndex++;
                }
            }
        };
    }


    private void addBefore(E element, Node<E> current) {
        final Node<E> previous = current.prev;
        final Node<E> newNode = new Node<>(previous, element, current);
        current.prev = newNode;
        if (Objects.isNull(previous)) {
            first = newNode;
        } else {
            previous.next = newNode;
        }
        size++;
    }

    private void addAfter(E element, Node<E> current) {
        final Node<E> next = current.next;
        final Node<E> newNode = new Node<>(current, element, next);
        current.next = newNode;
        if (Objects.isNull(next)) {
            last = newNode;
        } else {
            next.prev = newNode;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E peek() {
        rangeCheck();
        return first.item;
    }

    @Override
    public E poll() {
        rangeCheck();
        return unlink(first);
    }

    @Override
    public E pull() {
        rangeCheck();
        return last.item;
    }

    @Override
    public E remove() {
        rangeCheck();
        return unlink(last);
    }

    @Override
    public void push(E element) {
        if (Objects.nonNull(element)) {
            addLast(element);
        }
    }

    private void addLast(E element) {
        final Node<E> prevNode = last;
        final Node<E> newNode = new Node<>(prevNode, element, null);
        last = newNode;
        if (prevNode == null) {
            first = newNode;
        } else {
            prevNode.next = newNode;
        }
        size++;
    }

    private E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return element;
    }

    private void rangeCheck() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    @Override
    public void pushAll(E[] array) {
        for (E element : array) {
            this.push(element);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void pushAll(Collection<E> collection) {
        pushAll((E[]) collection.toArray());
    }

    @Override
    public int search(E e) {
        E[] elements = toArray();
        return IntStream.range(0, size)
                .filter(i -> elements[i].equals(e))
                .findFirst()
                .orElse(-1);

    }

    @Override
    public int clear() {
        final int result = size;
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private E[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return (E[]) result;
    }
}
