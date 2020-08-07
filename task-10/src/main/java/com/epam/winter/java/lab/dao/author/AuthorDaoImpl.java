package com.epam.winter.java.lab.dao.author;

import com.epam.winter.java.lab.dao.AbstractJDBCDao;
import com.epam.winter.java.lab.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.winter.java.lab.constants.Constants.*;

public class AuthorDaoImpl extends AbstractJDBCDao implements AuthorDao {

    @SuppressWarnings("unchecked")
    @Override
    protected Author parseResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_AUTHOR_COLUMN);
        String name = resultSet.getString(NAME_AUTHOR_COLUMN);
        String surName = resultSet.getString(SURNAME_AUTHOR_COLUMN);

        return new Author(id, name, surName);
    }

    @Override
    public Author getById(Integer id) {
        final String FIND_BY_ID = "select * from authors where idAuthors = ?";
        return getById(FIND_BY_ID, id);
    }

    @Override
    public void save(Author authorId) {
        final String name = authorId.getName();
        final String surName = authorId.getSurName();
        final String SQL_INSERT = "insert into authors (name, surname) values (?, ?)";
        executeUpdate(SQL_INSERT, name, surName);
    }

    @Override
    public void update(Author authorId) {
        final int id = authorId.getId();
        final String name = authorId.getName();
        final String surName = authorId.getSurName();
        final String SQL_UPDATE = "update authors set name = ?, surName = ?, where idAuthors = ?";
        executeUpdate(SQL_UPDATE, name, surName, id);
    }

    @Override
    public void delete(Author authorId) {
        final String SQL_DELETE = "delete from authors where idAuthors = ?";
        executeUpdate(SQL_DELETE, authorId.getId());
    }

    @Override
    public List<Author> getAll() {
        final String SQL_FIND_ALL = "select * from authors";
        return getAll(SQL_FIND_ALL);
    }
}
