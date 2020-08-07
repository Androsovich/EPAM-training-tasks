package com.epam.winter.java.lab.collections.list;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.*;
import java.util.function.Predicate;

import static org.apache.commons.collections4.ComparatorUtils.NATURAL_COMPARATOR;

/**
 * сортировка если нету comparator - а , происходит по натуральному компаратору
 * т.е предусматривается что E реализует Comparable,
 * при этом все null- элементы смещены в конец
 * */
public class LinkedList<E> implements List<E> {
    private int maxSize;
    private int size;
    private Comparator<E> comparator;
    private Node<E> first;
    private Node<E> last;

    public LinkedList() {
        this(null);
    }

    public LinkedList(Comparator<E> comparator) {
        this.comparator = comparator;
        this.maxSize = Integer.MAX_VALUE - 8;
    }

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
            private Node<E> lastReturned;
            private Node<E> next = first;
            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public void remove() {
                if (lastReturned == null)
                    throw new IllegalStateException();

                Node<E> lastNext = lastReturned.next;
                unlink(lastReturned);
                if (next == lastReturned)
                    next = lastNext;
                else
                    nextIndex--;
                lastReturned = null;
            }

            @Override
            public E getNext() {
                if (!hasNext()) throw new NoSuchElementException();
                lastReturned = next;
                next = next.next;
                nextIndex++;
                return lastReturned.item;
            }

            @Override
            public void set(E element) {
                if (lastReturned == null)
                    throw new IllegalStateException();
                lastReturned.item = element;
            }


            @Override
            public void addBefore(E element) {
                int indexToInsert = searchIndexToInsert(element);
                int lastIndex = find(lastReturned.item);
                if (indexToInsert == lastIndex) {
                    if (lastReturned.prev == null) {
                        linkFirst(element);
                    } else {
                        linkBefore(element, lastReturned);
                    }
                    nextIndex++;
                }
            }

            @Override
            public void addAfter(E element) {
                int indexToInsert = searchIndexToInsert(element);
                if (indexToInsert == nextIndex) {
                    if (next == null) {
                        linkLast(element);
                    } else {
                        linkBefore(element, next);
                    }
                    nextIndex++;
                }
            }
        };
    }

    @Override
    public int add(E element) {
        if (maxSize < size + 1) throw new IndexOutOfBoundsException("limit max size " + maxSize);
        linkLast(element);
        sorted();
        return find(element);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private E[] sorted(Comparator comparator) {
        return (E[]) Arrays.stream(toArray())
                .sorted(Comparator.nullsLast(comparator))
                .toArray(Object[]::new);
    }

    private void sorted() {
        E[] sorted = Objects.nonNull(comparator) ? sorted(comparator) : sorted(NATURAL_COMPARATOR);
        CollectionIterator<E> iterator = getIterator();
        for (E element : sorted) {
            iterator.getNext();
            iterator.set(element);
        }
    }

    private void linkLast(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new LinkedList.Node<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private void linkFirst(E element) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, element, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    private void linkBefore(E element, Node<E> current) {
        final Node<E> previous = current.prev;
        final Node<E> newNode = new Node<>(previous, element, current);
        current.prev = newNode;
        if (previous == null)
            first = newNode;
        else
            previous.next = newNode;
        size++;
    }

    private Node<E> node(int index) {
        Node<E> eNode = first;
        for (int i = 0; i < index; i++)
            eNode = eNode.next;
        return eNode;
    }


    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        sorted();
        return oldVal;

    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    private int searchIndexToInsert(E e) {
        if (maxSize < size + 1) throw new IndexOutOfBoundsException("limit max size " + maxSize);
        final int position = find(e);
        //method  Arrays.binarySearch() return - (position + 1)
        return position == -1 ? 0 : Math.abs(position) - 1;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private int search(E element, Comparator comparator) {
        return Arrays.binarySearch(toArray(), 0, size, element, Comparator.nullsLast(comparator));
    }

    @Override
    public int find(E e) {
        return Objects.isNull(comparator) ? search(e, NATURAL_COMPARATOR) : search(e, comparator);
    }

    private E unlink(Node<E> eNode) {
        final E element = eNode.item;
        final Node<E> next = eNode.next;
        final Node<E> prev = eNode.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            eNode.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            eNode.next = null;
        }

        eNode.item = null;
        size--;
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size)
            array = (T[]) java.lang.reflect.Array.newInstance(
                    array.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = array;
        for (Node<E> eNode = first; eNode != null; eNode = eNode.next) {
            result[i++] = eNode.item;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> eNode = first; eNode != null; eNode = eNode.next)
            result[i++] = eNode.item;
        return (E[]) result;
    }

    @Override
    public void addAll(List<E> list) {
        addAll(list.toArray());
    }

    @Override
    public void addAll(E[] array) {
        Arrays.stream(array).forEach(this::add);
    }

    @SuppressWarnings({"unchecked"})
    private void filterElements(Predicate<Object> predicate) {
        E[] array = (E[]) Arrays.stream(toArray())
                .limit(size)
                .filter(predicate)
                .toArray(Object[]::new);
        clear();
        addAll(array);
    }

    @Override
    public void filterMatches(List<E> list) {
        filterMatches(list.toArray());
    }

    @Override
    public void filterMatches(E[] array) {
        Predicate<Object> matches = Arrays.asList(array)::contains;
        filterElements(matches);
    }

    @Override
    public void filterDifference(List<E> list) {
        filterDifference(list.toArray());
    }

    @Override
    public void filterDifference(E[] array) {
        Predicate<Object> difference = s -> Arrays.stream(array)
                .filter(Objects::nonNull)
                .noneMatch(m -> m.equals(s));
        filterElements(difference);

    }

    @Override
    public void setMaxSize(int maxSize) {
        checkMaxSize(maxSize);
        this.maxSize = maxSize;
        if (size >= maxSize) {
            size = maxSize;
            if (size > 0) {
                Node<E> node = node(size - 1);
                node.next = null;
                last = node;
            } else {
                first = last = null;
            }
        }
    }

    private void checkMaxSize(int maxSize) {
        if (maxSize < 0) throw new IndexOutOfBoundsException("max Size negative  " + maxSize);
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void trim() {
        final long numberElements = Arrays.stream(toArray())
                .limit(size)
                .filter(Objects::isNull)
                .count();

        size -= numberElements;
        if (size > 0) {
            Node<E> node = node(size - 1);
            node.next = null;
            last = node;
        } else {
            first = last = null;
        }
    }
}
