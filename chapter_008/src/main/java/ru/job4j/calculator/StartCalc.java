package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.07.2019
 */
public class StartCalc {

    /**
     * Входные данные.
     */
    private final Input input;

    /**
     * Поле калькулятор.
     */
    private final Calculator calc;

    /**
     * Поле интерактивный калькулятор.
     */
    private final InteractCalc menu;

    /**
     * Список номеров операций.
     */
    private List<Integer> range = new ArrayList<>();

    /**
     * Вывод данных.
     */
    private Consumer<String> output;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param calc хранилище заявок.
     * @param output вывод данных.
     */
    public StartCalc(Input input, Calculator calc, InteractCalc menu, Consumer<String> output) {
        this.input = input;
        this.calc = calc;
        this.menu = menu;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     * заполняем список номеров операций и выбираем нужный.
     * пока не нажмем выход.
     */
    public void init() {
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        menu.show();
        do {
            menu.select(this.input.ask("select number of operation:", range));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }

    /**
     * Запуск программы.
     * @param args .
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Calculator calc = new Calculator();
        InteractCalc menu = new InteractCalc(input, calc, System.out::println);
        menu.fillActions();

        new StartCalc(input, calc, menu, System.out::println).init();

//        new StartCalc(
//                        new ConsoleInput(),
//                new Calculator(),
//                System.out::println
//        ).init();
    }

}
