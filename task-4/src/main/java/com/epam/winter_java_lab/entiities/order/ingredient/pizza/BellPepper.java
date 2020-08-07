package com.epam.winter_java_lab.entiities.order.ingredient.pizza;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class BellPepper implements Order {
    private Order order;

    public BellPepper(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        final String PEPPER_DESCRIPTION = " : bell pepper : ";
        return order.getDescription() + PEPPER_DESCRIPTION;
    }

    @Override
    public long getCost() {
        final long PEPPER_COST = 2;
        return order.getCost() + PEPPER_COST;
    }
}
