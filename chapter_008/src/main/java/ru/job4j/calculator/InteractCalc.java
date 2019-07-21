package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
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
     * @param key ключ операции.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input);
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
     * @param temp флаг переиспользования результата вычисления.
     * @return массив аргументов.
     */
    public Double[] load(boolean temp) {

        String first = null;
        String second = null;

        if (!temp) {
            first = input.ask("enter first number: ");
            second = input.ask("enter second number: ");

        }

        if (temp) {
            List<Double> storage = calc.getStorage();
            first = String.valueOf(storage.get(storage.size() - 1));
            second = input.ask("enter second number: ");
        }

        reuse = false;

        return new Double[] {Double.valueOf(first), Double.valueOf(second)};
    }


    /**
     * Сложение двух чисел.
     */
    public class AddNumbers extends BaseAction {

        public AddNumbers(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input) {

            Double[] source = load(reuse);
            calc.calculation(
                    source[0], source[1],
                    (one, two) -> {
                        Double result = one + two;
                        System.out.printf("%s + %s = ", one, two);
                        return result;
                    },
                    //result -> System.out.println(result)
                    result -> output.accept(result.toString())
            );
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
        public void execute(Input input) {

            Double[] source = load(reuse);
            calc.calculation(
                    source[0], source[1],
                    (one, two) -> {
                        Double result = one - two;
                        System.out.printf("%s - %s = ", one, two);
                        return result;
                    },
                    result -> System.out.println(result)
            );
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
        public void execute(Input input) {

            Double[] source = load(reuse);
            calc.calculation(
                    source[0], source[1],
                    (one, two) -> {
                        Double result = null;
                        if (!(two == 0)) {
                            result = one / two;
                            System.out.printf("%s / %s = ", one, two);
                        } else {
                            throw new ArithmeticException();
                        }
                        return result;
                    },
                    result -> System.out.println(result)
            );
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
        public void execute(Input input) {

            Double[] source = load(reuse);
            calc.calculation(
                    source[0], source[1],
                    (one, two) -> {
                        Double result = one * two;
                        System.out.printf("%s * %s = ", one, two);
                        return result;
                    },
                    result -> System.out.println(result)
            );
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
        public void execute(Input input) {

            Map<Double, BiFunction> last = calc.getLastAction();
            if (last != null && !last.isEmpty()) {

                List<Double> storage = calc.getStorage();
                Double previousResult = storage.get(storage.size() - 1);

                List<Double> secondStorage = calc.getSecondStorage();
                Double repeatSecond = secondStorage.get(secondStorage.size() - 1);

                BiFunction lastBiFunction = last.get(repeatSecond);

                calc.calculation(
                        previousResult, repeatSecond,
                        lastBiFunction,
                        result -> System.out.println(result)
                );

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
        public void execute(Input input) {

            List<Double> storage = calc.getStorage();
            Double previousResult = storage.get(storage.size() - 1);

            if (storage != null && !storage.isEmpty()) {
                reuse = true;
            } else {
                output.accept("this is first calculation");
            }
        }

    }

}
