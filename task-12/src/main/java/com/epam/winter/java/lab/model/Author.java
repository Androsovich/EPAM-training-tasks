package com.epam.winter.java.lab.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Author {
    private Integer id;
    private String name;
    private String surName;
    private List<Book> books;

    public Author() {
        this(null, null, null);
    }

    public Author(Integer id, String name, String surName) {
        this.id = id;
        this.name = name;
        this.surName = surName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
