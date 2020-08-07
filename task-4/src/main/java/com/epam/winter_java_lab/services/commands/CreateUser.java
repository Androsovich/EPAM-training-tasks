package com.epam.winter_java_lab.services.commands;

import com.epam.winter_java_lab.entiities.Cafe;

public class CreateUser extends Command {

    public CreateUser(Cafe cafe) {
        super(cafe);
    }

    @Override
    public void execute() {
        Cafe cafe = getCafe();
        cafe.createUser();
    }

}
