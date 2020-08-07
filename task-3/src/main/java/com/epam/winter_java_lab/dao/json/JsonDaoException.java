package com.epam.winter_java_lab.dao.json;

import com.epam.winter_java_lab.dao.DaoException;

public class JsonDaoException extends DaoException {
    public JsonDaoException() {
    }

    public JsonDaoException(String message) {
        super(message);
    }

    public JsonDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
