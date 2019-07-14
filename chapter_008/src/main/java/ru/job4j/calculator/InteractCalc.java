package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.07.2019
 */
public class InteractCalc {

    /**
     * Флаг переиспользования предыдущего вычисления.
     */
    boolean reuse = false;

    /**
     * Флаг повторного выбора операции.
     */
    boolean reselect = false;

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Поле калькулятор.
     */
    private final Calculator calc;

    /**
     * Вывод данных.
     */
    private final Consumer<String> output;

    /**
     * Список операций - меню.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input объект типа Input.
     * @param calc объект типа Calculator.
     */
    public InteractCalc(Input input, Calculator calc, Consumer<String> output) {
        this.input = input;
        this.calc = calc;
        this.output = output;
    }

    /**
     * Возвращает число пунктов меню.
     *
     * @return размер листа.
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Заполняет меню.
     */
    public void fillActions() {
        this.actions.add(new AddNumbers("0", "Add numbers."));
        this.actions.add(new SubtractNumbers("1", "subtract numbers."));
        this.actions.add(new DivisionNumbers("2", "division numbers."));
        this.actions.add(new MultiNumbers("3", "multiplication numbers."));

        this.actions.add(new ReSelect("4", "re-select operation."));
        this.actions.add(new ReusePrevious("5", "reuse previous calculation."));
    }

    /**
     * Выполняет действие по указанному ключу.
     *
     * @param key ключ операции.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.calc);
    }

    /**
     * Выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    /**
     * Определяет аргументы в выражении.
     */
    public String[] load(boolean temp, boolean secondTemp) {

        String first = null;
        String second = null;

        if (!temp && !secondTemp) {
            first = input.ask("enter first number: ");
            second = input.ask("enter second number: ");

        }

        if (temp) {
            List<Double> storage = calc.getStorage();
            first = String.valueOf(storage.get(storage.size() - 1));
            second = input.ask("enter second number: ");
        }

        if (secondTemp) {
            List<Double> storage = calc.getStorage();
            Double previous = storage.get(storage.size() - 1);
            first = String.valueOf(previous);

            List<Double> secondStorage = calc.getSecondStorage();
            Double repeat = secondStorage.get(secondStorage.size() - 1);
            second = String.valueOf(repeat);
        }

        reuse = false;
        reselect = false;

        return new String[] {first, second};
    }


    /**
     * Сложение двух чисел.
     */
    public class AddNumbers extends BaseAction {

        public AddNumbers(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Calculator calc) {
            output.accept("addition of two numbers");
            String[] source = load(reuse, reselect);
            calc.add(Double.valueOf(source[0]), Double.valueOf(source[1]));
            Double result = calc.getResult();
            output.accept(result.toString());
        }
    }

    /**
     * Разность двух чисел.
     */
    public class SubtractNumbers extends BaseAction {

        public SubtractNumbers(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Calculator calc) {
            output.accept("subtraction of two numbers");
            String[] source = load(reuse, reselect);
            calc.subtract(Double.valueOf(source[0]), Double.valueOf(source[1]));
            Double result = calc.getResult();
            output.accept(result.toString());
        }
    }

    /**
     * Деление чисел.
     */
    public class DivisionNumbers extends BaseAction {

        public DivisionNumbers(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Calculator calc) {
            output.accept("division of two numbers");
            String[] source = load(reuse, reselect);
            calc.div(Double.valueOf(source[0]), Double.valueOf(source[1]));
            Double result = calc.getResult();
            output.accept(result.toString());
        }
    }

    /**
     * Умножение чисел.
     */
    public class MultiNumbers extends BaseAction {

        public MultiNumbers(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Calculator calc) {
            output.accept("multiplication of two numbers");
            String[] source = load(reuse, reselect);
            calc.multiple(Double.valueOf(source[0]), Double.valueOf(source[1]));
            Double result = calc.getResult();
            output.accept(result.toString());
        }
    }

    /**
     * Повторный выбор операции.
     */
    public class ReSelect extends BaseAction {

        public ReSelect(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Calculator calc) {
            output.accept("re-select operation");
            List<Double> storage = calc.getStorage();
            List<Double> secondStorage = calc.getSecondStorage();
            if (secondStorage != null && !secondStorage.isEmpty()) {
                reselect = true;
            } else {
                output.accept("this is first calculation");
            }

        }
    }

    /**
     * Переиспользование предыдущего вычисления.
     */
    public class ReusePrevious extends BaseAction {

        public ReusePrevious(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Calculator calc) {
            output.accept("reuse previous calculation.");
            List<Double> storage = calc.getStorage();
            if (storage != null && !storage.isEmpty()) {
                reuse = true;
            } else {
                output.accept("this is first calculation");
            }
        }
    }

}
