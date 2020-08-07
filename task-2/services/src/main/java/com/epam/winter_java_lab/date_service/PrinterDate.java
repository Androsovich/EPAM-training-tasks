package com.epam.winter_java_lab.date_service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterDate {    

    public static String formatDate(LocalDateTime date) {
        final String PATTERN_DATE = "d LLLL ss HH yyyy mm";		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        return date.format(formatter);
    }	
}