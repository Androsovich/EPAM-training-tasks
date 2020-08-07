package com.epam.winter_java_lab.entiities;

import com.epam.winter_java_lab.entiities.enums.Ingredient;
import com.epam.winter_java_lab.entiities.order.interfaces.Order;
import com.epam.winter_java_lab.services.commands.Command;
import com.epam.winter_java_lab.services.commands.OrderCommandHistory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cafe {
    private final List<User> users = new ArrayList<>();
    private final OrderCommandHistory history = new OrderCommandHistory();
    private final Scanner scanner = new Scanner(System.in);

    public OrderCommandHistory getHistory() {
        return history;
    }

    public List<User> getUsers() {
        return users;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public User selectUser() {
        final String SELECT_USER_MESSAGE = "please select id user from the list below and press enter";
        User user = null;
        if (!users.isEmpty()) {
            System.out.println(SELECT_USER_MESSAGE);
            users.forEach(System.out::println);
            long id;
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                user = users.stream().filter(x -> x.getId() == id).findFirst().orElse(User.newBuilder().build());
            }
        }
        return user;
    }

    public void createUser() {
        final String ID_REQUEST_MESSAGE = "Enter user id : ";
        final String NAME_REQUEST_MESSAGE = "Enter user name : ";
        final String SECOND_NAME_REQUEST_MESSAGE = "Enter user second name : ";
        final String BIRTHDAY_REQUEST_MESSAGE = "Enter user birthDay (format YYYY-MM-DD) : ";
        final String NUMBER_PHONE_REQUEST_MESSAGE = "Enter user number phone : ";
        final String IS_VEGAN_REQUEST_MESSAGE = " is user Vegetarian? (yes/no) : ";

        User user;
        User.UserBuilder builder = User.newBuilder();

        System.out.print(ID_REQUEST_MESSAGE);
        builder.setId(Long.parseLong(scanner.nextLine()));

        System.out.print(NAME_REQUEST_MESSAGE);
        builder.setName(scanner.nextLine());

        System.out.print(SECOND_NAME_REQUEST_MESSAGE);
        builder.setSecondName(scanner.nextLine());

        System.out.print(BIRTHDAY_REQUEST_MESSAGE);
        String birthDay = scanner.nextLine();
        LocalDate date = LocalDate.parse(birthDay, DateTimeFormatter.ISO_DATE);
        builder.setBirthDay(date);

        System.out.print(NUMBER_PHONE_REQUEST_MESSAGE);
        builder.setNumberPhone(scanner.nextLine());

        System.out.print(IS_VEGAN_REQUEST_MESSAGE);
        String isVegetarian = scanner.nextLine();
        builder.setVegetarian(parseResponse(isVegetarian));

        user = builder.build();
        if (Objects.nonNull(user)) {
            users.add(user);
        }
    }

    public void createOrder() {
        Map<String, Command> commands = new HashMap<>();
        User user = selectUser();
        Order order = user.createOrder();
        user.setOrder(order);
        history.put(user, commands);
        scanner.nextLine();
        List<Ingredient> ingredients = Ingredient.checkUser(user);
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getQuestion());
            if (scanner.hasNext()) {
                String string = scanner.nextLine();
                if (parseResponse(string)) {
                    commands.putIfAbsent(ingredient.toString(), ingredient.getCommand(this, user));
                }
            }
        }
    }

    public void restoreAndUpdateOrder() {
        final String EXIT = "exit";
        final String UPDATE_MESSAGE = "Select number to remove ingredient or input exit ";
        final String USER_NOT_AVAILABLE = "no available user";

        User user = selectUser();
        if (Objects.nonNull(user)) {
            String exitKey = null;
            do {
                System.out.println(user);
                System.out.println("order ingredients");
                List<String> ingredients = new ArrayList<>(history.get(user).keySet());
                for (int i = 0; i < ingredients.size(); i++) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(i)
                            .append(" ")
                            .append(ingredients.get(i));
                    System.out.println(builder.toString());
                }
                System.out.println(UPDATE_MESSAGE);
                if (scanner.hasNextInt()) {
                    int index = scanner.nextInt();
                    String ingredient = ingredients.get(index);
                    history.get(user).remove(ingredient);
                } else {
                    exitKey = scanner.nextLine();
                }
            } while (!EXIT.equals(exitKey));
        }else {
            System.out.println(USER_NOT_AVAILABLE);
        }
    }

    public boolean parseResponse(String response) {
        final String YES = "yes";
        return YES.equals(response);
    }

}
