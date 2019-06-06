package ru.job4j.io.finder;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

/**
 * Запуск поисковика файлов.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.06.2019
 */
public class MainFinder {

    public static void main(String[] args) {
        Args keys = new Args(args);
        Finder finder = new Finder(keys);
        try {
            finder.search();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
