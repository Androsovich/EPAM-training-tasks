package com.epam.winter.java.lab.model;

import java.time.LocalDate;

public class Extradition {
    private Integer id;
    private Integer cardId;
    private Integer bookId;
    private LocalDate dateExtradition;
    private LocalDate dateDelivery;

    public Extradition(Integer id, Integer cardId, Integer bookId, LocalDate dateExtradition, LocalDate dateDelivery) {
        this.id = id;
        this.cardId = cardId;
        this.bookId = bookId;
        this.dateExtradition = dateExtradition;
        this.dateDelivery = dateDelivery;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public LocalDate getDateExtradition() {
        return dateExtradition;
    }

    public void setDateExtradition(LocalDate dateExtradition) {
        this.dateExtradition = dateExtradition;
    }

    public LocalDate getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(LocalDate dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    @Override
    public String toString() {
        return id + "," + cardId + "," + bookId + "," + dateExtradition + "," + dateDelivery;
    }
}
