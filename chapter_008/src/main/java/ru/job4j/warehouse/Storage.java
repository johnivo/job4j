package ru.job4j.warehouse;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.07.2019
 */
public interface Storage {

    /**
     * Нижняя граница.
     */
    double LOWER = 0.25;

    /**
     * Верхняя граница.
     */
    double UPPER = 0.75;

    /**
     * Дисконт.
     */
    double DISCOUNT = 0.50;

    /**
     * Метод будет проверять, подходит ли продукт в данный момент для данного склада.
     * @param food продукт.
     * @param current текущая дата.
     * @return true or false.
     */
    boolean checkDate(Food food, Date current);

    /**
     * Определяет временной интервал между двумя датами.
     * @param first первая дата
     * @param second вторая дата.
     * @return diff временной интервал в днях.
     */
    static long duration(Date first, Date second) {
        Instant one = first.toInstant();
        Instant two = second.toInstant();
        long diff = ChronoUnit.DAYS.between(one, two);
        //System.out.println("diff " + diff);
        return diff;
    }

}
