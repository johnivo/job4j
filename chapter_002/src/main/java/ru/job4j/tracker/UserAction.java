package ru.job4j.tracker;

/**
 * Interface UserAction.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 08.01.2019
 */

public interface UserAction {

    /**
     * Метод возвращает ключ опции.
     * @return ключ.
     */
    int key();

    /**
     * Основной метод.
     * @param input объект типа Input.
     * @param tracker объект типа Tracker.
     */
    void execute(Input input, ITracker tracker);

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню.
     */
    String info();
}
