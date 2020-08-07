package com.epam.winter.java.lab;

import com.epam.winter.java.lab.collections.list.ArrayList;
import com.epam.winter.java.lab.collections.list.LinkedList;
import com.epam.winter.java.lab.collections.map.ArrayMap;
import com.epam.winter.java.lab.collections.map.ListMap;
import com.epam.winter.java.lab.collections.queue.ArrayQueue;
import com.epam.winter.java.lab.collections.queue.ListQueue;
import com.epam.winter.java.lab.collections.stack.ArrayStack;
import com.epam.winter.java.lab.collections.stack.ListStack;
import com.epam.winter.java.lab.service.TestService;

import static com.epam.winter.java.lab.constants.TestDate.*;

public class MainClass {


    public static void main(String[] args) {
        //test Integer
        TestService.testList(new ArrayList<>(), "test ArrayList<Integer>", NUMBERS, OTHER_NUMBERS, TEST_NUMBERS);
        TestService.testList(new LinkedList<>(), "test LinkedList<Integer>", NUMBERS, OTHER_NUMBERS, TEST_NUMBERS);

        TestService.testStack(new ArrayStack<>(), "test ArrayStack<Integer>", NUMBERS_FOR_STACK, TEST_NUMBERS_STACK);
        TestService.testStack(new ListStack<>(), "test ListStack<Integer>", NUMBERS_FOR_STACK, TEST_NUMBERS_STACK);

        TestService.testQueue(new ArrayQueue<>(), "test ArrayQueue<Integer>", NUMBERS_FOR_QUEUE, TEST_NUMBERS_QUEUE);
        TestService.testQueue(new ListQueue<>(), "test ListQueue<Integer>", NUMBERS_FOR_QUEUE, TEST_NUMBERS_QUEUE);

        TestService.testMap(new ArrayMap<>(), "test ArrayMap<Integer>", NUMBERS_FOR_MAP, TEST_NUMBERS_MAP);
        TestService.testMap(new ListMap<>(), "test ListMap<Integer>", NUMBERS_FOR_MAP, TEST_NUMBERS_MAP);

        //test Users
        TestService.testList(new ArrayList<>(), "test ArrayList<User> ", USERS, OTHER_USERS, TEST_USERS);
        TestService.testList(new LinkedList<>(), "test ArrayList<User> ", USERS, OTHER_USERS, TEST_USERS);

        TestService.testStack(new ArrayStack<>(), "test ArrayStack<User>", USERS, TEST_USERS);
        TestService.testStack(new ListStack<>(), "test ListStack<User>", USERS, TEST_USERS);

        TestService.testMap(new ArrayMap<>(), "test ArrayMap<String, User>", USERS_FOR_MAP, TEST_USERS);
        TestService.testMap(new ListMap<>(), "test ListMap<String, User>", USERS_FOR_MAP, TEST_USERS);

        TestService.testQueue(new ArrayQueue<>(), "test ArrayQueue<User>", USERS, TEST_USERS);
        TestService.testQueue(new ListQueue<>(), "test ListQueue<User>", USERS, TEST_USERS);

    }
}
