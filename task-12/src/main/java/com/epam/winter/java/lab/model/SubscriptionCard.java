package com.epam.winter.java.lab.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionCard {
    private Integer id;
    private Integer userId;
    private LocalDate createDate;

    public SubscriptionCard() {
        this(null, null, null);
    }

    public SubscriptionCard(Integer id, Integer userId, LocalDate createDate) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
    }

    public SubscriptionCard(Integer userId, LocalDate createDate) {
        this(null, userId, createDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
