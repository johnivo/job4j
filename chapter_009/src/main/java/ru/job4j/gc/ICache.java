package ru.job4j.gc;

/**
 * Описывает получение данных из кеша.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 04.09.2019
 */
public interface ICache {

    String getData(String key);

}
