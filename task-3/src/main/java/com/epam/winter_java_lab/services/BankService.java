package com.epam.winter_java_lab.services;

import com.epam.winter_java_lab.dao.json.JsonBankBranchDao;
import com.epam.winter_java_lab.dao.json.JsonBankDao;
import com.epam.winter_java_lab.dao.json.JsonDao;
import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.Transaction;
import com.epam.winter_java_lab.entities.User;
import com.epam.winter_java_lab.entities.wraper.json.Bank;
import com.epam.winter_java_lab.entities.wraper.json.BankBranch;
import com.epam.winter_java_lab.entities.wraper.json.Setting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.winter_java_lab.entities.constant.Constants.ARRAY_TRANSACTIONS_NAME;
import static com.epam.winter_java_lab.entities.constant.Constants.JSON_DB_FILE;

public class BankService {
    private final static String TYPE_ID = "ID";
    private final static JsonDao<Bank> bankDao = new JsonBankDao(FilesService.getPath(JSON_DB_FILE));
    private static Setting setting;

    public static void setSetting(Setting setting) {
        BankService.setting = setting;
    }

    public static User getUserByCredit(Credit credit, List<User> users) {
        return users.stream()
                .filter(y -> credit.getUserId() == y.getId())
                .findFirst().orElse(new User());
    }

    public static void addTransactionsToDb(List<String> filesList) {
        filesList.stream().peek(file -> {
                    JsonDao<BankBranch> branchDao = new JsonBankBranchDao(FilesService.getPath(file));
                    List<Transaction> transactions = branchDao.parse().getTransactions();
                    bankDao.addListToJsonArray(ARRAY_TRANSACTIONS_NAME, transactions, Transaction.class);
                    branchDao.clean();
                }
        ).count();
    }

    public static List<User> getUsers() {
        Optional<String> showFor = Optional.ofNullable(setting.getShowFor());
        List<User> users = bankDao.parse().getUsers();
        if (showFor.isPresent()) {
            final String type = setting.getShowFor();
            users = users.stream()
                    .filter(s -> {
                                String test = type.equals(TYPE_ID) ? String.valueOf(s.getId()) : s.getFullName();
                                return setting.getShowForList().stream().anyMatch(x -> x.equals(test));
                            }
                    )
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return users;
    }

    public static List<Transaction> getTransactions(Credit credit) {
        final List<Transaction> transactions = bankDao.parse().getTransactions();
        return transactions.stream()
                .filter(transaction -> isValidDate(transaction.getDate(), setting))
                .filter(transaction -> Stream.of(credit)
                        .anyMatch(x -> x.getId() == transaction.getCreditId()))
                .collect(Collectors.toList());
    }

    public static List<Credit> getValidCredits(List<User> users) {
        return getCredits(users).stream()
                .filter(credit -> isValidDate(credit.getDate(), setting))
                .collect(Collectors.toList());
    }

    public static List<Credit> getCredits(List<User> users){
        List<Credit> credits = bankDao.parse().getCredits();
        return credits.stream()
                .filter(credit -> users
                        .stream()
                        .anyMatch(user -> credit.getUserId() == user.getId()))
                .collect(Collectors.toList());
    }

    public static boolean isValidDate(LocalDate date, Setting setting) {
        final LocalDate dateFrom = Optional.ofNullable(setting.getDateFrom()).orElse(LocalDate.MIN);
        final LocalDate dateTo = Optional.ofNullable(setting.getDateTo()).orElse(LocalDate.MAX);
        return (date.isAfter(dateFrom) && date.isBefore(dateTo));
    }

}



