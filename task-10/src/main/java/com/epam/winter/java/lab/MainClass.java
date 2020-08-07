package com.epam.winter.java.lab;

import com.epam.winter.java.lab.model.User;
import com.epam.winter.java.lab.services.BookService;
import com.epam.winter.java.lab.services.UserService;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        User testUser = UserService.getById(3);
        System.out.println("user getByID = 3 : " + testUser);
        System.out.println();

        UserService.save(new User(null, "George ","Kalen","st.SlowWater", "233233323"));
        System.out.println("save User : new User(null, \"George \",\"Kalen\",\"st.SlowWater\", \"233233323\")");
        System.out.println("user list:");
        List<User> users = UserService.getAll();
        users.forEach(System.out::println);
        System.out.println();
        UserService.update(new User(3, "Arya","Stark","Winterfell", "7777777"));
        System.out.println("update Arya add phone 7777777");
         UserService.getAll().forEach(System.out::println);
        System.out.println();
        System.out.println("delete User :George ,Kalen,st.SlowWater, 233233323");
        UserService.delete(new User(11, "George ","Kalen","st.SlowWater", "233233323"));
        UserService.getAll().forEach(System.out::println);
        System.out.println();
        System.out.println("test join :");
        List<String> booksLibrary = BookService.getBooksLibrary();
        booksLibrary.stream().forEach(System.out::println);
    }
}

