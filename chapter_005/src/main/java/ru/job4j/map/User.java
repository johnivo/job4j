package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Модель "User".
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.04.2019
 */
public class User {

    /**
     * поля: имя, дети, дата рождения.
     */
    private String name;
    private int children;
    private Calendar birthday;

    /**
     * Конструктор.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Геттеры к полям.
     */
    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        //return children == user.children && name.equals(user.name) && birthday.equals(user.birthday);
        return children == user.children && Objects.equals(name, user.name) && birthday == user.birthday;
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + Integer.hashCode(this.children);
        result = 31 * result + this.birthday.hashCode();
        return result;
    }
}
