package com.epam.winter.java.lab.dao.author;

import com.epam.winter.java.lab.dao.AbstractDao;
import com.epam.winter.java.lab.dao.book.BookDao;
import com.epam.winter.java.lab.dao.book.BookDaoException;
import com.epam.winter.java.lab.model.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {
    private final String COLUMN_ID_AUTHOR = "idauthor";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_SURNAME = "surname";

    private BookDao bookDao;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Resource
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Author getById(Integer id) {
        final String SQL_AUTHOR_BY_ID = "select * from authors where idauthor = ?";
        RowMapper<Author> rowMapper = getRowMapper();
        return this.jdbcTemplate.queryForObject(SQL_AUTHOR_BY_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public int save(Author author) {
        final String TABLE_NAME = "authors";
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(COLUMN_ID_AUTHOR);
        return simpleJdbcInsert.executeAndReturnKey(getParameters(author)).intValue();
    }

    @Override
    public void update(Author author) {
        final String SQL_UPDATE_AUTHOR = "update authors SET name = ?, surname = ? where idauthor = ?";
        this.jdbcTemplate.update(SQL_UPDATE_AUTHOR, author.getName(), author.getSurName(), author.getId());
    }

    @Override
    public void delete(Author author) {
        final String SQL_DELETE_AUTHOR = "delete from authors where idauthor = ?";
        this.jdbcTemplate.update(SQL_DELETE_AUTHOR, author.getId());
    }

    @Override
    public List<Author> getAll() {
        final String SQL_AUTHORS_GET = "select * from authors";
        return this.jdbcTemplate.query(SQL_AUTHORS_GET, getRowMapper());
    }

    @Override
    public Author initObject(ResultSet rs) {
        Author author;
        try {
            author = new Author();
            author.setId(rs.getInt(COLUMN_ID_AUTHOR));
            author.setName(rs.getString(COLUMN_NAME));
            author.setSurName(rs.getString(COLUMN_SURNAME));

            final int id = author.getId();
            author.setBooks(bookDao.getBooksAuthor(id));
        } catch (SQLException e) {
            throw new BookDaoException(e);
        }
        return author;
    }

    @Override
    public Map<String, Object> getParameters(Author author) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_NAME, author.getName());
        parameters.put(COLUMN_SURNAME, author.getSurName());
        return parameters;
    }
}
