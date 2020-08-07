package com.epam.winter.java.lab.services;

import com.epam.winter.java.lab.date.ResultCalculate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.epam.winter.java.lab.constants.Constants.*;

public class Printer {
    private final static String TITLE = "Result:";
    private final static String RESULT_INCORRECT = "%d. %s \n";


    public static void printResult(List<ResultCalculate> results) {
        final String RESULT = "%d.%3d steps;%4.2f result;%s \n";
        System.out.println(TITLE);
        for (int i = 0; i < results.size(); i++) {
            ResultCalculate result = results.get(i);
            final int step = result.getNumberSteps();
            final Double calculateResult = result.getResult();
            final String expression = result.getExpression();
            final Map<Integer, String> steps = result.getSteps();

            if (Objects.isNull(calculateResult)) {
                System.out.printf(RESULT_INCORRECT, i + 1, steps.get(INVALID_STEP));
            } else {
                System.out.printf(RESULT, i + 1, step, calculateResult, expression);
            }
        }
    }

    public static void printSteps(List<ResultCalculate> results, int step, int numberExpression) {
        String stepExpression = MESSAGE_EXCEPTION_VALUE;

        if (results.size() != numberExpression) {
            ResultCalculate result = results.get(numberExpression - 1);
            Map<Integer, String> steps = result.getSteps();
            stepExpression = steps.containsKey(INVALID_STEP) ? MESSAGE_EXCEPTION_EXPRESSION :
                    steps.getOrDefault(step, MESSAGE_EXCEPTION_VALUE);
        }
        System.out.println(TITLE);
        System.out.printf("%d. %s", numberExpression, stepExpression);
    }
}
