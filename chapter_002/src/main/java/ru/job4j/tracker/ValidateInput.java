package ru.job4j.tracker;

import java.util.List;

/**
 * Проверка данных, введенных пользователем в консоли.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 10.01.2019
 */
public class ValidateInput implements Input {

    /**
     * Реализация композиции.
     * Поле содержит источник данных, который описывается интерфейсом Input.
     */
    private final Input input;

    public ValidateInput(final  Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu. ");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again. ");
            }
        } while (invalid);
        return value;
    }
}