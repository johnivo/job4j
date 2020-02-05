package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Seat;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.persistence.DBStore;
import ru.job4j.cinema.persistence.Store;

import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.02.2020
 */
public class ValidateService implements Validate {

    private static final Validate INSTANCE = new ValidateService();

    private final Store storage = DBStore.getInstance();

    public ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Seat> getSeats() {
        if (storage.getSeats().size() > 0) {
            return storage.getSeats();
        } else {
            throw new IllegalStateException("Fill in the hall data");
        }
    }

    @Override
    public void makePayment(Seat seat, User user) {
        Seat dbSeat = storage.getSeat(seat);
        if (dbSeat.getPrice() == seat.getPrice() && !dbSeat.isBooked()) {
            storage.makePayment(seat, user);
        } else {
            if (dbSeat.getPrice() != seat.getPrice()) {
                throw new IllegalStateException(String.format("Wrong price: curPrice=%d dbPrice=%d", seat.getPrice(), dbSeat.getPrice()));
            }
            if (dbSeat.isBooked()) {
                throw new IllegalStateException("This place is already booked");
            }
        }
    }

}
