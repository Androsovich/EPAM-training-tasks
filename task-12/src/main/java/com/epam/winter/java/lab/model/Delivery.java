package com.epam.winter.java.lab.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class Delivery {
    private Integer id;
    private Integer cardId;
    private Integer bookId;
    private LocalDate dateExtradition;
    private LocalDate dateDelivery;

    public Delivery() {
        this(null, null, null, null, null);
    }

    public Delivery(Integer id, Integer cardId, Integer bookId, LocalDate dateExtradition, LocalDate dateDelivery) {
        this.id = id;
        this.cardId = cardId;
        this.bookId = bookId;
        this.dateExtradition = dateExtradition;
        this.dateDelivery = dateDelivery;
    }

    public Delivery(Integer cardId, Integer bookId, LocalDate dateExtradition, LocalDate dateDelivery) {
        this(null, cardId, bookId, dateExtradition, dateDelivery);
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return id.equals(delivery.id) &&
                Objects.equals(cardId, delivery.cardId) &&
                Objects.equals(bookId, delivery.bookId) &&
                Objects.equals(dateExtradition, delivery.dateExtradition) &&
                Objects.equals(dateDelivery, delivery.dateDelivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardId, bookId, dateExtradition, dateDelivery);
    }
}
