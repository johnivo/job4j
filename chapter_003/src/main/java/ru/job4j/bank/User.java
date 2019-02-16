package ru.job4j.bank;

import java.util.Objects;

/**
 * Class User.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 15.02.2019
 */
public class User {

    /**
     * Поля: имя, номер паспорта.
     */
    private String name;
    private String passport;

    /**
     * Конструктор.
     *
     * @param name имя.
     * @param passport номер паспорта.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Геттеры к полям.
     */
    public String getName() {
        return this.name;
    }

    public String getPassport() {
        return this.passport;
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
        return this.name.equals(user.name) && this.passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("name ")
                .append(name)
                .append(" passport ")
                .append(passport)
                .toString();
    }
}