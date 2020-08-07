package com.epam.winter.java.lab.dao.user;

import com.epam.winter.java.lab.dao.DaoException;

public class UserDaoException extends DaoException {
    public UserDaoException() {
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(Throwable cause) {
        super(cause);
    }
}
