package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for calculating arithmetic operations.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.07.2019
 */
public class Calculator {

    /**
     * Лист для хранения результатов вычислений.
     */
    private List<Double> storage = new ArrayList<>();

    /**
     * Лист для хранения вторых аргументов использовавшихся в выражениях.
     */
    private List<Double> secondStorage = new ArrayList<>();

    /**
     * result of the operation.
     */
    private double result;

    /**
     * addition.
     * @param first - first value.
     * @param second - second value.
     */
    public void add(double first, double second) {
        this.result = first + second;
        this.storage.add(this.result);
        this.secondStorage.add(second);
    }

    /**
     * subtract.
     * @param first - first value.
     * @param second - second value.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
        this.storage.add(this.result);
        this.secondStorage.add(second);
    }

    /**
     * division.
     * @param first - first value.
     * @param second - second value.
     */
    public void div(double first, double second) {
        if (!(second == 0)) {
            this.result = first / second;
            this.storage.add(this.result);
            this.secondStorage.add(second);
        } else {
            new MenuOutException("Division by zero");
        }
    }

    /**
     * multiplication.
     * @param first - first value.
     * @param second - second value.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
        this.storage.add(this.result);
        this.secondStorage.add(second);
    }

    /**
     * getResult.
     * @return - result.
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Метод возвращает список результатов всех вычислений.
     * @return result список результатов.
     */
    public List<Double> getStorage() {
        return this.storage;
    }

    /**
     * Метод возвращает список вторых аргументов в выражениях.
     * @return result список результатов.
     */
    public List<Double> getSecondStorage() {
        return this.secondStorage;
    }
}
