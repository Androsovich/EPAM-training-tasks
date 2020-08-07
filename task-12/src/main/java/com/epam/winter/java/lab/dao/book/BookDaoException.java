package com.epam.winter.java.lab.dao.book;

import com.epam.winter.java.lab.dao.DaoException;

public class BookDaoException extends DaoException {
    public BookDaoException() {
    }

    public BookDaoException(String message) {
        super(message);
    }

    public BookDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDaoException(Throwable cause) {
        super(cause);
    }
}
