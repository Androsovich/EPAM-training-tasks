package com.epam.winter.java.lab.collections.map;

import com.epam.winter.java.lab.collections.map.entity.Entity;

import java.util.List;

/**
 * <pre>
 * a. Уметь работать с любым типом (кроме примитивного) (нельзя вставить null)(не могут быть повторяющиеся объекты) + Entity
 * b. Обе реализации должны основываться на hash-е ключа.
 * c. Возвращать true/false, если map пустая/не пустая (isEmpty)
 * d. Добавлять значения как по ключу/значению так и по Entity (set)
 * e. Выбрасывать исключение, если попытаться вставить по ключу, который уже
 *      есть в map
 * f. Удаление объекта (ключ / значение) по ключу / Entity (remove). Возвращать
 *      удаленную Entity
 * g. Получать List всех ключей (getKeys)
 * h. Получать List всех значений (getValues)
 * i. Возвращать true / false, если в map содержится / не содержится value
 *      (contains)
 * j. Получать значение / Entity по ключу (get / getEntity)
 * k. Вернуть размер map (size)
 * l. Очистить map (clear) Вернуть количество очищенных элементов.
 * </pre>
 */
public interface Map<K, V> {

    boolean isEmpty();//    Boolean isEmpty()

    void set(K key, V value);//    void set(key, value / Entity)

    void set(Entity<K, V> entity);//    void set(key, value / entity)

    Entity<K, V> remove(K key);//    Entity remove(key / Entity)

    Entity<K, V> remove(Entity<K, V> entity);//    entity remove(key / entity)

    List<K> getKeys();//    List getKeys()

    List<V> getValues();//    List getValues()

    V get(K key);//    value get(Ключ)

    Entity<K, V> getEntity(K key);//    Entity getEntity(Ключ)

    boolean contains(V value);//    Boolean contains(value)

    int clear();//    количество clear()

    int size();//    количество size()

}
