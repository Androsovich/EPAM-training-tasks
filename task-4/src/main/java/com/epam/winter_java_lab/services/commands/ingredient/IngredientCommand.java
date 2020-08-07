package com.epam.winter_java_lab.services.commands.ingredient;

import com.epam.winter_java_lab.entiities.Cafe;
import com.epam.winter_java_lab.entiities.User;
import com.epam.winter_java_lab.entiities.order.interfaces.Order;
import com.epam.winter_java_lab.services.commands.Command;

public abstract class IngredientCommand extends Command {
    private User user;

    public IngredientCommand(Cafe cafe, User user) {
        super(cafe);
        this.user = user;
    }

    protected User getUser() {
        return user;
    }

    @Override
    public void execute() {
        User user = getUser();
        Order order = user.getOrder();
        addIngredient(order, user);
    }

    public abstract void addIngredient(Order order, User user);
}
