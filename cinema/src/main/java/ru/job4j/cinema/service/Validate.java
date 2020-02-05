package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Seat;
import ru.job4j.cinema.model.User;

import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.02.2020
 */
public interface Validate {

    List<Seat> getSeats();

    void makePayment(Seat seat, User user);

}
