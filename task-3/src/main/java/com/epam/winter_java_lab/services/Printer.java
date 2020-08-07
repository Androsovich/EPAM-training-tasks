package com.epam.winter_java_lab.services;

import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.User;
import com.epam.winter_java_lab.entities.wraper.json.Setting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.winter_java_lab.services.BankService.getUserByCredit;
import static com.epam.winter_java_lab.services.BankService.isValidDate;

public class Printer {
    private final static String STRING_FORMAT = "%11s | %11s |%25s|%25s|%12s| %7s | %15s |";


    public static void printResult(List<User> users, List<Credit> credits, Setting setting) {
        final String NULL_FIELD = "null";
        credits = sortedCredits(credits, users, setting);
        printHeader();
        credits.stream()
                .filter(x -> isValidDate(x.getDate(), setting))
                .peek(x -> {
                            CalculateCredit calculateCredit = new CalculateCredit(x, setting, BankService.getTransactions(x));
                            calculateCredit.calculate();
                        })
                .forEach(x -> {
                    User user = getUserByCredit(x, users);
                    BigDecimal debt = !x.isHaveDept() ? BigDecimal.ZERO : x.getMoney();
                    System.out.printf(STRING_FORMAT, x.getId(), user.getId(), user.getFullName(), x.getCounter(),
                            debt, x.getPeriods(), formatStatusDept(x));
                    System.out.println();
                });
        credits.stream()
                .filter(x -> !isValidDate(x.getDate(), setting))
                .forEach((x -> {
                    User user = getUserByCredit(x, users);
                    System.out.printf(STRING_FORMAT, NULL_FIELD, NULL_FIELD, user.getFullName(), NULL_FIELD,
                            NULL_FIELD, NULL_FIELD, NULL_FIELD);
                    System.out.println();
                }));

    }

    private static String formatStatusDept(Credit credit) {
        final String DONE = "DONE";
        final String PROGRESS = "IN PROGRESS";
        return !credit.isHaveDept() ? DONE + "-" + credit.getRepaymentDate() : PROGRESS;
    }

    public static List<Credit> sortedCredits(List<Credit> credits, List<User> users, Setting setting) {
        final String NAME = "NAME";
        final String AGE = "AGE";
        final String SORT_BY = setting.getSortBy();
        List<Credit> creditList;
        if (SORT_BY.equals(NAME)) {
            creditList = credits.stream()
                    .sorted(Comparator.comparing(x -> getUserByCredit(x, users).getFullName()))
                    .collect(Collectors.toList());
        } else if (SORT_BY.equals(AGE)) {
            creditList = credits.stream()
                    .sorted(Comparator.comparing(x -> getUserByCredit(x, users).getBirthday().getYear()))
                    .collect(Collectors.toList());
        } else {
            creditList = credits.stream()
                    .sorted(Comparator.comparing(Credit::getMoney))
                    .collect(Collectors.toList());
        }
        return creditList;
    }

    private static void printHeader() {
        final String HEADER_CREDIT_ID = "ID Credit";
        final String HEADER_USER_ID = "ID User";
        final String HEADER_FULL_NAME = "Full Name";
        final String HEADER_NUMBER_TRANSACTION = "Number Transactions";
        final String HEADER_DEBT = "DEBT";
        final String HEADER_PERIOD = "Period";
        final String HEADER_STATUS_DEBT = "Status DEBT";

        System.out.printf(STRING_FORMAT, HEADER_CREDIT_ID, HEADER_USER_ID, HEADER_FULL_NAME, HEADER_NUMBER_TRANSACTION,
                HEADER_DEBT, HEADER_PERIOD, HEADER_STATUS_DEBT);
        System.out.println();
    }
}
