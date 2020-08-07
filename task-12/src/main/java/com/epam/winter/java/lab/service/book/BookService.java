package com.epam.winter.java.lab.service.book;

import com.epam.winter.java.lab.model.Book;
import com.epam.winter.java.lab.service.IService;

public interface BookService extends IService<Book> {

    Book getBookByDeliveryId(Integer idDelivery);

    void increaseAmount(Integer bookId);

    void reduceAmount(Integer bookId);
}
