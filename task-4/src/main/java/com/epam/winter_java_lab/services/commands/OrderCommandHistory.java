package com.epam.winter_java_lab.services.commands;

import com.epam.winter_java_lab.entiities.User;

import java.util.*;

public class OrderCommandHistory {
    private Map<User, Map<String, Command>> history = new HashMap<>();

    public void put(User user, Map<String, Command> command) {
        history.putIfAbsent(user, command);
    }

    public Map<String, Command> get(User user) {
        return history.get(user);
    }

    public void removeCommands(User user) {
        history.remove(user);
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
