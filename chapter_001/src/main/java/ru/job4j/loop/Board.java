package ru.job4j.loop;

/**
 * Построение шаматной доски в псевдографике.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 12.12.2018
 */
public class Board {

    /**
     * Метод строит шахматную доску из символов x и " " размерностью i*j.
     * @param width ширина доски.
     * @param height высота доски.
     * @return все добавленные в него символы и строки в одну строку.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("x");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}