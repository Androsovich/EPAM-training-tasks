package com.epam.winter.java.lab.service.author;

import com.epam.winter.java.lab.dao.author.AuthorDao;
import com.epam.winter.java.lab.model.Author;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
   private AuthorDao dao;

    @Resource
    public void setDao(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public Author getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public int save(Author instance) {
        return dao.save(instance);
    }

    @Override
    public void update(Author instance) {
        dao.update(instance);
    }

    @Override
    public void delete(Author instance) {
        dao.delete(instance);
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }
}
