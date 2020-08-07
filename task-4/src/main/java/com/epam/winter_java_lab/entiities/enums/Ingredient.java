package com.epam.winter_java_lab.entiities.enums;

import com.epam.winter_java_lab.entiities.Cafe;
import com.epam.winter_java_lab.entiities.User;
import com.epam.winter_java_lab.services.commands.Command;
import com.epam.winter_java_lab.services.commands.ingredient.pizza.*;
import com.epam.winter_java_lab.services.commands.ingredient.soup.BeanCommand;
import com.epam.winter_java_lab.services.commands.ingredient.soup.PotatoCommand;

import java.util.Arrays;
import java.util.List;

public enum Ingredient {
    POTATO{
        final String QUESTION_POTATO = "need potato(yes/no) : ";

        @Override
        public Command getCommand(Cafe cafe, User user) {
            return new PotatoCommand(cafe, user);
        }

        @Override
        public String getQuestion() {
            return QUESTION_POTATO;
        }
    },
    BEAN{
        final String QUESTION_BEAN = "need bean(yes/no) : ";

        @Override
        public Command getCommand(Cafe cafe, User user) {
            return new BeanCommand(cafe, user);
        }

        @Override
        public String getQuestion() {
            return QUESTION_BEAN;
        }
    },
    BACON {
        final String QUESTION_BACON = "need bacon(yes/no) : ";

        @Override
        public Command getCommand(Cafe cafe, User user) {
            return new BaconCommand(cafe, user);
        }

        @Override
        public String getQuestion() {
            return QUESTION_BACON;
        }
    },
    PEPPER {
        final String QUESTION_PEPPER = "need bell pepper(yes/no) : ";

        @Override
        public Command getCommand(Cafe cafe, User user) {
            return new BellPepperCommand(cafe, user);
        }

        @Override
        public String getQuestion() {
            return QUESTION_PEPPER;
        }
    },
    OLIVES {
        final String QUESTION_OLIVES = "need olives(yes/no) : ";

        @Override
        public Command getCommand(Cafe cafe, User user) {
            return new OlivesCommand(cafe, user);
        }

        @Override
        public String getQuestion() {
            return QUESTION_OLIVES;
        }
    },
    MUSHROOMS {
        final String QUESTION_MUSHROOMS = "need mushrooms(yes/no) : ";

        @Override
        public Command getCommand(Cafe cafe, User user) {
            return new MushroomsCommand(cafe, user);
        }

        @Override
        public String getQuestion() {
            return QUESTION_MUSHROOMS;
        }
    },
    PEPPERONI {
        final String QUESTION_PEPPERONI = "need pepperoni(yes/no) : ";

        @Override
        public Command getCommand(Cafe cafe, User user) {
            return new PepperoniCommand(cafe, user);
        }

        @Override
        public String getQuestion() {
            return QUESTION_PEPPERONI;
        }
    };

    public static List<Ingredient> checkUser(User user) {
        return !user.isVegetarian() ? Arrays.asList(BACON, PEPPER, OLIVES, MUSHROOMS, PEPPERONI)
                : Arrays.asList(POTATO, BEAN);
    }

    public abstract String getQuestion();

    public abstract Command getCommand(Cafe cafe, User user);


}
