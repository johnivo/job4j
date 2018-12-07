package ru.job4j.calculator;

/**
 * Calculator - class for calculating arithmetic operations.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 07.12.2018
 */
public class Calculator {

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
    }

    /**
     * subtract.
     * @param first - first value.
     * @param second - second value.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * division.
     * @param first - first value.
     * @param second - second value.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * multiplication.
     * @param first - first value.
     * @param second - second value.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * getResult.
     * @return - result.
     */
    public double getResult() {
        return this.result;
    }
}