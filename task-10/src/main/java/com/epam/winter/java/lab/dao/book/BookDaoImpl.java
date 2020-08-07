package com.epam.winter.java.lab.dao.book;

import com.epam.winter.java.lab.dao.AbstractJDBCDao;
import com.epam.winter.java.lab.dao.DaoException;
import com.epam.winter.java.lab.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.winter.java.lab.constants.Constants.*;
import static com.epam.winter.java.lab.dao.utills.ConnectorDb.getConnection;

public class BookDaoImpl extends AbstractJDBCDao implements BookDao {

    @SuppressWarnings("unchecked")
    @Override
    protected Book parseResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_BOOK_COLUMN);
        String name = resultSet.getString(NAME_BOOK_COLUMN);
        int authorId = resultSet.getInt(ID_AUTHOR_BOOK_COLUMN);
        LocalDate publicationDate = resultSet.getDate(PUBLICATION_DATE_COLUMN).toLocalDate();
        int amount = resultSet.getInt(AMOUNT_COLUMN);

        return new Book(id, name, authorId, publicationDate, amount);
    }

    @Override
    public Book getById(Integer id) {
        final String FIND_BY_ID = "select * from books where idbook = ?";
        return getById(FIND_BY_ID, id);
    }

    @Override
    public void save(Book book) {
        final String name = book.getName();
        final int authorId = book.getAuthorId();
        final LocalDate publicationDate = book.getPublicationDate();
        final int amount = book.getAmount();
        final String SQL_INSERT = "insert into books (name, authorid, publicationDate, amount ) values (?, ?, ?, ?)";
        executeUpdate(SQL_INSERT, name, authorId, publicationDate, amount);
    }

    @Override
    public void update(Book book) {
        final int id = book.getId();
        final String name = book.getName();
        final int authorId = book.getAuthorId();
        final LocalDate publicationDate = book.getPublicationDate();
        final int amount = book.getAmount();
        final String SQL_UPDATE = "update books set name = ?, authorid = ?, publicationDate = ?,amount = ?, where idbook = ?";
        executeUpdate(SQL_UPDATE, name, authorId, publicationDate, amount, id);
    }

    @Override
    public void delete(Book book) {
        final String SQL_DELETE = "delete from books where idbook = ?";
        executeUpdate(SQL_DELETE, book.getId());
    }

    @Override
    public List<Book> getAll() {
        final String SQL_FIND_ALL = "select * from books";
        return getAll(SQL_FIND_ALL);
    }

    @Override
    public List<String> getBooksLibrary() {
        final String SQL_INNER_JOIN = "SELECT books.idbook, books.name, authors.name, authors.surname " +
                "FROM books INNER JOIN authors ON books.idauthor=authors.idauthor";
        List<String> booksLibrary;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_INNER_JOIN);
            resultSet = statement.executeQuery();
            booksLibrary = new ArrayList<>();

            while (resultSet.next()) {
                final String id = String.format(FORMAT_ID, resultSet.getString(ID_JOIN_COLUMN));
                final String nameBook = String.format( FORMAT_NAME, resultSet.getString(NAME_JOIN_BOOK_COLUMN));
                final String authorName = String.format( FORMAT_NAME, resultSet.getString(NAME_JOIN_AUTHOR_COLUMN));
                final String authorSurName = String.format(FORMAT_SURNAME, resultSet.getString(SURNAME_JOIN_AUTHOR_COLUMN));

                String result = String.join(DELIMITER, id, nameBook, authorName, authorSurName);
                booksLibrary.add(result);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(connection, statement, resultSet);
        }
        return booksLibrary;
    }
}
