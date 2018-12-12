package ru.job4j.loop;

/**
 * Вычисление факториала числа.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 11.12.2018
 */
public class Factorial {

    /**
     * Метод находит факториал числа.
     * @param n число.
     * @return факториал.
     */
    public int calc(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++)
            fact *= i;
        return fact;
    }
}