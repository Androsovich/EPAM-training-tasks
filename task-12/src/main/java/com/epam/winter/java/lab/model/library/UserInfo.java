package com.epam.winter.java.lab.model.library;

import com.epam.winter.java.lab.model.Book;
import com.epam.winter.java.lab.model.Delivery;
import com.epam.winter.java.lab.model.User;

import java.util.Map;

public class UserInfo {
    private final User user;
    private final Integer cardId;
    private Map<Delivery, Book> deliveryBookMap;

    public UserInfo(User user, Integer cardId, Map<Delivery, Book> deliveryBookMap) {
        this.user = user;
        this.cardId = cardId;
        this.deliveryBookMap = deliveryBookMap;
    }

    public User getUser() {
        return user;
    }

    public Integer getCardId() {
        return cardId;
    }

    public Map<Delivery, Book> getDeliveryBookMap() {
        return deliveryBookMap;
    }
}
