package com.epam.winter_java_lab.entities.wraper.json;

import com.epam.winter_java_lab.entities.Transaction;

import java.util.List;

public class BankBranch {
    private List<Transaction> transactions;

    public BankBranch(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankBranch{" +
                "transactions=" + transactions +
                '}';
    }
}
