package ru.job4j.tracker;

import java.util.Scanner;
import java.util.List;

/**
 * Ввод пользовательских данных из консоли.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 01.01.2019
 */
public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range.");
        }
        return key;
    }
}