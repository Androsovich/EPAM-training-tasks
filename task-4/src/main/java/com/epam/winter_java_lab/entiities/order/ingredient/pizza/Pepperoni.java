package com.epam.winter_java_lab.entiities.order.ingredient.pizza;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class Pepperoni implements Order {
    private Order order;

    public Pepperoni(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        final String PEPPERONI_DESCRIPTION = " : Pepperoni : ";
        return order.getDescription() + PEPPERONI_DESCRIPTION;
    }

    @Override
    public long getCost() {
        final long PEPPERONI_COST = 10;
        return order.getCost() + PEPPERONI_COST;
    }
}
