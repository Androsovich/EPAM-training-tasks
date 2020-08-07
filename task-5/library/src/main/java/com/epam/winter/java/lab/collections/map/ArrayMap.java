package com.epam.winter.java.lab.collections.map;

import com.epam.winter.java.lab.collections.map.entity.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArrayMap<K, V> implements Map<K, V> {
    private final static int DEFAULT_CAPACITY = 16;
    private final static float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] hashTable;
    private int size;
    private float threshold;

    @SuppressWarnings("unchecked")
    public ArrayMap() {
        hashTable = new Node[DEFAULT_CAPACITY];
        threshold = hashTable.length * LOAD_FACTOR;
    }

    @SuppressWarnings("unchecked")
    private void arrayDoubling() {
        Node<K, V>[] oldHashTable = hashTable;  // copy reference current hashTable
        hashTable = new Node[oldHashTable.length * 2]; //create new hashTable
        size = 0;
        for (Node<K, V> node : oldHashTable) { //set into new hashTable from oldHashTable
            if (Objects.nonNull(node)) {
                for (Entity<K, V> entity : node.getEntities()) {
                    set(entity); //
                }
            }
        }
    }

    private class Node<K, V> {
        private Entity<K, V> entity;
        private Entity<K, V>[] entities;
        private int size = 0;

        @SuppressWarnings("unchecked")
        Node(Entity<K, V> entity) {
            this.entity = entity;
            entities = new Entity[DEFAULT_CAPACITY];
        }

        private int hash() {
            return entity.getHash() % hashTable.length;
        }

        private Entity<K, V> getEntity() {
            return entity;
        }

        private void setEntity(Entity<K, V> entity) {
            entities[size++] = entity;
        }

        private Entity<K, V>[] getEntities() {
            return entities;
        }

        private int getSize() {
            return size;
        }

        private Entity<K, V> remove(K key) {
            int index = indexOf(key);      //if index is is not found return -1
            Entity<K, V> result = entities[index];
            entities[index] = null;
            //move the array to the left by 1
            if (index > 1) {  //because index = 0 ArrayIndexBoundsException
                System.arraycopy(entities, index, entities, index - 1, entities.length - index);
            }
            size--;
            return result;
        }

        private int indexOf(K key) {
            int index = -1;
            for (int i = 0; i < entities.length; i++) {
                if (Objects.nonNull(entities[i])) {
                    if (entities[i].getKey().equals(key)) {
                        index = i;
                    }
                }
            }
            return index;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void set(final K key, final V value) {
        set(new Entity<>(key, value));
    }

    @Override
    public void set(final Entity<K, V> entity) {
        if (size + 1 >= threshold) { // if size > 2/3 increase by 2 times ( hashTable )
            threshold *= 2;
            arrayDoubling();
        }

        Node<K, V> newNode = new Node<>(entity);
        int index = newNode.hash();

        if (Objects.isNull(hashTable[index])) {
            simpleAdd(index, newNode);
        } else {
            collisionProcessing(index, newNode);
        }
    }

    /**
     * simpleAdd method add Entity in empty bucket
     *
     * @param node  - new Entity insert in hashTable
     * @param index - index empty bucket
     */
    private void simpleAdd(final int index, final Node<K, V> node) {
        //create new bucket
        hashTable[index] = new Node<>(null);
        //insert Entity into an array inside bucket
        hashTable[index].setEntity(node.getEntity());
        size++;
    }

    /**
     * if collision true add Entity in array bucket
     *
     * @throws IllegalArgumentException - if keys equals
     */
    private void collisionProcessing(final int index, final Node<K, V> newNode) {
        Entity<K, V> newEntity = newNode.getEntity();
        Entity<K, V>[] entities = hashTable[index].getEntities(); //get bucket
        for (Entity<K, V> entity : entities) { // null elements warning
            isKeyExists(entity, newEntity);     // checking keys for equality
        }
        hashTable[index].setEntity(newEntity);//if everything is OK add Entity into the array inside bucket
        size++;
    }

    /**
     * @param entityFromArray - Entity from array of bucket where happens collision
     * @throws IllegalArgumentException - if keys equals
     */
    private void isKeyExists(final Entity<K, V> entityFromArray, final Entity<K, V> newEntity) {
        final String EXCEPTION_MESSAGE = "   key exists";
        if (Objects.nonNull(entityFromArray)) {                         //eliminate null element from array
            if (newEntity.getKey().equals(entityFromArray.getKey()))   // newEntity must be not null
                throw new IllegalArgumentException(newEntity.getKey() + EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Entity<K, V> remove(K key) {
        Entity<K, V> result = null;
        int index = indexBucket(key);

        if (!isEmpty() && Objects.nonNull(hashTable[index])) {
            result = hashTable[index].remove(key);
            if (hashTable[index].getSize() == 0) {
                hashTable[index] = null;
            }
            size--;
        }
        return result;
    }

    @Override
    public Entity<K, V> remove(Entity<K, V> entity) {
        return remove(entity.getKey());
    }

    private int indexBucket(K key) {
        return key.hashCode() % hashTable.length;
    }

    //returns Entity or null if the key is not found or  bucket does not exist
    private Entity<K, V> searchEntity(K key) {
        Entity<K, V> result = null;
        if (!isEmpty()) {
            int index = indexBucket(key);
            Node<K, V> bucket = hashTable[index];
            if (Objects.nonNull(bucket)) {
                for (Entity<K, V> entity : bucket.entities) {
                    if (entity.getKey().equals(key)) {
                        result = entity;
                        break;
                    }
                }
            }
        }
        return result;
    }

    //need Node Stream
    @Override
    public List<K> getKeys() {
        return Arrays.stream(hashTable)
                .filter(Objects::nonNull)
                .flatMap(s -> Arrays.stream(s.getEntities()))
                .filter(Objects::nonNull)
                .map(Entity::getKey)
                .collect(Collectors.toList());

    }

    @Override
    public List<V> getValues() {
        return Arrays.stream(hashTable)
                .filter(Objects::nonNull)
                .flatMap(s -> Arrays.stream(s.getEntities()))
                .filter(Objects::nonNull)
                .map(Entity::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public V get(K key) {
        Entity<K, V> entity = searchEntity(key);
        return Objects.nonNull(entity) ? entity.getValue() : null;
    }


    @Override
    public Entity<K, V> getEntity(K key) {
        Entity<K, V> entity = searchEntity(key);
        return Objects.nonNull(entity) ? entity : null;
    }

    @Override
    public boolean contains(V value) {
        return Arrays.stream(hashTable)
                .filter(Objects::nonNull)
                .flatMap(s -> Arrays.stream(s.getEntities()))
                .filter(Objects::nonNull)
                .anyMatch(s -> s.getValue().equals(value));
    }

    @Override
    public int clear() {
        final int numClearElements = size;
        for (int i = 0; i < hashTable.length - 1; i++)
            hashTable[i] = null;
        size = 0;
        return numClearElements;
    }

    @Override
    public int size() {
        return size;
    }
}
