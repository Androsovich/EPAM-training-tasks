package com.epam.winter.java.lab.collections.stack;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.*;
import java.util.stream.IntStream;

public class ListStack<E> implements Stack<E> {
    private int size;
    private Node<E> tailNode;

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
            private Node<E> current;
            private Node<E> previous = tailNode;
            private int prevIndex = size;

            @Override
            public boolean hasNext() {
                return prevIndex > 0;
            }

            @Override
            public void remove() {
                if (current == null)
                    throw new NoSuchElementException();
                unlink(current);
            }

            @Override
            public E getNext() {
                if (!hasNext())
                    throw new NoSuchElementException();
                current = previous;
                previous = previous.prev;
                prevIndex--;
                return current.item;
            }

            @Override
            public void set(E element) {
                if (isElementInsert(element))
                    current.item = element;
            }

            @Override
            public void addBefore(E element) {
                if (isElementInsert(element)) {
                    ListStack.this.addBefore(element, current);
                }
            }

            @Override
            public void addAfter(E element) {
                if (isElementInsert(element)) {
                    ListStack.this.addAfter(element, current);
                }
            }

            private boolean isElementInsert(E element) {
                if (current == null)
                    throw new NoSuchElementException();
                return isUnique(element);
            }
        };
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private E unlink(Node<E> node) {
        final E element = node.item;
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

//        if (prev == null) {
//        tailNode = next;
        if (Objects.nonNull(prev)) {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tailNode = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.item = null;
        size--;
        return element;
    }

    @Override
    public E peek() {
        rangeCheck();
        return isEmpty() ? null : tailNode.item;
    }

    @Override
    public E pop() {
        rangeCheck();
        return isEmpty() ? null : unlink(tailNode);

    }

    @Override

    public void push(E element) {
        if (isUnique(element) && Objects.nonNull(element)) {
            linkLast(element);
        }
    }

    private void rangeCheck() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private boolean isUnique(E element) {
        boolean result = true;
        for (Node<E> node = tailNode; node != null; node = node.prev) {
            if (node.item.equals(element) || Objects.isNull(element)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private void linkLast(E element) {
        final Node<E> previous = tailNode;
        final Node<E> newNode = new ListStack.Node<>(previous, element, null);
        if (previous == null) {
            tailNode = newNode;
        } else {
            previous.next = newNode;
            tailNode = previous.next;
        }
        size++;
    }

    private void addBefore(E element, Node<E> current) {
        final Node<E> previous = current.prev;
        final Node<E> newNode = new Node<>(previous, element, current);
        current.prev = newNode;
        if (Objects.nonNull(previous)) {
            previous.next = newNode;
        }
        size++;
    }

    private void addAfter(E element, Node<E> current) {
        final Node<E> next = current.next;
        final Node<E> newNode = new Node<>(current, element, next);
        current.next = newNode;
        if (Objects.isNull(next)) {
            tailNode = newNode;
        } else {
            next.prev = newNode;
        }
        size++;
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
    public int search(E element) {
        E[] elements = toArray();
        return IntStream.range(0, elements.length)
                .filter(i -> elements[i].equals(element))
                .findFirst()
                .orElse(-1);
    }


    @Override
    public int clear() {
        final int numClearElement = size;
        for (Node<E> node = tailNode; node != null; ) {
            Node<E> prev = node.prev;
            node.item = null;
            node.next = null;
            node.prev = null;
            node = prev;
        }
        tailNode = null;
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
        Object[] elements = Arrays.stream(toArray())
                .sorted(comparator)
                .toArray(Object[]::new);
        clear();
        pushAll((E[]) elements);
    }

    @SuppressWarnings("unchecked")
    private E[] toArray() {
        Object[] result = new Object[size];
        int i = size;
        for (Node<E> node = tailNode; node != null; node = node.prev) {
            result[--i] = node.item;
        }
        return (E[]) result;
    }
}
