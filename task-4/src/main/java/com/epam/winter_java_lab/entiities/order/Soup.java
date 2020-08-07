package com.epam.winter_java_lab.entiities.order;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class Soup implements Order {
    private long cost;

    @Override
    public String getDescription() {
        final String description = "SOUP ingredients : pasta : tomato : ";
        return description;
    }

    @Override
    public long getCost() {
        final long COST = 8;
        return COST;
    }
}
