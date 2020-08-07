package com.epam.winter.java.lab.dao.book;

import com.epam.winter.java.lab.dao.AbstractDao;
import com.epam.winter.java.lab.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    private final String COLUMN_ID_BOOK = "idbook";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_ID_AUTHOR = "idauthor";
    private final String COLUMN_PUBLICATION_DATE = "publicationdate";
    private final String COLUMN_AMOUNT = "amount";

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Book getBookByDeliveryId(Integer idDelivery) {
        final String SQL_BOOK_BY_DELIVERY = "select * from books where idbook = (select idbook " +
                "from extradition where idextradition = ?)";
        return this.jdbcTemplate.queryForObject(SQL_BOOK_BY_DELIVERY, new Object[]{idDelivery}, getRowMapper());
    }

    @Override
    public Book getById(Integer id) {
        final String SQL_BOOK_BY_ID = "select * from books where idbook = ?";
        return this.jdbcTemplate.queryForObject(SQL_BOOK_BY_ID, new Object[]{id}, getRowMapper());
    }

    @Override
    public int save(Book book) {
        final String TABLE_NAME = "books";
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(COLUMN_ID_BOOK);
        return simpleJdbcInsert.executeAndReturnKey(getParameters(book)).intValue();
    }

    @Override
    public void update(Book book) {
        final String SQL_UPDATE_BOOK = "update books SET name = ?, idauthor = ?, " +
                "publicationdate = ?, amount = ? where idbook = ?";
        this.jdbcTemplate.update(SQL_UPDATE_BOOK, book.getName(), book.getAuthorId(),
                book.getPublicationDate(), book.getAmount(), book.getId());
    }

    @Override
    public void delete(Book book) {
        final String SQL_DELETE_BOOK = "delete from books where idbook = ?";
        this.jdbcTemplate.update(SQL_DELETE_BOOK, book.getId());
    }

    @Override
    public List<Book> getAll() {
        final String SQL_BOOKS_GET = "select * from books";
        return this.jdbcTemplate.query(SQL_BOOKS_GET, getRowMapper());
    }

    @Override
    public List<Book> getBooksAuthor(Integer idAuthor) {
        String SQL_BOOKS_AUTHOR = "select * from books where idauthor = ?";
        return jdbcTemplate.query(SQL_BOOKS_AUTHOR, new Object[]{idAuthor}, getRowMapper());
    }

    @Override
    public Book initObject(ResultSet rs) {
        Book book;
        try {
            book = new Book();
            book.setId(rs.getInt(COLUMN_ID_BOOK));
            book.setName(rs.getString(COLUMN_NAME));
            book.setAuthorId(rs.getInt(COLUMN_ID_AUTHOR));
            book.setPublicationDate(rs.getDate(COLUMN_PUBLICATION_DATE).toLocalDate());
            book.setAmount(rs.getInt(COLUMN_AMOUNT));
        } catch (SQLException e) {
            throw new BookDaoException(e);
        }
        return book;
    }

    @Override
    public Map<String, Object> getParameters(Book book) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_NAME, book.getName());
        parameters.put(COLUMN_ID_AUTHOR, book.getAuthorId());
        parameters.put(COLUMN_PUBLICATION_DATE, book.getPublicationDate());
        parameters.put(COLUMN_AMOUNT, book.getAmount());
        return parameters;
    }
}
