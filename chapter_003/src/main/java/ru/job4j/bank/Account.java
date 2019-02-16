package ru.job4j.bank;

import java.util.Objects;

/**
 * Class Account.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 15.02.2019
 */
public class Account {

    /**
     * value кол-во денег, реквизиты - номер банковского счета.
     */
    private double value;

    /**
     * requisites реквизиты - номер банковского счета.
     */
    private String requisites;

    /**
     * Конструктор.
     *
     * @param value кол-во денег.
     * @param requisites реквизиты счета.
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Геттеры к полям.
     */
    public double getValue() {
        return this.value;
    }

    public String getRequisites() {
        return this.requisites;
    }

    /**
     * Сеттер к полю value.
     */
    //public void setValue(double value) {
    //    this.value = value;
    //}

    public boolean transfer(Account src, Account dest, double amount) {
        boolean success = false;
        if (src != null && amount > 0 && amount <= src.getValue() && dest != null) {
            success = true;
            src.value -= amount;
            dest.value += amount;
        }
        return success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return this.requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Account{value=")
                .append(value)
                .append(", requisites=")
                .append(requisites)
                .append("}")
                .toString();
    }
}