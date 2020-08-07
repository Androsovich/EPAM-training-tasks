package com.epam.winter.java.lab.service.user;

import com.epam.winter.java.lab.dao.user.UserDao;
import com.epam.winter.java.lab.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao dao;

    @Resource
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public int save(User instance) {
        return dao.save(instance);
    }

    @Override
    public void update(User instance) {
        dao.update(instance);
    }

    @Override
    public void delete(Integer userId) {
        dao.delete(userId);
    }

    @Override
    public void delete(User instance) {
        delete(instance.getId());
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public User getUserByCardId(Integer cardId) {
        return dao.getUserByCardId(cardId);
    }
}
