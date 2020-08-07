package com.epam.winter_java_lab.services.commands;

import com.epam.winter_java_lab.entiities.Cafe;

public class CreateOrder extends Command {

    public CreateOrder(Cafe cafe) {
        super(cafe);
    }

    @Override
    public void execute() {
            getCafe().createOrder();
        }
}

