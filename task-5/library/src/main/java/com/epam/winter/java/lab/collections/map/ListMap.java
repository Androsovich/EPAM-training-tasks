package com.epam.winter.java.lab.collections.map;

import com.epam.winter.java.lab.collections.map.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListMap<K, V> implements Map<K, V> {
    private final static int DEFAULT_CAPACITY = 16;
    private final static float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] hashTable;
    private int size;
    private float threshold;

    @SuppressWarnings("unchecked")
    public ListMap() {
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
                Entity<K, V> entity = node.getEntity();
                set(entity);
            }
        }
    }

    private class Node<K, V> {
        private Entity<K, V> entity;
        private Node<K, V> next;

        Node(Entity<K, V> entity, Node<K, V> next) {
            this.entity = entity;
            this.next = next;
        }

        private int hash() {
            return entity.getHash() % hashTable.length;
        }

        private Entity<K, V> getEntity() {
            return entity;
        }
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void set(K key, V value) {
        set(new Entity<>(key, value));
    }

    @Override
    public void set(Entity<K, V> entity) {
        if (size + 1 >= threshold) { // if size > 2/3 increase by 2 times ( hashTable )
            threshold *= 2;
            arrayDoubling();
        }

        Node<K, V> newNode = new Node<>(entity, null);
        int index = newNode.hash();

        if (Objects.isNull(hashTable[index])) {
            simpleAdd(index, newNode);
        } else {
            collisionProcessing(index, newNode);
        }

    }

    private void simpleAdd(final int index, final Node<K, V> node) {
        //create new bucket
        hashTable[index] = node;
        size++;
    }

    /**
     * if collision true add Node in  bucket
     *
     * @throws IllegalArgumentException - if keys equals
     */
    private void collisionProcessing(final int index, final Node<K, V> newNode) {

        Node<K, V> node = hashTable[index]; //get bucket
        isKeyExists(node, newNode);//check keys

        //if everything is OK add newNode into the bucket(position - last node)
        for (Node<K, V> nextNode = node; nextNode != null; nextNode = nextNode.next) {
            if (Objects.isNull(nextNode.next)) {
                nextNode.next = newNode;
                break;//bad idea
            }
        }
        size++;
    }

    private void isKeyExists(final Node<K, V> node, final Node<K, V> newNode) {
        final String EXCEPTION_MESSAGE = "   key exists";
        final K newEntityKey = newNode.getEntity().getKey();
        for (Node<K, V> nextNode = node; nextNode != null; nextNode = nextNode.next) {
            K nextEntityKey = nextNode.getEntity().getKey();
            if (nextEntityKey.equals(newEntityKey)) {
                throw new IllegalArgumentException(newEntityKey + EXCEPTION_MESSAGE);
            }
        }
    }

    private int indexBucket(K key) {
        return key.hashCode() % hashTable.length;
    }

    @Override
    public Entity<K, V> remove(K key) {
        Entity<K, V> result = null;
        int index = indexBucket(key);
        if (!isEmpty() && Objects.nonNull(hashTable[index])) {
            Node<K, V> node = hashTable[index];

            //bad idea  {Node<K, V> nextNode = node, previous = node;}
            for (Node<K, V> nextNode = node, previous = node; nextNode != null; nextNode = nextNode.next) {
                K nextEntityKey = nextNode.getEntity().getKey();
                if (nextEntityKey.equals(key)) {
                    if (nextNode == node) {
                        hashTable[index] = nextNode.next;// if nextNode.next == null clear bucket else head next node
                    }
                    result = nextNode.getEntity();
                    previous.next = nextNode.next; //change links
                    break;
                }
                previous = nextNode;//next iteration
            }
            size--;
        }
        return result;
    }

    @Override
    public Entity<K, V> remove(Entity<K, V> entity) {
        return remove(entity.getKey());
    }

    //node not Null
    private List<Entity<K, V>> getEntitiesFromNode(Node<K, V> node) {
        List<Entity<K, V>> entities = new ArrayList<>(); //хотя бы 1 элемент но будет
        for (Node<K, V> nextNode = node; nextNode != null; nextNode = nextNode.next) {
            entities.add(nextNode.getEntity());
        }
        return entities;
    }

    @Override
    public List<K> getKeys() {
        return Arrays.stream(hashTable)
                .filter(Objects::nonNull)
                .flatMap(s -> getEntitiesFromNode(s).stream())
                .map(Entity::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<V> getValues() {
        return Arrays.stream(hashTable)
                .filter(Objects::nonNull)
                .flatMap(s -> getEntitiesFromNode(s).stream())
                .map(Entity::getValue)
                .collect(Collectors.toList());
    }

    //returns Entity or null if the key is not found or  bucket does not exist
    private Entity<K, V> searchEntity(K key) {
        Entity<K, V> result = null;
        if (!isEmpty()) {
            int index = indexBucket(key);
            Node<K, V> node = hashTable[index];
            if (Objects.nonNull(node)) {
                for (Entity<K, V> entity : getEntitiesFromNode(node)) {
                    if (entity.getKey().equals(key)) {
                        result = entity;
                        break;
                    }
                }
            }
        }
        return result;
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
        List<Entity<K, V>> entities = getEntitiesFromNode(hashTable[1]);
        return Arrays.stream(hashTable)
                .filter(Objects::nonNull)
                .flatMap(s -> getEntitiesFromNode(s).stream())
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
