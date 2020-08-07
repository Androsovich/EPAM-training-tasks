package com.epam.winter.java.lab.dao.card;

import com.epam.winter.java.lab.dao.DaoException;

public class CardDaoException extends DaoException {
    public CardDaoException() {
    }

    public CardDaoException(String message) {
        super(message);
    }

    public CardDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardDaoException(Throwable cause) {
        super(cause);
    }
}
