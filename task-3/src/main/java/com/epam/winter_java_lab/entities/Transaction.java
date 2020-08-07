package com.epam.winter_java_lab.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private long id;
    private LocalDate date;
    private long creditId;
    private BigDecimal money;

    public Transaction(long id, LocalDate date, long creditId, BigDecimal money) {
        this.id = id;
        this.date = date;
        this.creditId = creditId;
        this.money = money;
    }

    public Transaction() {
        this(0, null, 0, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", creditId=" + creditId +
                ", money=" + money +
                '}';
    }
}
