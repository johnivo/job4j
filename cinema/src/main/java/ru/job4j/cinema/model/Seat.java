package ru.job4j.cinema.model;

import java.util.Objects;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.02.2020
 */
public class Seat implements Comparable<Seat> {

    private int row;
    private int place;
    private int price;
    private boolean booked;

    public Seat(int row, int place, int price, boolean booked) {
        this.row = row;
        this.place = place;
        this.price = price;
        this.booked = booked;
    }

    public Seat() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seat seat = (Seat) o;
        return row == seat.row
                && place == seat.place
                && price == seat.price
                && booked == seat.booked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, place, price, booked);
    }

    @Override
    public String toString() {
        return String.format("Seat{ row=%d, place=%d, price=%d, booked=%s }",
                row, place, price, booked);
    }

    @Override
    public int compareTo(Seat o) {
        int rs = Integer.compare(this.row, o.row);
        return rs != 0 ? rs : Integer.compare(this.place, o.place);
    }
}
