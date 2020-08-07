package com.epam.winter.java.lab.collections.stack;


import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

import java.util.Collection;
import java.util.Comparator;

public interface Stack<E>  {

    CollectionIterator<E> getIterator();//    Iterator getIterator();

    boolean isEmpty(); //    Boolean isEmpty()

    E peek();//    элемент peek()

    E pop();//    элемент pop()

    void push(E e);//    void push(Элемент)

    void pushAll(Collection<E> collection);//    void pushAll(коллекция / массив)

    void pushAll(E[] array);//    void pushAll(коллекция / массив)

    int search(E e);//    позиция search(элемент)

    int clear();//    количество clear()

    int size();// количество size()

    void sort(Comparator<E> comparator); //   void sort(компаратор)
}
