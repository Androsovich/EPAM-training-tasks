package com.epam.winter_java_lab.entities.enums;

import java.time.LocalDate;

public enum Periods {
    DAY() {
        @Override
        public LocalDate getNextDate(LocalDate date) {
            return date.plusDays(RANGE);
        }

        @Override
        public LocalDate getPrevDate(LocalDate date) {
            return date.minusDays(RANGE);
        }
    }, WEEK() {
        @Override
        public LocalDate getNextDate(LocalDate date) {
            return date.plusWeeks(RANGE);
        }

        @Override
        public LocalDate getPrevDate(LocalDate date) {
            return date.minusWeeks(RANGE);
        }
    }, MONTH() {
        @Override
        public LocalDate getNextDate(LocalDate date) {
            return date.plusMonths(RANGE);
        }

        @Override
        public LocalDate getPrevDate(LocalDate date) {
            return date.minusMonths(RANGE);
        }
    }, YEAR() {
        @Override
        public LocalDate getNextDate(LocalDate date) {
            return date.plusYears(RANGE);
        }

        @Override
        public LocalDate getPrevDate(LocalDate date) {
            return date.minusYears(RANGE);
        }
    };

    private final static long RANGE = 1;

    public abstract LocalDate getNextDate(LocalDate date);

    public abstract LocalDate getPrevDate(LocalDate date);

}
