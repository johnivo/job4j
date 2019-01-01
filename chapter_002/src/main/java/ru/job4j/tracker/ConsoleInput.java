package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Ввод пользовательских данных из консоли.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 01.01.2019
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}