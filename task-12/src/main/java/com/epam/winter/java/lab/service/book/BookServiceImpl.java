package com.epam.winter.java.lab.service.book;

import com.epam.winter.java.lab.dao.book.BookDao;
import com.epam.winter.java.lab.model.Book;
import com.epam.winter.java.lab.service.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao dao;

    @Resource
    public void setDao(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Book getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public int save(Book instance) {
        return dao.save(instance);
    }

    @Override
    public void update(Book instance) {
        dao.update(instance);
    }

    @Override
    public void delete(Book instance) {
        dao.delete(instance);
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public Book getBookByDeliveryId(Integer idDelivery) {
        return dao.getBookByDeliveryId(idDelivery);
    }

    @Override
    public void reduceAmount(Integer bookId) {
        Book book = getAll().stream()
                .filter(i-> i.getId().equals(bookId))
                .filter(i-> i.getAmount() > 0)
                .findFirst()
                .orElseThrow(ServiceException::new);
        book.reduceAmount();
        update(book);
    }

    @Override
    public void increaseAmount(Integer bookId) {
        Book book = getById(bookId);
        book.increaseAmount();
        update(book);
    }
}
