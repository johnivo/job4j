package ru.job4j.interactcalc;

import java.util.Scanner;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 21.07.2019
 */
public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String next() {
        return scanner.next();
    }

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

}
