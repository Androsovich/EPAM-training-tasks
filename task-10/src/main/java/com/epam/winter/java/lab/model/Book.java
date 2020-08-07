package com.epam.winter.java.lab.model;

import java.time.LocalDate;

public class Book {
    private Integer id;
    private String name;
    private Integer authorId;
    private LocalDate publicationDate;
    private Integer amount;

    public Book(Integer id, String name, Integer authorId, LocalDate publicationDate, Integer amount) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
        this.amount = amount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + authorId + "," + publicationDate + "," + amount;

    }
}
