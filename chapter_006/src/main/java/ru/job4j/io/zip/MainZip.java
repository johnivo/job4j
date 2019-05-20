package ru.job4j.io.zip;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

/**
 * Точка входа в программу-архиватор.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 17.05.2019
 */
public class MainZip {

    public static void main(String[] args) throws ParseException {
        Zip zip = new Zip();
        Args keys = new Args(args);
        try {
            zip.pack(keys);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
