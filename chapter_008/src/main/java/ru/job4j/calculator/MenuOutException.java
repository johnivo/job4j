package ru.job4j.calculator;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.07.2019
 */
public class MenuOutException extends RuntimeException {

    public MenuOutException(String msg) {
        super(msg);
    }
}
