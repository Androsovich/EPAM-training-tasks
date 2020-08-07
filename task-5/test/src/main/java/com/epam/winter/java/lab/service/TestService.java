package com.epam.winter.java.lab.service;

import com.epam.winter.java.lab.collections.iterator.CollectionIterator;
import com.epam.winter.java.lab.collections.list.List;
import com.epam.winter.java.lab.collections.map.Map;
import com.epam.winter.java.lab.collections.map.entity.Entity;
import com.epam.winter.java.lab.collections.queue.Queue;
import com.epam.winter.java.lab.collections.stack.Stack;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.epam.winter.java.lab.constants.TestDate.*;

public class TestService {

    private static <T> void printQueue(Queue<T> queue, T[] testArray) {
        System.out.print("queue result : ");
        IntStream.range(0, queue.size()).forEach(x -> System.out.print(queue.poll() + " "));
        queue.pushAll(testArray);
        System.out.println();
    }

    private static <T> void printStack(Stack<T> stack, T[] testArray) {
        System.out.print("stack result : ");
        IntStream.range(0, stack.size()).forEach(x -> System.out.print(stack.pop() + " "));
        stack.pushAll(testArray);
        System.out.println();
    }

    public static <T extends Comparable<T>> void testList(List<T> userList, String message, T[] elements,
                                                          T[] otherElements, T[] testElements) {
        System.out.println("test List User " + message);
        System.out.println(TEST_DELIMITER);
        userList.addAll(elements);
        System.out.println("actual array :" + Arrays.toString(elements));
        System.out.println("List after addAll : " + Arrays.toString(userList.toArray()));
        T removedElement = userList.remove(0);
        System.out.println("test List remove  " + removedElement + " index  0 : result " + Arrays.toString(userList.toArray()));
        System.out.println("find position  " + elements[1] + "  : index = " + userList.find(elements[1]));
        System.out.println("find position " + removedElement + "  not exists  : index = " + userList.find(removedElement));
        System.out.println("set  " + removedElement +
                " : index  1 , result element after set  : prev element  = " +
                userList.set(1, removedElement) + " ");
        System.out.println("list after set : result " + Arrays.toString(userList.toArray()) + ")");
        System.out.println("list get(1) : result  = " + userList.get(1) +
                " : list  " + Arrays.toString(userList.toArray()));
        userList.trim();
        System.out.println("test trim : list  " + Arrays.toString(userList.toArray()));
        System.out.println(" TEST_OTHER_ARRAY  " + Arrays.toString(otherElements));
        System.out.println(" list : " + Arrays.toString(userList.toArray()));
        userList.filterMatches(otherElements);
        System.out.println("test filterMatches : " + Arrays.toString(userList.toArray()));
        userList.addAll(elements);
        System.out.println(" TEST_OTHER_ARRAY  " + Arrays.toString(otherElements));
        System.out.println(" list : " + Arrays.toString(userList.toArray()));
        userList.filterDifference(otherElements);
        System.out.println("test filterDifference : " + Arrays.toString(userList.toArray()));
        userList.addAll(otherElements);
        System.out.println("test MaxSize  = 5 :  list " + Arrays.toString(userList.toArray()));
        userList.setMaxSize(5);
        System.out.println("test MaxSize  = 5 : result  list " + Arrays.toString(userList.toArray()));
        System.out.println("test iterator :");
        CollectionIterator<T> iterator = userList.getIterator();
        userList.setMaxSize(9);
        while (iterator.hasNext()) {
            T nextElement = iterator.getNext();
            System.out.println("nextElement  = " + nextElement);
            if (nextElement.equals(testElements[REMOVE_INDEX_ELEMENT])) {
                System.out.println("remove element " + testElements[REMOVE_INDEX_ELEMENT] + ": ");
                iterator.remove();
            }

            if (nextElement.equals(testElements[AFTER_INDEX_ELEMENT])) {
                System.out.println("addAfter element " + testElements[AFTER_INDEX_ELEMENT] + ": " + testElements[INDEX_ELEMENT_ADD_AFTER]);
                iterator.addAfter(testElements[INDEX_ELEMENT_ADD_AFTER]);
            }

            if (nextElement.equals(testElements[BEFORE_INDEX_ELEMENT])) {
                System.out.println("addBefore element " + testElements[BEFORE_INDEX_ELEMENT] + ": " + testElements[INDEX_ELEMENT_ADD_BEFORE]);
                iterator.addBefore(testElements[INDEX_ELEMENT_ADD_BEFORE]);
            }
        }
        System.out.println(" result after iteration  list : " + Arrays.toString(userList.toArray()));
        System.out.println();

    }

    public static <T extends Comparable<T>> void testStack(Stack<T> userStack, String message, T[] elements,
                                                           T[] testElements) {

        System.out.println("test Stack  " + message);
        System.out.println(TEST_DELIMITER);
        System.out.println("test pushAll : array " + Arrays.toString(elements));
        userStack.pushAll(elements);
        printStack(userStack, elements);
        System.out.println("test peek : " + userStack.peek());
        printStack(userStack, elements);
        System.out.println("test pop : " + userStack.pop());
        System.out.println("stack after pop : ");
        printStack(userStack, elements);
        System.out.println("test push   " + elements[1] + " : ");
        userStack.push(elements[0]);
        printStack(userStack, elements);
        System.out.println("test push   " + testElements[testElements.length -1] + " : ");
        userStack.push(testElements[testElements.length -1]);
        printStack(userStack, elements);
        System.out.println("test search " + elements[1] + " : index = " + userStack.search(elements[1]));
        System.out.println("test sort : ");
        userStack.sort(T::compareTo);
        printStack(userStack, elements);
        System.out.println("test clear : number =  " + userStack.clear());
        System.out.println("test iterator ");
        userStack.pushAll(elements);

        CollectionIterator<T> stackIterator = userStack.getIterator();
        while (stackIterator.hasNext()) {
            T nextElement = stackIterator.getNext();
            System.out.println("nextElement  = " + nextElement);
            if (nextElement.equals(testElements[REMOVE_INDEX_ELEMENT])) {
                System.out.println("remove element " + testElements[REMOVE_INDEX_ELEMENT] + ": ");
                stackIterator.remove();
            }

            if (nextElement.equals(testElements[AFTER_INDEX_ELEMENT])) {
                System.out.println("addAfter element " + testElements[AFTER_INDEX_ELEMENT] + ": " + testElements[INDEX_ELEMENT_ADD_AFTER]);
                stackIterator.addAfter(testElements[INDEX_ELEMENT_ADD_AFTER]);
            }
            if (nextElement.equals(testElements[BEFORE_INDEX_ELEMENT])) {
                System.out.println("addBefore element " + testElements[BEFORE_INDEX_ELEMENT] + ": " + testElements[INDEX_ELEMENT_ADD_BEFORE]);
                stackIterator.addBefore(testElements[INDEX_ELEMENT_ADD_BEFORE]);
            }
        }
        printStack(userStack, elements);
        System.out.println(TEST_DELIMITER);
        System.out.println();

    }

    public static <T extends Comparable<T>> void testQueue(Queue<T> queue, String message, T[] elements,
                                                          T[] testElements) {
        System.out.println(TEST_DELIMITER);
        System.out.println();
        System.out.println(message);
        queue.pushAll(elements);
        System.out.println("test pushAll : array " + Arrays.toString(elements));
        printQueue(queue, elements);
        System.out.println("test pull : element =  " + queue.pull());
        System.out.println("test peek : element =  " + queue.peek());
        System.out.println("test poll : element =  " + queue.poll());
        System.out.println("test remove : element =  " + queue.remove());
        System.out.println("test search: element " + testElements[0] + " : index   " + queue.search(testElements[0]));
        System.out.println("test clear: number elements =  " + queue.clear());
        printQueue(queue, elements);
        System.out.println(" pushAll : array " + Arrays.toString(elements));
        System.out.println(" test push : element " + testElements[testElements.length - 1]);
        queue.push(testElements[testElements.length - 1]);
        printQueue(queue, elements);
        System.out.println("test iterator ");
        CollectionIterator<T> queueIterator = queue.getIterator();
        while (queueIterator.hasNext()) {
            T nextElement = queueIterator.getNext();
            System.out.println("nextElement  = " + nextElement);
            if (nextElement.equals(testElements[REMOVE_INDEX_ELEMENT])) {
                System.out.println("remove element " + testElements[REMOVE_INDEX_ELEMENT] + ": ");
                queueIterator.remove();
            }

            if (nextElement.equals(testElements[AFTER_INDEX_ELEMENT])) {
                System.out.println("addAfter element " + testElements[AFTER_INDEX_ELEMENT] + ": " + testElements[INDEX_ELEMENT_ADD_AFTER]);
                queueIterator.addAfter(testElements[INDEX_ELEMENT_ADD_AFTER]);
            }

            if (nextElement.equals(testElements[BEFORE_INDEX_ELEMENT])) {
                System.out.println("addBefore element " + testElements[BEFORE_INDEX_ELEMENT] + ": " + testElements[INDEX_ELEMENT_ADD_BEFORE]);
                queueIterator.addBefore(testElements[INDEX_ELEMENT_ADD_BEFORE]);
            }
        }
        printQueue(queue, testElements);
        queue.clear();
        System.out.println(TEST_DELIMITER);
        System.out.println();
    }

    public static <T extends Comparable<T>> void testMap(Map<String, T> map, String message, T[] elements,
                                                         T[] testElements) {
        final String FIRST_KEY = "first";
        final String SECOND_KEY = "second";
        final String THIRD_KEY = "third";
        final String OTHER_KEY = "other";
        System.out.println(message);
        System.out.println(TEST_DELIMITER);
        map.set(FIRST_KEY, elements[FIRST_ELEMENT_VALUE]);
        map.set(SECOND_KEY, elements[SECOND_ELEMENT_VALUE]);
        map.set(THIRD_KEY, elements[THIRD_ELEMENT_VALUE]);
        Entity<String, T> entity = new Entity<>(OTHER_KEY, testElements[INDEX_ELEMENT_OTHER]);
        System.out.println("test getKeys " + Arrays.toString(map.getKeys().toArray()));
        System.out.println("test getValues " + Arrays.toString(map.getValues().toArray()));
        System.out.println("test get third " + map.get(THIRD_KEY));
        System.out.println("test get entity third " + map.getEntity(THIRD_KEY));
        System.out.println("test contains " + testElements[INDEX_ELEMENT_CONTAINS] + " " +
                map.contains(testElements[INDEX_ELEMENT_CONTAINS]));
        System.out.println("test set Entity " + entity);
        map.set(entity);
        System.out.println("keys :" + Arrays.toString(map.getKeys().toArray()));
        System.out.println("remove first : " + map.remove(FIRST_KEY));
        System.out.println("keys :" + Arrays.toString(map.getKeys().toArray()));
        System.out.println("test clear : number elements = " + map.clear());
        System.out.println(TEST_DELIMITER);
        System.out.println();
    }
}
