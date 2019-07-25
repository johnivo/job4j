package ru.job4j.interactcalc;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.07.2019
 */
public class Calculator {

    /**
     * результат вычисления.
     */
    private Double result;

    /**
     * Хранилище операций.
     */
    protected Map<String, Action> operation = new HashMap<>();

    /**
     * Список содержит доступные опции.
     */
    private List<String> options = new ArrayList<>();

    /**
     * Конструктор.со списком операций, загружаемых по-умолчанию.
     */
    public Calculator() {
        this.loadOperation(new Add());
        this.loadOperation(new Subtract());
        this.loadOperation(new Multiple());
        this.loadOperation(new Div());

        this.loadOption(new ReSelect());
        this.loadOption(new Reuse());
        this.loadOption(new NewCalc());
    }

    /**
     * Возвращает результат вычисления.
     * @return result результат.
     */
    public Double getResult() {
        return this.result;
    }

    /**
     * Заносит в карту операцию с соответствующим обозначением.
     * @param action операция.
     */
    public void loadOperation(Action action) {
        this.operation.put(action.operation(), action);
    }

    /**
     * Заполняет список доступных опций.
     * @param option опция.
     */
    public void loadOption(Option option) {
        this.options.add(option.option());
    }

    /**
     * Выполняет заданную арифметическую операцию над двумя заданными аргументами.
     * @param first первый аргумент.
     * @param second второй аргумент.
     * @param operation операция.
     */
    public void calculate(Double first, Double second, String operation) {
        this.result = this.operation.get(operation).arithmetical(first, second);
    }


    public String menu() {
        final String LN = System.lineSeparator();
        String menu = Joiner.on(LN).join(
                "Arithmetical operations: +-/*",
                "Action menu:",
                " reselect operation: =",
                " reuse of previous calculation: m",
                " new calculation: n",
                " exit: e",
                ""
        );
        return menu;
    }

    /**
     * Сложение.
     */
    public static final class Add implements Action {

        @Override
        public String operation() {
            return "+";
        }

        @Override
        public Double arithmetical(Double first, Double second) {
            return first + second;
        }

        @Override
        public Boolean getArgs() {
            return true;
        }

    }

    /**
     * Вычитание.
     */
    public static final class Subtract implements Action {

        @Override
        public String operation() {
            return "-";
        }

        @Override
        public Double arithmetical(Double first, Double second) {
            return first - second;
        }

        @Override
        public Boolean getArgs() {
            return true;
        }
    }

    /**
     * Произведение.
     */
    public static final class Multiple implements Action {

        @Override
        public String operation() {
            return "*";
        }

        @Override
        public Double arithmetical(Double first, Double second) {
            return first * second;
        }

        @Override
        public Boolean getArgs() {
            return true;
        }
    }

    /**
     * Деление.
     */
    public static final class Div implements Action {

        @Override
        public String operation() {
            return "/";
        }

        @Override
        public Double arithmetical(Double first, Double second) {
            Double result;
            if (!(second == 0)) {
                result = first / second;
            } else {
                throw new ArithmeticException();
            }
            return result;
        }

        @Override
        public Boolean getArgs() {
            return true;
        }
    }

    /**
     * Повторный выбор операции.
     */
    public static final class ReSelect implements Option {

        @Override
        public String option() {
            return "=";
        }
    }

    /**
     * Переиспользование предыдущего вычисления.
     */
    public static final class Reuse implements Option {

        @Override
        public String option() {
            return "m";
        }
    }

    /**
     * Новое вычисление.
     */
    public static final class NewCalc implements Option {

        @Override
        public String option() {
            return "n";
        }
    }

}
