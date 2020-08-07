package com.epam.winter.java.lab.collections.map;

import com.epam.winter.java.lab.collections.map.entity.Entity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractMapTest {
    private Map<String, String> stringMap;
    private Map<TestUser, String> testUserMap;

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public void setTestUserMap(Map<TestUser, String> testUserMap) {
        this.testUserMap = testUserMap;
    }

    @Before
    public abstract void setUp();

    @After
    public void clearDate() {
        stringMap.clear();
        testUserMap.clear();
    }

    @Test
    public void isEmpty() {
        assertTrue(stringMap.isEmpty());
    }

    @Test
    public void testSimpleSet() {
        stringMap.set("1", "12");
        stringMap.set("2", "12");
        stringMap.set("4", "12");
        stringMap.set("5", "12");
        assertEquals(4, stringMap.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAddKeysEqualThrowException() {
        stringMap.set("1", "12");
        stringMap.set("2", "12");
        stringMap.set("4", "12");
        stringMap.set("4", "12");
        assertEquals(4, stringMap.size());
    }


    @Test
    public void testSetCollision() {
        testUserMap.set(new TestUser("qwerty", 5), "34");
        testUserMap.set(new TestUser("qwert", 5), "34");
        testUserMap.set(new TestUser("qwer3", 5), "34");
        testUserMap.set(new TestUser("1wer3", 5), "34");
        assertEquals(4, testUserMap.size());

    }

    @Test
    public void testRemoveAllEntities() {
        stringMap.set("1", "12");
        stringMap.set("2", "12");
        stringMap.set("4", "12");
        stringMap.set("34", "12");
        stringMap.remove("1");
        stringMap.remove("2");
        stringMap.remove("4");
        stringMap.remove("34");
        assertTrue(stringMap.isEmpty());
    }


    @Test
    public void testGetKeysObjects() {
        TestUser[] usersActual = {
                new TestUser("2", 34),
                new TestUser("4", 555),
                new TestUser("6", 51234),
                new TestUser("8", 5434),
        };
        testUserMap.set(new TestUser("2", 34), "34");
        testUserMap.set(new TestUser("4", 555), "34");
        testUserMap.set(new TestUser("6", 51234), "34");
        testUserMap.set(new TestUser("8", 5434), "34");
        List<TestUser> keys = testUserMap.getKeys();
        for (TestUser user : usersActual) {
            assertTrue(keys.contains(user));
        }
    }

    @Test
    public void testGetValues() {
        String[] actual = {"ABC", "a4", "re", "re"};

        testUserMap.set(new TestUser("2", 34), "ABC");
        testUserMap.set(new TestUser("42", 555), "a4");
        testUserMap.set(new TestUser("61", 51234), "re");
        testUserMap.set(new TestUser("85", 5434), "re");

        List<String> values = testUserMap.getValues();

        for (String value : actual) {
            assertTrue(values.contains(value));
        }
    }

    @Test
    public void testGet() {
        String actual = "ABC";
        TestUser testUser = new TestUser("2", 34);

        testUserMap.set(new TestUser("2", 34), "ABC");

        assertEquals(actual, testUserMap.get(testUser));
    }

    @Test
    public void testGetEntity() {
        Entity<String, String> actual = new Entity<>("345", "123");

        stringMap.set("345", "123");
        Entity<String, String> expected = stringMap.getEntity("345");

        assertEquals(actual, expected);
    }

    @Test
    public void contains() {
        stringMap.set("1", "wqwqw");
        stringMap.set("2", "12334we");
        stringMap.set("4", "12aaa");
        stringMap.set("34", "12sasss");

        assertTrue(stringMap.contains("wqwqw"));
        assertTrue(stringMap.contains("12334we"));
        assertTrue(stringMap.contains("12aaa"));
        assertTrue(stringMap.contains("12sasss"));
    }

    @Test
    public void clear() {
        stringMap.set("1", "wqwqw");
        stringMap.set("2", "12334we");
        stringMap.set("4", "12aaa");
        stringMap.set("34", "12sasss");

        stringMap.clear();

        assertTrue(stringMap.isEmpty());
    }

    @Test
    public void size() {
        stringMap.set("1", "wqwqw");
        stringMap.set("2", "12334we");
        stringMap.set("4", "12aaa");
        stringMap.set("34", "12sasss");
        stringMap.set("35", "12sasss");
        stringMap.set("36", "12sasss");

        assertEquals(6, stringMap.size());
    }

    @Test
    public void testRemoveHeadCollision() {
        testUserMap.set(new TestUser("qwerty", 5), "34");
        testUserMap.set(new TestUser("qwert", 5), "34");
        testUserMap.set(new TestUser("qwer3", 5), "34");
        testUserMap.set(new TestUser("1wer3", 5), "34");
        testUserMap.remove(new TestUser("qwerty", 5));

        assertEquals(3, testUserMap.size());
    }

    @Test
    public void remove() {
        stringMap.set("1", "12");
        stringMap.set("2", "12");
        stringMap.set("4", "12");
        stringMap.set("34", "12");
        stringMap.remove("1");
        stringMap.remove("2");
        stringMap.remove("4");
        stringMap.remove("34");
        assertTrue(stringMap.isEmpty());
    }

    @Test
    public void testSimpleRemove() {
        Entity<String, String> actual = new Entity<>("2", "12");
        stringMap.set("1", "12");
        stringMap.set("2", "12");
        stringMap.set("4", "12");
        stringMap.set("34", "12");
        assertEquals(4, stringMap.size());
        Assert.assertEquals(actual, stringMap.remove("2"));
        assertEquals(3, stringMap.size());
    }

}