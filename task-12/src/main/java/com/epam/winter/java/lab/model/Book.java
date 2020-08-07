package com.epam.winter.java.lab.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class Book {
    private Integer id;
    private String name;
    private Integer authorId;
    private LocalDate publicationDate;
    private Integer amount;

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public void reduceAmount() {
        amount--;
    }

    public void increaseAmount() {
        amount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(authorId, book.authorId) &&
                Objects.equals(publicationDate, book.publicationDate) &&
                Objects.equals(amount, book.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorId, publicationDate, amount);
    }
}
