package com.epam.winter_java_lab.entities;

import com.epam.winter_java_lab.entities.enums.Sex;

import java.time.LocalDate;

public class User {

    private long id;
    private String name;
    private String secondName;
    private Sex sex;
    private LocalDate birthday;


    public User(long id, String name, String secondName, Sex sex, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.sex = sex;
        this.birthday = birthday;
    }

    public User() {
        this(0, null, null, null, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getFullName() {
        return name + " " + secondName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                '}';
    }
}
