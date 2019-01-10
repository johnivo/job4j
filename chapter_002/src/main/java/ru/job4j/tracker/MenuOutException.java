package ru.job4j.tracker;

/**
 * class MenuOutException.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 10.01.2019
 */
public class MenuOutException extends RuntimeException {

    public MenuOutException(String msg) {
        super(msg);
    }
}
