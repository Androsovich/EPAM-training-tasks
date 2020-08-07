package com.epam.winter.java.lab.entities;

import java.util.Objects;

public class User implements Comparable<User> {
    private String name;
    private String email;
    private Integer age;
    private Sex sex;

    public User(String name, String email, Integer age, Sex sex) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(age, user.age) &&
                sex == user.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, age, sex);
    }

    @Override
    public String toString() {
        final String DELIMITER = " , ";
        return "User{" + name + DELIMITER + email + DELIMITER + age + DELIMITER + sex + '}';
    }


    @Override
    public int compareTo(User o) {
        int result = this.name.compareTo(o.name);
        if(result == 0){
            result = this.email.compareTo(o.email);
        }
        if(result == 0){
            result = this.age.compareTo(o.age);
        }
        if(result == 0){
            result = this.sex.compareTo(o.sex);
        }
        return result;
    }
}
