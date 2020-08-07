package com.epam.winter.java.lab.service.user;

import com.epam.winter.java.lab.model.User;
import com.epam.winter.java.lab.service.IService;

public interface UserService extends IService<User> {

    User getUserByCardId(Integer cardId);

    void delete(Integer userId);
}
