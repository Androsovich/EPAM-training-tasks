package com.epam.winter.java.lab.services;

import com.epam.winter.java.lab.dao.book.BookDao;
import com.epam.winter.java.lab.dao.book.BookDaoImpl;
import com.epam.winter.java.lab.model.Book;

import java.util.List;
import java.util.Map;

public class BookService {
    private static final BookDao bookDao = new BookDaoImpl();

    public static Book getById(Integer id) {
        return bookDao.getById(id);
    }

    public static void save(Book book) {
        bookDao.save(book);
    }

    public static void update(Book book) {
        bookDao.update(book);
    }

    public static void delete(Book book) {
        bookDao.delete(book);
    }

    public static List<Book> getAll() {
        return bookDao.getAll();
    }

    public static List<String> getBooksLibrary() {
        return bookDao.getBooksLibrary();
    }
}
