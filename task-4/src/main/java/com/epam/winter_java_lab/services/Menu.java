package com.epam.winter_java_lab.services;

import com.epam.winter_java_lab.entiities.Cafe;
import com.epam.winter_java_lab.services.commands.*;

import java.util.*;

public class Menu {
    private static Cafe cafe = new Cafe();
    private static List<String> menuItemMessage = new ArrayList<>();
    private static Command[] commands = {
            new CreateUser(cafe), new CreateOrder(cafe),
            new RestoreAndUpdateOrder(cafe), new GetCheckOrder(cafe)
    };

    static {
        menuItemMessage.add("----------------------------------");
        menuItemMessage.add("choice number menu and press enter");
        menuItemMessage.add("----------------------------------");
        menuItemMessage.add("0. create user");
        menuItemMessage.add("1. create order");
        menuItemMessage.add("2. restore and update Order");
        menuItemMessage.add("3. print order");
        menuItemMessage.add("4. exit");
    }

    public static void startApp() {
        final int EXIT = 4;

        Scanner scanner = cafe.getScanner();

        while (true) {
            try {
                menuItemMessage.forEach(System.out::println);
                int menuNumber = Integer.
                        parseInt(scanner.nextLine());
                if (menuNumber == EXIT) break;
                commands[menuNumber].execute();

            } catch (NumberFormatException e) {
                System.out.println("Not a Number, scanner read garbage");
            }
        }
//
//                command.execute();
//            }
//            if (menuNumber == CREATE_ORDER) {
//                Command command = new CreateOrder(cafe);
//                command.execute();
//            }
//            if (menuNumber == RESTORE_ORDER) {
//                Command command = new RestoreAndUpdateOrder(cafe);
//                command.execute();
//            }
//            if (menuNumber == PRINT_ORDER) {
//                Command command = new PrintOrder(cafe);
//                command.execute();
//            }
//            scanner.nextLine();

//    }
    }
}

