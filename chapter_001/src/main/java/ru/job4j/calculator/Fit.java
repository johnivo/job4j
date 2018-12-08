package ru.job4j.calculator;

/**
 * Calculation of the ideal weight.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 08.12.2018
 */
public class Fit {

    /**
     * Идеальный вес для мужщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}