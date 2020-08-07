package com.epam.winter.java.lab.services;

import com.epam.winter.java.lab.dao.user.UserDao;
import com.epam.winter.java.lab.dao.user.UserDaoImpl;
import com.epam.winter.java.lab.model.User;

import java.util.List;

public class UserService {
    private static final UserDao userDao = new UserDaoImpl();

    public static User getById(Integer id) {
        return userDao.getById(id);
    }

    public static void save(User user) {
        userDao.save(user);
    }

    public static void update(User user) {
        userDao.update(user);
    }

    public static void delete(User user) {
        userDao.delete(user);
    }

    public static List<User> getAll() {
        return userDao.getAll();
    }
}
