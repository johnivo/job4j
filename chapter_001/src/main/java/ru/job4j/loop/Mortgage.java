package ru.job4j.loop;

/**
 * Посчитать количество лет необходимых для погашения кредита.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.07.2019
 */
public class Mortgage {

    public int year(int amount, int monthly, double percent) {
        int year = 0;
        while (amount > 0) {
            amount = (int) (amount * (1 + percent / 100) - monthly * 12);
            year++;
        }
        return year;
    }
}
