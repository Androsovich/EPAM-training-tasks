package com.epam.winter.java.lab.dao.author;

import com.epam.winter.java.lab.dao.DaoException;

public class AuthorDaoException extends DaoException {
    public AuthorDaoException() {
    }

    public AuthorDaoException(String message) {
        super(message);
    }

    public AuthorDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorDaoException(Throwable cause) {
        super(cause);
    }
}
