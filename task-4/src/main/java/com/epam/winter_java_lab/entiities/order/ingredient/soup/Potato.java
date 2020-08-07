package com.epam.winter_java_lab.entiities.order.ingredient.soup;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class Potato implements Order {
    private Order order;

    public Potato(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        final String POTATO_DESCRIPTION = " : POTATO : ";
        return order.getDescription() + POTATO_DESCRIPTION;
    }

    @Override
    public long getCost() {
        final long POTATO_COST = 4;
        return order.getCost() + POTATO_COST;
    }
}
