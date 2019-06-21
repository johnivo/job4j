package ru.job4j.tracker;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Точка входа в программу.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 08.01.2019
 */
public class StartUI {

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;

    /**
     * Вывод данных.
     */
    private final Consumer<String> output;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     * @param output вывод данных.
     */
    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            //int key = Integer.valueOf(input.ask("select: "));
            //menu.select(Integer.valueOf(input.ask("select: ")));
            menu.select(input.ask("select:", range));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }

    /**
     * Запуск программы.
     * @param args .
     */
    public static void main(String[] args) {
        new StartUI(
                new ValidateInput(
                        new ConsoleInput()
                ),
                new Tracker(),
                System.out::println
        ).init();
    }
}