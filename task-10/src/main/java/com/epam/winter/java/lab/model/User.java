package com.epam.winter.java.lab.model;

import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String surName;
    private String address;
    private String phone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surName, user.surName) &&
                Objects.equals(address, user.address) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, address, phone);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + surName + "," + address + "," + phone;
    }
}
