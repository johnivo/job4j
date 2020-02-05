package ru.job4j.cinema.model;

import java.util.Objects;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.02.2020
 */
public class User {

    private String username;
    private String phone;
    private Seat seat;

    public User(String username, String phone, Seat seat) {
        this.username = username;
        this.phone = phone;
        this.seat = seat;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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
        return username.equals(user.username)
                && phone.equals(user.phone)
                && seat.equals(user.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, phone, seat);
    }

    @Override
    public String toString() {
        return String.format("User{ username=%s, phone=%s, seat=%s }",
                username, phone, seat);
    }

}
