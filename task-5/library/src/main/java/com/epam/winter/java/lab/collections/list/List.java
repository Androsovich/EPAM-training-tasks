package com.epam.winter.java.lab.collections.list;


import com.epam.winter.java.lab.collections.iterator.CollectionIterator;

/**
 * a. Уметь работать с любым типом (кроме примитивного) (можно вставить null)
 * b. Возвращать Iterator (метод getIterator) (так должны делать несколько
 * коллекций, подумайте, как это оформить)
 * c. Устанавливать компаратор (компаратор можно передать только в
 * конструкторе)
 * d. Автоматически сортировать объекты при вставке (add) используя
 * компаратор, если компаратора нет – каким-либо образом. Возвращать
 * индекс вставленного элемента
 * e. Устанавливать максимальное количество элементов (метод setMaxSize),
 * если не установлено – неограниченно количество
 * f. Если установить MaxSize после того, как объект заполнен, причем,
 * количество элементов в нем больше этого числа – обрезать все не
 * влезающие элементы.
 * g. Вставлять элементы в коллекцию, как по одному, так и массивом /
 * коллекцией того же типа! (add, addAll) (используйте перегрузку методов).
 * Из методов ..All не возвращать ничего
 * h. Обновлять элемент по индексу (set). Возвращать старый элемент!
 * Сортировать при обновлении!
 * i. Удалять объекты (remove). Возвращая старый объект!
 * j. Полностью очищать коллекцию (clear)
 * k. Искать по элементу, возвращая номер позиции, или -1, если такого элемента
 * нет(find) (не забываем – ваша коллекция отсортирована)
 * l. Получать элементы по индексу (get)
 * m. Отдавать массив элементов (toArray)
 * n. Возвращать количество элементов (size)
 * o. Удалять null-элементы (trim)
 * p. Фильтровать сравнивая с коллекцией / массивом такого же типа, оставляя
 * отличия/одинаковые элементы. (filterMatches, filterDifference)
 *
 * maybe need to add impl.  Iterable<E>
 */
public interface List<E> {

    CollectionIterator<E> getIterator();//    Iterator getIterator();

    int add(E e);    //    int add(элемент)

    E get(int index);//    элемент get(Позиция)

    E set(int index, E e);//    элемент set(позиция, элемент)

    E remove(int index);//    элемент remove(индекс)

    int find(E e);//    позиция find(Элемент)

    <T> T[] toArray(T[] a);//    массив toArray(массив)

    E[] toArray();//    массив toArray(массив)

    void addAll(List<E> collection);//    void addAll(коллекция / массив)

    void addAll(E[] array);//    void addAll(коллекция / массив)

    void filterMatches(List<E> E);//    void filterMatches(Массив / Коллекция)

    void filterMatches(E[] array);//    void filterMatches(Массив / Коллекция)

    void filterDifference(List<E> E);//    void filterDifference(Массив / Коллекция)

    void filterDifference(E[] array);//    void filterDifference(Массив / Коллекция)

    void setMaxSize(int maxSize);//    void setMaxSize(число)

    int getMaxSize();//    void getMaxSize(число)

    void clear();//    void clear()

    int size();//    размер size()

    void trim();//    void trim()

}
