package com.epam.winter.java.lab.constants;

import com.epam.winter.java.lab.entities.Sex;
import com.epam.winter.java.lab.entities.User;

public class TestDate {
    public final static int REMOVE_INDEX_ELEMENT = 0;
    public final static int AFTER_INDEX_ELEMENT = 1;
    public final static int BEFORE_INDEX_ELEMENT = 2;
    public final static int INDEX_ELEMENT_ADD_AFTER = 3;
    public final static int INDEX_ELEMENT_ADD_BEFORE = 4;
    public final static int FIRST_ELEMENT_VALUE = 0;
    public final static int SECOND_ELEMENT_VALUE = 1;
    public final static int THIRD_ELEMENT_VALUE = 2;
    public final static int INDEX_ELEMENT_CONTAINS = 2;
    public final static int INDEX_ELEMENT_OTHER = 4;
    public final static String TEST_DELIMITER = "-------------------------------";

    public final static User[] USERS = {
            null,
            new User("Peter", "uiii3@gmail.com", 27, Sex.MALE),
            new User("Mila", "zxz56@gmail.com", 23, Sex.FEMALE),
            new User("Mark", "222232sass@gmail.com", 24, Sex.MALE),
            null
    };
    public final static User[] USERS_FOR_MAP = {
            new User("Peter", "uiii3@gmail.com", 27, Sex.MALE),
            new User("Mila", "zxz56@gmail.com", 23, Sex.FEMALE),
            new User("Mark", "222232sass@gmail.com", 24, Sex.MALE),

    };
    public final static User[] OTHER_USERS = {
            null,
            new User("Peter", "uiii3@gmail.com", 27, Sex.MALE),
            new User("Mila", "zxz56@gmail.com", 23, Sex.FEMALE),
            new User("Miranda", "wqwsass@gmail.com", 32, Sex.FEMALE),
            new User("Den", "222sfggs@gmail.com", 45, Sex.FEMALE),
            null,
            null
    };
    public final static User[] TEST_USERS = {
            new User("Mila", "zxz56@gmail.com", 23, Sex.FEMALE),
            new User("Miranda", "wqwsass@gmail.com", 32, Sex.FEMALE),
            new User("Peter", "uiii3@gmail.com", 27, Sex.MALE),
            new User("Mirian", "wqs@gmail.com", 23, Sex.FEMALE),
            new User("Nick", "33s@gmail.com", 3, Sex.MALE)
    };

    //    public final static int REMOVE_INDEX_ELEMENT = 0;
//    public final static int AFTER_INDEX_ELEMENT = 1;
//    public final static int BEFORE_INDEX_ELEMENT = 2;
//    public final static int INDEX_ELEMENT_ADD_AFTER = 3;
//    public final static int INDEX_ELEMENT_ADD_BEFORE = 4;
    public final static Integer[] NUMBERS = {4, -22, null, 1225, 400, null};
    public final static Integer[] OTHER_NUMBERS = {4, 10, 15, 400};
    public final static Integer[] TEST_NUMBERS = {4, 400, 1225, 550, 700};
    public final static Integer[] NUMBERS_FOR_STACK = {-45, 55, 2, 32, 5};
    public final static Integer[] TEST_NUMBERS_STACK = {-45, 55, 32, 400, 10, 400};
    public final static Integer[] NUMBERS_FOR_QUEUE = {-45, 335, -22, 322, 51};
    public final static Integer[] TEST_NUMBERS_QUEUE = {335, -45, 51, 400, 10, 322};

    public final static Integer[] TEST_NUMBERS_MAP = {44, 67, -25, -14, 700};
    public final static Integer[] NUMBERS_FOR_MAP = {45, -25, 400};

}
