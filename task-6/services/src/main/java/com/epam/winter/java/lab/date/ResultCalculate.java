package com.epam.winter.java.lab.date;

import java.util.Map;
import java.util.Objects;

public class ResultCalculate {

    private final String expression;
    private Double result;
    private Map<Integer, String> steps;
    private int numberSteps;

    public ResultCalculate(String expression, Double result, Map<Integer, String> steps) {

        this.expression = expression;
        this.result = result;
        this.steps = steps;
    }

    public int getNumberSteps() {
        if (Objects.nonNull(result) && Objects.nonNull(steps)) {
            this.numberSteps = steps.size();
        }
        return this.numberSteps;
    }

    public void setSteps(Map<Integer, String> steps) {
        this.steps = steps;
    }

    public Map<Integer, String> getSteps() {
        return steps;
    }

    public String getExpression() {
        return expression;
    }

    public Double getResult() {
        return result;
    }
}
