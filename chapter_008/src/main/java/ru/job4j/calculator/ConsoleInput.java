package ru.job4j.calculator;

import java.util.List;
import java.util.Scanner;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.07.2019
 */
public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public Integer ask(String question, List<Integer> range) {
        Integer key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (Integer value : range) {
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
