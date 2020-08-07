package com.epam.winter.java.lab.collections.list;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import static org.junit.Assert.*;

public abstract class AbstractListTest {
    private static java.util.Map<String, String[]> mapTestDataString = new HashMap<>();
    Comparator<String> comparator;
    private List<String> stringList;
    private List<Integer> integerList;
    private List<String> stringsWithComparator;

    public void setComparator(Comparator<String> comparator) {
        this.comparator = comparator;
    }

    public void setStringsWithComparator(List<String> stringsWithComparator) {
        this.stringsWithComparator = stringsWithComparator;
    }

    void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @BeforeClass
    public static void initDate() {
        mapTestDataString.put("testArray", new String[]{"12", "21", null, "1", "3", "2", "5", null});
        mapTestDataString.put("testSortedAddArray", new String[]{"1", "12", "2", "21", "3", "5", null, null});
        mapTestDataString.put("testSortedArray", new String[]{"1", "12", "2", "21", "3", "5", "8", null, null});
        mapTestDataString.put("expectedArrayAfterSet", new String[]{"1", "2", "21", "22", "3", "4", "5", null, null});
    }

    @Before
    public abstract void setUp();

    @After
    public void clearDate() {
        stringsWithComparator.clear();
        integerList.clear();
        stringList.clear();
    }

    @Test
    public void testListInit() {
        assertEquals(0, stringList.size());
    }

    @Test
    public void testAddToEmptyList() {
        stringList.add("A");
        assertEquals(1, stringList.size());
        stringList.clear();
        stringList.add(null);
        assertEquals("size = 1, after clear and add null", 1, stringList.size());
        stringList.add(null);
        assertEquals("size = 2, after  add  next element", 2, stringList.size());
    }

    @Test
    public void testAddReturnIndexElement() {
        assertEquals(0, integerList.add(1));
        assertEquals(1, integerList.add(2));
        assertEquals(2, integerList.add(5));
        assertEquals(2, integerList.add(3));
        assertEquals(4, integerList.add(null));
    }

    @Test
    public void testAddResultIndexSortedValue() {
        String[] actual = mapTestDataString.get("testArray");
        String[] expected = mapTestDataString.get("testSortedAddArray");

        Arrays.stream(actual).forEach(stringList::add);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], stringList.get(i));
        }
        assertEquals(stringList.size(), expected.length);
    }

    @Test
    public void testGet() {
        String[] actual = mapTestDataString.get("testSortedArray");
        Arrays.stream(actual).forEach(stringList::add);
        assertEquals("2", stringList.get(2));
        assertEquals("3", stringList.get(4));
        assertNull(stringList.get(7));
        assertNull(stringList.get(8));
    }

    @Test
    public void testSet() {
        String[] actual = new String[]{"1", "12", "2", "21", "3", "5", "8", null, null};

        Arrays.stream(actual).forEach(stringList::add);

        assertEquals(actual[4], stringList.set(4, "4"));
        assertEquals(actual[5], stringList.set(5, "22"));

        actual[4] = "4";
        actual[5] = "22";
        Arrays.sort(actual, Comparator.nullsLast(Comparator.naturalOrder()));
        assertEquals(actual.length, stringList.size());
        assertArrayEquals("Compare element", actual, stringList.toArray());
    }

    @Test
    public void testSetAndAddWithComparator() {
        String[] actual = new String[]{"1", "12", "2", "21", "3", "5", "8", null, null};

        Arrays.sort(actual, Comparator.nullsLast(comparator));
        Arrays.stream(actual).forEach(stringsWithComparator::add);

        assertEquals(actual[4], stringsWithComparator.set(4, "4"));
        assertEquals(actual[5], stringsWithComparator.set(5, "22"));

        actual[4] = "4";
        actual[5] = "22";
        Arrays.sort(actual, Comparator.nullsLast(comparator));
        assertEquals(actual.length, stringsWithComparator.size());
        assertArrayEquals("Compare element", actual, stringsWithComparator.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexEqualSize() {
        stringList.add("1");
        stringList.set(stringList.size(), "next");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetWithEmptyList() {
        stringList.set(0, "next");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetNegativeIndex() {
        stringList.set(-1, "next");
    }


    @Test
    public void testRemove() {
        stringList.add("AA");
        stringList.add("CC");
        stringList.add("BB");
        stringList.remove(1);
        assertNotEquals(3, stringList.size());
        assertEquals("CC", stringList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithEmptyList() {
        stringList.remove(0);
    }


    @Test
    public void find() {
        stringList.add("AA");
        stringList.add("CC");
        stringList.add("BB");
        stringList.add(null);
        assertEquals(0, stringList.find("AA"));
        assertEquals(1, stringList.find("BB"));
        assertEquals(2, stringList.find("CC"));
        assertEquals(3, stringList.find(null));
        assertEquals(4, stringList.size());
        assertTrue(stringList.find("111") < 0);
    }

    @Test
    public void toArray() {
        String[] actual = mapTestDataString.get("testSortedAddArray");
        Arrays.stream(actual).forEach(stringList::add);
        assertArrayEquals(actual, stringList.toArray());
    }

    @Test
    public void addAll() {
        String[] actual = mapTestDataString.get("testSortedAddArray");
        stringList.addAll(actual);
        assertArrayEquals(actual, stringList.toArray());
    }


    @Test
    public void filterMatches() {
        Integer[] expected = {1, 7, 12};
        Integer[] arrayFirst = {1, 4, 7, 3, 2, 12};
        Integer[] arraySecond = {1, 5, 7, 12};
        integerList.addAll(arrayFirst);
        integerList.filterMatches(arraySecond);

        assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    public void filterDifference() {
        Integer[] expected = {2, 3, 4};
        Integer[] arrayFirst = {1, 4, 7, 3, 2, 12};
        Integer[] arraySecond = {1, 5, 7, 12};

        integerList.addAll(arrayFirst);
        integerList.filterDifference(arraySecond);

        assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    public void setMaxSize() {
        stringList.add("11");
        stringList.add("12");
        stringList.add("13");
        stringList.add("14");
        stringList.add("15");
        stringList.add("16");
        stringList.add("17");
        stringList.add("18");
        stringList.add("19");
        stringList.add("20");
        stringList.add("21");
        stringList.add("22");
        stringList.add("23");
        stringList.add("24");
        assertEquals(14, stringList.size());
        stringList.setMaxSize(7);
        assertEquals(7, stringList.size());
        assertEquals("17", stringList.get(6));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementMoreMaxSizeThrowException() {
        stringList.add("11");
        stringList.add("12");
        stringList.add("13");
        assertEquals(3, stringList.size());
        stringList.setMaxSize(3);
        stringList.add("34");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementMaxSizeZeroThrowException() {
        stringList.setMaxSize(0);
        stringList.add("33");
    }

    @Test
    public void clear() {
        String[] actual = mapTestDataString.get("testSortedArray");
        Arrays.stream(actual).forEach(stringList::add);
        assertEquals(stringList.size(), actual.length);
        stringList.clear();
        assertEquals(0, stringList.size());
    }

    @Test
    public void size() {
        String[] actual = mapTestDataString.get("testSortedArray");
        assertEquals(stringList.size(), 0);

        Arrays.stream(actual).forEach(stringList::add);
        assertEquals(stringList.size(), actual.length);

        stringList.clear();

        stringList.add("1");
        assertEquals(stringList.size(), 1);

        stringList.add("45");
        assertEquals(stringList.size(), 2);
    }

    @Test
    public void trim() {
        stringList.add("AA");
        stringList.add("AA");
        stringList.add("AA");
        stringList.add("AA");
        stringList.add("AA");
        stringList.add(null);
        stringList.add(null);
        stringList.add(null);
        stringList.add(null);
        assertEquals(9, stringList.size());
        stringList.trim();
        assertEquals(5, stringList.size());
    }

    @Test
    public void testTrimListAllNullElements() {
        stringList.add(null);
        stringList.add(null);
        stringList.add(null);
        stringList.add(null);
        stringList.trim();
        assertEquals(0, stringList.size());

    }

    @Test
    public void testIteratorAddBeforeAndAddAfter() {
        Integer[] expected = {1, 2, 4, 5, 7, 8};

        integerList.add(1);
        integerList.add(2);
        integerList.add(5);
        integerList.add(8);
        CollectionIterator<Integer> iterator = integerList.getIterator();
        while (iterator.hasNext()) {
            Integer test = iterator.getNext();
            if (test == 2) {
                iterator.addAfter(4);
            }
            if (test == 8) {
                iterator.addBefore(7);
            }
        }
        assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    public void testIteratorRemove() {
        Integer[] expected = {2, 5, 7};
        integerList.add(1);
        integerList.add(2);
        integerList.add(4);
        integerList.add(5);
        integerList.add(7);
        integerList.add(8);
        CollectionIterator<Integer> iterator = integerList.getIterator();
        while (iterator.hasNext()) {
            Integer test = iterator.getNext();
            if (test == 1) {
                iterator.remove();
            }
            if (test == 4) {
                iterator.remove();
            }
            if (test == 8) {
                iterator.remove();
            }
        }
        assertArrayEquals(expected, integerList.toArray());
    }
}
