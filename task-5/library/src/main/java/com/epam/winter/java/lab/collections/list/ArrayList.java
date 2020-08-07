package com.epam.winter.java.lab.collections.list;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.*;
import java.util.function.Predicate;

import static org.apache.commons.collections4.ComparatorUtils.NATURAL_COMPARATOR;

/**
 * сортировка если нету comparator - а , происходит по натуральному компаратору
 * т.е предусматривается что E реализует Comparable
 * при этом все null- элементы смещены в конец
 * */
public class ArrayList<E> implements List<E> {

    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private int defaultCapacity = 10;
    private int maxSize;
    private int size;
    private Object[] elementData;
    private final Comparator<E> comparator;


    public ArrayList() {
        this(null);
    }

    public ArrayList(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elementData = EMPTY_ELEMENT_DATA;
        this.maxSize = Integer.MAX_VALUE - 8;
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
                if (size == 0)// if not Empty
                    throw new NoSuchElementException();
                    System.arraycopy(elementData, lastIndex + 1, elementData, lastIndex, elementData.length - lastIndex -1 );
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
                elementData[lastIndex] = e;
            }

            //use BinarySearch to search for a index and compare it with a insert index
            //if index to insert before element insert else no
            @Override
            public void addBefore(E e) {
                final int indexToInsert = searchIndexToInsert(e);
                if (indexToInsert == lastIndex) {
                    insert(lastIndex, e);
                }
            }

            //use BinarySearch to search for a index and compare it with a insert index
            //if index to insert after element insert else no
            @Override
            public void addAfter(E e) {
                final int indexToInsert = searchIndexToInsert(e);
                if (indexToInsert == nextIndex)
                    insert(nextIndex, e);
            }
        };
    }

    @Override
    public int add(E e) {
        if (maxSize < size + 1) throw new IndexOutOfBoundsException("limit max size " + maxSize);
        ensureCapacityInternal(size + 1);
        int index = size++;
        elementData[index] = e;
        sorted();
        return find(e);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object[] sorted(Comparator comparator) {
        return Arrays.stream(elementData)
                .sorted(Comparator.nullsLast(comparator))
                .toArray(Object[]::new);
    }

    private void sorted() {
        elementData = Objects.nonNull(comparator) ? sorted(comparator) : sorted(NATURAL_COMPARATOR);
    }

    private int calculateCapacity(Object[] elementData, int minCapacity) {
        return elementData == EMPTY_ELEMENT_DATA ? Math.max(defaultCapacity, minCapacity) : minCapacity;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - maxSize > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > maxSize) ? Integer.MAX_VALUE : maxSize;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elementData[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E newElement) {
        rangeCheck(index);
        E lastElement = (E) elementData[index];
        elementData[index] = newElement;
        sorted();
        return lastElement;
    }

    @Override
    public E remove(int index) {
        E lastElement = set(index, null);
        size--;
        return lastElement;
    }


    @Override
    public int find(E e) {
        return Objects.isNull(comparator) ? search(e, NATURAL_COMPARATOR) : search(e, comparator);
    }

    private void insert(int index, E e) {
        final int indexToInsert = Math.max(index, 0);//if  index = -1
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, indexToInsert, elementData, indexToInsert + 1,
                size - indexToInsert);
        elementData[indexToInsert] = e;
        size++;
    }

    private int searchIndexToInsert(E e) {
        if (maxSize < size + 1) throw new IndexOutOfBoundsException("limit max size " + maxSize);
        final int position = find(e);
        //method  Arrays.binarySearch() return - (position + 1)
        return position == -1 ? 0 : Math.abs(position) - 1;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private int search(E e, Comparator comparator) {
        return Arrays.binarySearch(elementData, 0, size, e, Comparator.nullsLast(comparator));
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size)
            return (T[]) Arrays.copyOf(elementData, size, array.getClass());
        System.arraycopy(elementData, 0, array, 0, size);
        if (array.length > size)
            array[size] = null;
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(elementData, size);
    }

    @Override
    public void addAll(List<E> list) {
        addAll(list.toArray());
    }

    @Override
    public void addAll(E[] array) {
        Arrays.stream(array).forEach(this::add);
    }

    private void filterElements(Predicate<Object> predicate) {
        elementData = Arrays.stream(elementData)
                .limit(size)
                .filter(predicate)
                .toArray(Object[]::new);
        size = elementData.length;
        sorted();
    }

    @Override
    public void filterMatches(List<E> list) {
        filterMatches(list.toArray());
    }

    @Override
    public void filterMatches(E[] array) {
        Predicate<Object> matches = s -> Arrays.asList(array).contains(s);
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
        this.defaultCapacity = maxSize;
        if (size >= maxSize) {
            size = maxSize;
            elementData = Arrays.copyOf(elementData, maxSize);
        }
    }

    private void checkMaxSize(int maxSize) {
        if (maxSize < 0) throw new IndexOutOfBoundsException("max Size negative  " + maxSize);
    }

    @Override
    public int getMaxSize() {
        return this.maxSize;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void trim() {
        final long numberElements = Arrays.stream(elementData).limit(size).filter(Objects::isNull).count();
        size -= numberElements;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Array out of bounds " + index);
    }
}
