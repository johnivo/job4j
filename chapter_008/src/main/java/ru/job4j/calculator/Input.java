package ru.job4j.calculator;

import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.07.2019
 */
public interface Input {

    String ask(String question);

    Integer ask(String question, List<Integer> range);
}
