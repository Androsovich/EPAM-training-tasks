package com.epam.winter.java.lab.dao.book;

import com.epam.winter.java.lab.dao.Dao;
import com.epam.winter.java.lab.model.Book;

import java.util.List;
import java.util.Map;

public interface BookDao extends Dao<Book> {
   List<String> getBooksLibrary();
}
