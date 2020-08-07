package com.epam.winter_java_lab.entities.wraper.json;

import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.Transaction;
import com.epam.winter_java_lab.entities.User;

import java.util.List;

public class Bank {
    private List<User> users;
    private List<Credit> credits;
    private List<Transaction> transactions;

    public Bank(List<User> users, List<Credit> credits, List<Transaction> transactions) {
        this.users = users;
        this.credits = credits;
        this.transactions = transactions;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "users=" + users +
                ", credits=" + credits +
                ", transactions=" + transactions +
                '}';
    }
}
