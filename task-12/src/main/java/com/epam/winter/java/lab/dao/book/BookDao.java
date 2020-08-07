package com.epam.winter.java.lab.dao.book;

import com.epam.winter.java.lab.dao.IDao;
import com.epam.winter.java.lab.model.Book;

import java.util.List;

public interface BookDao extends IDao<Book> {

    List<Book> getBooksAuthor(Integer idAuthor);

    Book getBookByDeliveryId(Integer idDelivery);
}