package com.epam.winter_java_lab.services;

import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.Transaction;
import com.epam.winter_java_lab.entities.enums.Periods;
import com.epam.winter_java_lab.entities.wraper.json.Setting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalculateCredit {
    private final Credit credit;
    private final BigDecimal percent;
    private final Periods period;
    private final LocalDate creditDate;
    private final Setting setting;
    private List<Transaction> transactions;
    private long transactionCounter;


    public CalculateCredit(Credit credit, Setting setting, List<Transaction> transactions) {
        final BigDecimal ONE_HUNDRED = new BigDecimal(100);

        this.credit = credit;
        this.period = credit.getPeriods();
        this.percent = credit.getRate().divide(ONE_HUNDRED, 3, RoundingMode.CEILING);
        this.creditDate = credit.getDate();
        this.setting = setting;
        this.transactions = transactions;
        this.transactionCounter = 0;
    }

    public void calculate() {
        LocalDate dateTo = Optional.ofNullable(setting.getDateTo())
                .orElseGet(() -> transactions.stream().map(Transaction::getDate).max(LocalDate::compareTo).get());

        List<LocalDate> iterationDate = new ArrayList<>();
        for (LocalDate date = creditDate; date.isBefore(dateTo); date = period.getNextDate(date)) {// true
            iterationDate.add(date);
        }
        Optional<LocalDate> repaymentDate;
        for (LocalDate date : iterationDate) {
            List<Transaction> validTransactions = getValidTransactions(date, transactions);
            repaymentDate = calculateCredit(credit, validTransactions);
            if (repaymentDate.isPresent()) {
                credit.setRepaymentDate(repaymentDate.get());
                credit.setTransactionCounter(transactionCounter);
                break;
            }
            calculatePercent(date);
        }
    }

    private void calculatePercent(final LocalDate date) {
        if ((credit.getMoney().compareTo(BigDecimal.ZERO) > 0)) {
            if (!date.isEqual(creditDate)) {
                BigDecimal result = credit.getMoney().multiply(percent).add(credit.getMoney());
                credit.setMoney(result.setScale(2, RoundingMode.CEILING));
            }
        }
    }

    private Optional<LocalDate> calculateCredit(Credit credit, List<Transaction> transactions) {
        return transactions.stream()
                .filter(x -> subtractAndSave(x).compareTo(BigDecimal.ZERO) <= 0)
                .map(Transaction::getDate)
                .findFirst();
    }

    private List<Transaction> getValidTransactions(LocalDate date, List<Transaction> transactions) {
        return transactions.stream()
                .filter(x -> creditDate.isBefore(x.getDate()))
                .filter(x -> isDateInPeriod(date, x.getDate()))
                .collect(Collectors.toList());
    }

    private BigDecimal subtractAndSave(Transaction transaction) {
        BigDecimal result = credit.getMoney().subtract(transaction.getMoney());
        credit.setMoney(result);
        transactionCounter++;
        return result;
    }

    private boolean isDateInPeriod(LocalDate date, LocalDate checkDate) {
        return date.isEqual(checkDate) || date.isAfter(checkDate) && period.getPrevDate(date).isBefore(checkDate);
    }
}
