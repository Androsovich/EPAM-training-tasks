package com.epam.winter_java_lab.services.commands;

import com.epam.winter_java_lab.entiities.Cafe;

public class RestoreAndUpdateOrder extends Command {


    public RestoreAndUpdateOrder(Cafe cafe) {
        super(cafe);
    }

    @Override
    public void execute() {
        getCafe().restoreAndUpdateOrder();
    }
}

