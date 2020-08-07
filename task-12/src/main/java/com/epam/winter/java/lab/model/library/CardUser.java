package com.epam.winter.java.lab.model.library;

import com.epam.winter.java.lab.model.SubscriptionCard;
import com.epam.winter.java.lab.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CardUser {
    private User user;
    private SubscriptionCard card;

    public CardUser(User user, SubscriptionCard card) {
        this.user = user;
        this.card = card;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubscriptionCard getCard() {
        return card;
    }

    public void setCard(SubscriptionCard card) {
        this.card = card;
    }
}
