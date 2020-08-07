package com.epam.winter.java.lab.collections.map.entity;
import java.util.Objects;
import java.util.Optional;

/**
 * <pre>
 * a. Уметь работать с любым типом (кроме примитивного) (нельзя вставить null, как на место ключа, так и значения) +++
 * b. Содержать ключ / значение +++
 * c. Возвращать ключ / значение (getKey, getValue) +++
 * d. Установить ключ/ значение можно только через конструктор +++
 * e. При вставке null – exception +++
 * </pre>
 */

public class Entity<K, V> implements IEntity<K, V> {
    private final K key;
    private final V value;
    private int hash;

    public Entity(K key, V value) {
        this.key = Optional.of(key).orElseThrow(NullPointerException::new);
        this.value = Optional.of(value).orElseThrow(NullPointerException::new);
        this.hash = Math.abs(this.key.hashCode());
    }

    @Override
    public int getHash() {
        return hash;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?, ?> entity = (Entity<?, ?>) o;
        return Objects.equals(key, entity.key);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
