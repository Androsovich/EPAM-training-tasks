package com.epam.winter_java_lab.entiities.order.ingredient.soup;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class Bean implements Order {
    private Order order;

    public Bean(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        final String BEAN_DESCRIPTION = " : BEAN : ";
        return order.getDescription() + BEAN_DESCRIPTION;
    }

    @Override
    public long getCost() {
        final long BEAN_COST = 10;
        return order.getCost() + BEAN_COST;
    }
}
