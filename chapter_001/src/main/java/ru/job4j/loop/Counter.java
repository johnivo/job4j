package ru.job4j.loop;

/**
 * Подсчет суммы чётных чисел в диапазоне.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 11.12.2018
 */
public class Counter {

    /**
     * Метод суммирует четные числа в заданном диапазоне.
     * @param start начало диапазона.
     * @param finish конец диапазона.
     * @return сумма.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}