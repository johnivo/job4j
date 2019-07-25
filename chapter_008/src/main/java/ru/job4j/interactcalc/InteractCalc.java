package ru.job4j.interactcalc;

import java.util.function.Consumer;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 21.07.2019
 */
public class InteractCalc {

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Получение данных от пользователя.
     */
    private final Calculator calc;

    /**
     * Вывод данных.
     */
    private final Consumer<String> output;

    /**
     * Поле содержит предыдущий результат вычисления.
     */
    private Double last;

    /**
     * Флаг переиспользования предыдущего вычисления.
     */
    boolean reuse = false;

    /**
     * Флаг повторного выбора операции.
     */
    boolean reselect = false;

    /**
     * Поле содержит предыдущую операцию.
     */
    private String previousOp;

    /**
     * Поле содержит предыдущий второй аргумент.
     */
    private String previousSec;

    /**
     * Конструктор.
     *
     * @param input ввод данных.
     * @param calc объект типа Calculator.
     * @param output вывод данных.
     */
    public InteractCalc(Input input, Calculator calc, Consumer<String> output) {
        this.input = input;
        this.calc = calc;
        this.output = output;
    }

    /**
     * Запускает калькулятор.
     * Определяет порядок взаимодействия с пользователем.
     */
    public void action() {
        output.accept(this.calc.menu());
        boolean exit = false;
        do {
            String first = reuse || reselect ? String.valueOf(last) : input.ask("Enter arg: ");
            String operation = reselect ? previousOp : input.ask("Enter operation: ");

            String second = "0";
            if (!operation.equals("sin") && !operation.equals("cos")) {
                second = reselect ? previousSec : input.ask("Enter second arg: ");
            }

            this.calc.calculate(Double.valueOf(first), Double.valueOf(second), operation);
            Double result = calc.getResult();
            //System.out.printf("Result: %s", result);
            //System.out.println();
            output.accept("Result: ");
            output.accept(result.toString());

            String option = input.ask("Choose next action: ");
            if (option.equals("m")) {
                last = result;
                reuse = true;
                reselect = false;
            } else if (option.equals("=")) {
                last = result;
                previousOp = operation;
                previousSec = second;
                reselect = true;
                reuse = false;
            } else if (option.equals("n")) {
                reuse = false;
                reselect = false;
            } else {
                exit = true;
            }
        } while (!exit);
    }

}
