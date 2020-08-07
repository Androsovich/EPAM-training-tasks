package com.epam.winter_java_lab.entiities.order.ingredient.pizza;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class Mushrooms implements Order {
    private Order order;

    public Mushrooms(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        final String MUSHROOMS_DESCRIPTION = " : Mushrooms : ";
        return order.getDescription() + MUSHROOMS_DESCRIPTION;
    }

    @Override
    public long getCost() {
        final long MUSHROOMS_COST = 3;
        return order.getCost() + MUSHROOMS_COST;
    }
}
