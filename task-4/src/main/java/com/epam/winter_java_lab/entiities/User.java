package com.epam.winter_java_lab.entiities;

import com.epam.winter_java_lab.entiities.order.Pizza;
import com.epam.winter_java_lab.entiities.order.Soup;
import com.epam.winter_java_lab.entiities.order.interfaces.Creator;
import com.epam.winter_java_lab.entiities.order.interfaces.Order;

import java.time.LocalDate;
import java.util.Objects;

public class User implements Creator {
    private long id;
    private String name;
    private String secondName;
    private LocalDate birthDay;
    private String numberPhone;
    private boolean isVegetarian;
    private Order order;

    private User() {
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

    public String getSecondName() {
        return secondName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static User.UserBuilder newBuilder() {
        return new User().new UserBuilder();
    }

    //factory method
    @Override
    public Order createOrder() {
        return isVegetarian ? new Soup() : new Pizza();
    }

    public class UserBuilder {

        public UserBuilder setId(long id) {
            User.this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public UserBuilder setSecondName(String secondName) {
            User.this.secondName = secondName;
            return this;
        }

        public UserBuilder setBirthDay(LocalDate birthDay) {
            User.this.birthDay = birthDay;
            return this;
        }

        public UserBuilder setNumberPhone(String numberPhone) {
            User.this.numberPhone = numberPhone;
            return this;
        }

        public UserBuilder setVegetarian(boolean vegetarian) {
            User.this.isVegetarian = vegetarian;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isVegetarian == user.isVegetarian &&
                Objects.equals(name, user.name) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(birthDay, user.birthDay) &&
                Objects.equals(numberPhone, user.numberPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, birthDay, numberPhone, isVegetarian);
    }

    @Override
    public String toString() {
        return "User [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDay=" + birthDay +
                ", numberPhone='" + numberPhone + '\'' +
                ", isVegetarian=" + isVegetarian +
                ']';
    }
}
