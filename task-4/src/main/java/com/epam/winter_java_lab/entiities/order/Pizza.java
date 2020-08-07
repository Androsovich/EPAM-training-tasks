package com.epam.winter_java_lab.entiities.order;

import com.epam.winter_java_lab.entiities.order.interfaces.Order;

public class Pizza implements Order {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        final String description = "PIZZA ingredients : cheese : tomato : ";
        return description;
    }

    @Override
    public long getCost() {
        final long COST = 20;
        return COST;
    }
}
