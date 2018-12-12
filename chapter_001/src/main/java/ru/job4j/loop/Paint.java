package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Построение пирамиды в псевдографике.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 12.12.2018
 */
public class Paint {

    /**
     * Метод строит правосторонний треугольник из символов ^ и " ".
     *
     * @param height высота треугольника.
     * @return все добавленные в него символы и строки в одну строку.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Метод строит левосторонний треугольник из символов ^ и " ".
     *
     * @param height высота треугольника.
     * @return все добавленные в него символы и строки в одну строку.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Метод строит пирамиду из символов ^ и " " заданной высоты.
     *
     * @param height высота пирамиды.
     * @return все добавленные в него символы и строки в одну строку.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }


    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}