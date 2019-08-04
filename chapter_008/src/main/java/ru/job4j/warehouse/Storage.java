package ru.job4j.warehouse;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
     * Проверяет, подходит ли продукт в данный момент для данного склада.
     * @param food продукт.
     * @param current текущая дата.
     * @return true or false.
     */
    boolean checkExpiration(Food food, LocalDateTime current);

    /**
     * Определяет временной интервал между двумя датами.
     * @param first первая дата
     * @param second вторая дата.
     * @return diff временной интервал в днях.
     */
    static long duration(LocalDateTime first, LocalDateTime second) {
        Duration dur = Duration.between(first, second);
        return dur.toMillis();
    }

    /**
     * Возвращает список продуктов на складе.
     */
    List<Food> getList();

    /**
     * Добавляет продукт.
     */
    void add(Food food);

}
