package com.epam.winter_java_lab;

import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.User;
import com.epam.winter_java_lab.entities.wraper.json.Setting;
import com.epam.winter_java_lab.services.BankService;
import com.epam.winter_java_lab.services.CalculateCredit;
import com.epam.winter_java_lab.services.Printer;
import com.epam.winter_java_lab.services.FilesService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.winter_java_lab.entities.constant.Constants.JSON_DB_FILE;
import static com.epam.winter_java_lab.entities.constant.Constants.JSON_DB_SETTING;

public class MainClass {
    public static void main(String[] args) {
        List<String> files = FilesService.readWorkDir(JSON_DB_FILE, JSON_DB_SETTING);
        Setting setting = FilesService.getSetting();
        List<String> resultFiles = FilesService.getFilesBySetting(setting, files);
        BankService.setSetting(setting);
        BankService.addTransactionsToDb(resultFiles);

        List<User> users = BankService.getUsers();
        System.out.println(BankService.getCredits(users));
        List<Credit> credits = BankService.getValidCredits(users);
        List<Credit> credits1 = BankService.getCredits(users);
        System.out.println(credits);
        credits = credits.stream()
                .peek(x -> {
                    CalculateCredit calculateCredit = new CalculateCredit(x, setting, BankService.getTransactions(x));
                    calculateCredit.calculate();
                }).collect(Collectors.toCollection(ArrayList::new));

        Printer.printResult(users, credits1, setting);

    }
}



