package com.epam.winter.java.lab.collections.queue;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.Collection;

public interface Queue<E>  {
    CollectionIterator<E> getIterator();//    Iterator getIterator();

    boolean isEmpty();//    boolean isEmpty();

    E peek(); //    элемент peek()

    E poll(); //    элемент poll()

    E pull();//    элемент pull()

    E remove();//    элемент remove()

    void push(E e);//    void push(Элемент)

    void pushAll(E[] e);//    void pushAll(коллекция / массив)

    void pushAll(Collection<E> collection);//    void pushAll(коллекция / массив)

    int search(E e);//    позиция search(элемент)

    int clear();//    количество clear;()

    int size();//    количество size;()

}
