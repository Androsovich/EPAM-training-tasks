package com.epam.winter.java.lab.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private Integer id;
    private String name;
    private String surName;
    private String address;
    private String phone;

    public User() {
       this( null, null, null,null, null);
    }

    public User(Integer id, String name, String surName, String address, String phone) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
