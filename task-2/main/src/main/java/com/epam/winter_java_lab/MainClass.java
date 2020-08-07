package com.epam.winter_java_lab;

import java.time.LocalDateTime;
import static com.epam.winter_java_lab.date_service.PrinterDate.formatDate;





public class MainClass {
    public static void main(String[] args) {
        final long WEEKS = 1;
        final String GREETING = "Hello world:";
        final String GREETING_AFTER_WEEKS = "Hello world + 1 week:";

        LocalDateTime date = LocalDateTime.now();
        System.out.println(GREETING + formatDate(date));
        System.out.println(GREETING_AFTER_WEEKS + formatDate(date.plusWeeks(WEEKS)));
    }    
}
