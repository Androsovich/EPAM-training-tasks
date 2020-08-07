package com.epam.winter.java.lab.collections.iterator;

import java.util.Iterator;

/**
 * YAGNI
 * extends Iterator<E>
 *
 * @Override default E next() {
 * return getNext();
 * }
 */
public interface CollectionIterator<E> {

    boolean hasNext();

    void remove();

    E getNext();

    void set(E e);

    void addBefore(E e);

    void addAfter(E e);
}
