package com.epam.winter.java.lab.dao.delivery;

import com.epam.winter.java.lab.dao.DaoException;

public class DeliveryDaoException extends DaoException {
    public DeliveryDaoException() {
    }

    public DeliveryDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryDaoException(String message) {
        super(message);
    }

    public DeliveryDaoException(Throwable cause) {
        super(cause);
    }
}
