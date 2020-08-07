package com.epam.winter_java_lab.services.commands;

import com.epam.winter_java_lab.entiities.Cafe;

public abstract class Command {

    private Cafe cafe;

    public Command(Cafe cafe) {
        this.cafe = cafe;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public abstract void execute();
}

