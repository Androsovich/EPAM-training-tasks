package com.epam.winter_java_lab.entiities.order.ingredient.pizza;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class Bacon implements Order {
    private Order order;

    public Bacon(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        final String BACON_DESCRIPTION = " : bacon : ";
        return order.getDescription() + BACON_DESCRIPTION;
    }

    @Override
    public long getCost() {
        final long BACON_COST = 10;
        return order.getCost() + BACON_COST;
    }
}
