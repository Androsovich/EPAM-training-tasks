package com.epam.winter.java.lab.services;

import com.epam.winter.java.lab.date.ResultCalculate;
import com.epam.winter.java.lab.enums.Operation;
import com.epam.winter.java.lab.exceptions.CalculateException;

import java.util.*;

import static com.epam.winter.java.lab.constants.Constants.*;

public class Calculator {
    private static final List<ResultCalculate> RESULT_CALCULATES = new ArrayList<>();

    private static List<String> parseExpression(String expression) {
        final String delimiters = "()+-*/";
        expression = expression.replaceAll(" ", "");
        final List<String> result = new ArrayList<>();
        final Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, delimiters, true);
        String token = "";
        while (tokenizer.hasMoreElements()) {
            token = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(token)) {
                throw new CalculateException(MESSAGE_EXCEPTION_EXPRESSION);
            }
            try {
                //проверяем на совпадение токен
                checkNumber(result, token);
                checkOperation(stack, result, token);
                checkLeftBracket(stack, token);
                checkRightBracket(stack, result, token);
                isIncorrectExpression(token);  //исключение если токен не совпал
            } catch (NoSuchElementException e) {
                // может выскакивать исключение(NoSuchElementsException) если унарные минусы или много операций подрят
                //так как это Incorrect expression отлавливаем и его
                throw new CalculateException(MESSAGE_EXCEPTION_EXPRESSION, e);
            }
        }
        while (!stack.isEmpty()) {     //  когда обработаны все токены (на входе больше не осталось)
            if (isOperator(stack.peek())) {
                result.add(stack.pop());
            } else {
                throw new CalculateException(MESSAGE_EXCEPTION_EXPRESSION);
            }
        }
        return result;
    }

    public static void calculate(String expression) {
        Double result = null;
        Map<Integer, String> stepsMap = new HashMap<>();
        try {
            int step = 0;
            List<String> tokens = parseExpression(expression);
            Deque<String> stack = new ArrayDeque<>();
            for (String token : tokens) {
                if (isNumber(token)) {
                    stack.push(token);
                } else if (isOperator(token)) {

                    // извлекаем 2 операнда, преобразуем в числа
                    double operand1 = Double.parseDouble(stack.pop());
                    double operand2 = Double.parseDouble(stack.pop());

                    Operation operation = Operation.getOperation(token);
                    Double resultOperation = operation.operation(operand2, operand1);
                    stack.push(String.valueOf(resultOperation));
                    //ложим в мапу значение шага и выражение на шаге
                    stepsMap.put(step, operation.operationToString());
                    step++;
                }
            }
            result = Double.parseDouble(stack.pop());
        } catch (CalculateException e) { // время жмет
            stepsMap.put(INVALID_STEP, MESSAGE_EXCEPTION_EXPRESSION);
        }
        ResultCalculate resultCalculate = new ResultCalculate(expression, result, stepsMap);
        RESULT_CALCULATES.add(resultCalculate);
    }

    public static List<ResultCalculate> getResults() {
        return RESULT_CALCULATES;
    }

    private static void checkNumber(List<String> result, String token) {
        if (isNumber(token)) result.add(token);
    }

    private static void checkOperation(Deque<String> stack, List<String> result, String token) {
        if (isOperator(token)) {
            //если стек не пустой проверяем приоретет токена и приоритет операции на вершине стека
            while (!stack.isEmpty() && (priority(token) <= priority(stack.peek()))) {
                result.add(stack.pop());
            }
            stack.push(token);              //ложим в стек нашу операцию
        }
    }

    private static void checkRightBracket(Deque<String> stack, List<String> result, String token) {
        if (isRightBracket(token)) {
            isStackEmpty(stack);                     //если  стек пустой exception
            while (!isLeftBracket(stack.peek())) {   // пока токен на вершине стека не открывающая скобка
                result.add(stack.pop());             // перекладываем пока стек не опустеет
                isStackEmpty(stack);             //если все таки нету и стек пустой exception
            }
            stack.pop(); // удалить открывающую скобку из стека без добавления
        }
    }

    private static void checkLeftBracket(Deque<String> stack, String token) {
        if (isLeftBracket(token)) stack.push(token);  //если отрывающая сразу ложим в стек
    }

    private static void isIncorrectExpression(String token) {
        if (!(isNumber(token) || isOperator(token)
                || isLeftBracket(token) || isRightBracket(token)))
            throw new CalculateException(MESSAGE_EXCEPTION_EXPRESSION);

    }

    private static void isStackEmpty(Deque<String> stack) {
        if (stack.isEmpty()) throw new CalculateException(MESSAGE_EXCEPTION_EXPRESSION);
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception exc) {
            return false;
        }
        return true;
    }

    private static boolean isOperator(String token) {
        for (String operation : OPERATORS) {
            if (token.equals(operation))
                return true;
        }
        return false;
    }

    private static boolean isLeftBracket(String token) {
        final String SIGN_LEFT_BRACKET = "(";
        return token.equals(SIGN_LEFT_BRACKET);
    }

    private static boolean isRightBracket(String token) {
        final String SIGN_RIGHT_BRACKET = ")";
        return token.equals(SIGN_RIGHT_BRACKET);
    }

    private static int priority(String token) {
        final int BRACKET_PRIORITY = 1;
        final int ADDITION_PRIORITY = 2;
        final int MULTIPLY_PRIORITY = 3;
        if (token.equals("(")) return BRACKET_PRIORITY;
        if (token.equals("+") || token.equals("-")) return ADDITION_PRIORITY;
        return MULTIPLY_PRIORITY;
    }
}


