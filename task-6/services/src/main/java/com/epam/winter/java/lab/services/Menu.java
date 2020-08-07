package com.epam.winter.java.lab.services;

import com.epam.winter.java.lab.date.ResultCalculate;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void showStepsCalculate(List<ResultCalculate> results) {
        final int INDEX_EXPRESSION = 0;
        final int INDEX_STEP = 1;
        final String TITLE = "Input number expression and step";
        Scanner scanner = new Scanner(System.in);
        System.out.println(TITLE);

        if(scanner.hasNext()) {
            String[] inputDate = scanner.nextLine().split(" ");
            int numberExpression = Integer.parseInt(inputDate[INDEX_EXPRESSION]);
            int step = Integer.parseInt(inputDate[INDEX_STEP]);
            Printer.printSteps(results, step, numberExpression);
        }
        scanner.close();

    }
}
