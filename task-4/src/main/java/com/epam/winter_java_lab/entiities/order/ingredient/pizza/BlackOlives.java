package com.epam.winter_java_lab.entiities.order.ingredient.pizza;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class BlackOlives implements Order {
    private Order order;

    public BlackOlives(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        final String OLIVES_DESCRIPTION = " : Black Olives : ";
        return order.getDescription() + OLIVES_DESCRIPTION;
    }

    @Override
    public long getCost() {
        final long OLIVES_COST = 4;
        return order.getCost() + OLIVES_COST;
    }
}
