package com.epam.winter_java_lab.services.commands.ingredient.pizza;

import com.epam.winter_java_lab.entiities.Cafe;
import com.epam.winter_java_lab.entiities.User;
import com.epam.winter_java_lab.entiities.order.ingredient.pizza.Bacon;
import com.epam.winter_java_lab.entiities.order.interfaces.Order;
import com.epam.winter_java_lab.services.commands.ingredient.IngredientCommand;

public class BaconCommand extends IngredientCommand {
    public BaconCommand(Cafe cafe, User user) {
        super(cafe, user);
    }

    @Override
    public void addIngredient(Order order, User user) {
        order = new Bacon(order);
        user.setOrder(order);
    }
}
