package com.epam.winter.java.lab.dao.user;

import com.epam.winter.java.lab.dao.AbstractJDBCDao;
import com.epam.winter.java.lab.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.winter.java.lab.constants.Constants.*;

public class UserDaoImpl extends AbstractJDBCDao implements UserDao {


    @Override
    public User getById(Integer id) {
        final String FIND_BY_ID = "select * from users where idUser = ?";
        return getById(FIND_BY_ID, id);
    }

    @Override
    public void save(User user) {
        final String name = user.getName();
        final String surName = user.getSurName();
        final String address = user.getAddress();
        final String phone = user.getPhone();
        final String SQL_INSERT = "insert into users (name, surname, adress, phone) values (?, ?, ?, ?)";
        executeUpdate(SQL_INSERT, name, surName, address, phone);

    }

    @Override
    public void update(User user) {
        final int id = user.getId();
        final String name = user.getName();
        final String surName = user.getSurName();
        final String address = user.getAddress();
        final String phone = user.getPhone();
        final String SQL_UPDATE = "update users set name = ?, surname = ?, adress = ?, phone = ? where idUser = ?";
        executeUpdate(SQL_UPDATE, name, surName , address,phone,id);
    }

    @Override
    public void delete(User user) {
        final String SQL_DELETE = "delete from users where idUser = ?";
        executeUpdate(SQL_DELETE, user.getId());
    }

    @Override
    public List<User> getAll() {
        final String SQL_FIND_ALL = "select * from users";
        return getAll(SQL_FIND_ALL);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected User parseResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_USER_COLUMN);
        String name = resultSet.getString(NAME_USER_COLUMN);
        String surName = resultSet.getString(SURNAME_USER_COLUMN);
        String address = resultSet.getString(ADDRESS_USER_COLUMN);
        String phone = resultSet.getString(PHONE_USER_COLUMN);

        return new User(id, name, surName, address, phone);
    }
}
