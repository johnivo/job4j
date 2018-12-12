package ru.job4j.loop;

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
        // Буфер для результата.
        StringBuilder screen = new StringBuilder();
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != height; column++) {
                // если строка равна ячейки, то рисуем галку.
                // в данном случае строка определяем, сколько галок будет на строке
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }

    /**
     * Метод строит левосторонний треугольник из символов ^ и " ".
     *
     * @param height высота треугольника.
     * @return все добавленные в него символы и строки в одну строку.
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != height; column++) {
                if (row >= height - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Метод строит пирамиду из символов ^ и " " заданной высоты.
     *
     * @param height высота пирамиды.
     * @return все добавленные в него символы и строки в одну строку.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
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