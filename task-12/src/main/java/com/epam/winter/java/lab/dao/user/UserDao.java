package com.epam.winter.java.lab.dao.user;

import com.epam.winter.java.lab.dao.IDao;
import com.epam.winter.java.lab.model.User;

public interface UserDao extends IDao<User> {

    User getUserByCardId(Integer cardId);

    void delete(Integer userId);
}
