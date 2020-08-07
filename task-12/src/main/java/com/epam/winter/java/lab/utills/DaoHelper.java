package com.epam.winter.java.lab.utills;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class DaoHelper {

    public static LocalDate convertDate(Date date){
        final String DEFAULT_DATE = "2000-01-01";
        return  Optional.ofNullable(date)
                .orElseGet(()->Date.valueOf(DEFAULT_DATE))
                .toLocalDate();
    }
}
