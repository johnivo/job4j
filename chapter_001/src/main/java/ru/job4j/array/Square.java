package ru.job4j.array;

/**
 * Заполнение массива степенями чисел.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 13.12.2018
 */
public class Square {

    /**
     * Метод заполняет массив числами от 1 до bound возведенными в квадрат.
     * @param bound длина массива.
     * @return массив.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        // заполнить массив через цикл элементами от 1 до bound возведенными в квадрат
        for (int i = 1; i <= bound; i++) {
            rst[i-1] = i*i;
        }
        return rst;
    }
}