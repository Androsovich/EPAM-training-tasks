package com.epam.winter.java.lab;

import com.epam.winter.java.lab.date.ResultCalculate;
import com.epam.winter.java.lab.services.Calculator;
import com.epam.winter.java.lab.services.FileService;
import com.epam.winter.java.lab.services.Menu;
import com.epam.winter.java.lab.services.Printer;

import java.io.FileNotFoundException;
import java.util.List;

import static com.epam.winter.java.lab.constants.Constants.MESSAGE_EXCEPTION_PATH;

public class MainClass
{
    public static void main( String[] args )
    {
        try {
            List<String> expressions = FileService.readFile(args[0]);
            for (String expression : expressions) {
                Calculator.calculate(expression);
            }
            List<ResultCalculate> results = Calculator.getResults();
            Printer.printResult(results);
            Menu.showStepsCalculate(results);
        } catch (FileNotFoundException e) {
            System.out.println(MESSAGE_EXCEPTION_PATH);
        }
    }
}
