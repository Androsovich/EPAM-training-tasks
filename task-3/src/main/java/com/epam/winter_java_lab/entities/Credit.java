package com.epam.winter_java_lab.entities;

import com.epam.winter_java_lab.entities.enums.Periods;
import com.epam.winter_java_lab.entities.wraper.json.Setting;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Credit {

    private long id;
    private long userId;
    private LocalDate date;
    private Periods periods;
    private BigDecimal money;
    private BigDecimal rate;

    private transient LocalDate repaymentDate;
    private transient long transactionCounter;

    private Credit(long id, long userId, LocalDate date,
                   Periods period, BigDecimal money, BigDecimal rate) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.periods = period;
        this.money = money;
        this.rate = rate;
    }

    private Credit() {
        this(0, 0, null, null, null, null);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Periods getPeriods() {
        return periods;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public LocalDate getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(LocalDate repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public boolean isHaveDept() {
        return money.compareTo(BigDecimal.ZERO) > 0;
    }

    public static Credit.CreditBuilder newBuilder() {
        return new Credit().new CreditBuilder();
    }

    public long getCounter() {
        return transactionCounter;
    }

    public void setTransactionCounter(long transactionCounter) {
        this.transactionCounter = transactionCounter;
    }

    public class CreditBuilder {
        private CreditBuilder() {
        }

        public CreditBuilder setId(long id) {
            Credit.this.id = id;
            return this;
        }

        public CreditBuilder setUserId(long userId) {
            Credit.this.userId = userId;
            return this;
        }

        public CreditBuilder setDate(LocalDate date) {
            Credit.this.date = date;
            return this;
        }

        public CreditBuilder setPeriods(Periods periods) {
            Credit.this.periods = periods;
            return this;
        }

        public CreditBuilder setMoney(BigDecimal money) {
            Credit.this.money = money;
            return this;
        }

        public CreditBuilder setRate(BigDecimal rate) {
            Credit.this.rate = rate;
            return this;
        }

        public Credit build() {
            return Credit.this;
        }
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", periods=" + periods +
                ", money=" + money +
                ", rate=" + rate +
                '}';
    }

}
