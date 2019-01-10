package ru.job4j.tracker;

import java.util.List;

/**
 * Interface for ConsoleInput.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 01.01.2019
 */
public interface Input {

    String ask(String question);

    int ask(String question, List<Integer> range);
}