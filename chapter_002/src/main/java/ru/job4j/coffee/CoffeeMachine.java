package ru.job4j.coffee;

import java.util.Arrays;

/**
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 01.02.2019
 */
public class CoffeeMachine {

    /**
     * Поля: купюра, цена кофе.
     */
    private int value;
    private int price;

    /**
     * Конструктор.
     *
     * @param value купюра.
     * @param price цена.
     */
    public CoffeeMachine(int value, int price) {
        this.value = value;
        this.price = price;
    }

    /**
     * Метод рассчитывает выдачу сдачи для автомата наименьшим количеством монет.
     * В автомате монеты номиналом 1, 2, 5, 10.
     *
     * @param value купюра 50, 100 и тд.
     * @param price цена кофе.
     * @return сдача в виде массива монет.
     */
    int[] changes(int value, int price) {
        int[] result = new int[100];
        int[] coins = {10, 5, 2, 1};
        int change = value - price;
        if (change < 0) {
            throw new ArithmeticException();
        }
        int size = 0;
        int sum;
        for (int i = 0; i < coins.length; i++) {
            if (change > 0 && change % coins[i] != 0) {
                sum = 0;
                for (int j = 0; j < (change / coins[i]); j++) {
                    result[size++] = coins[i];
                    sum = sum + coins[i];
                }
                change = change - sum;
            } else {
                for (int j = 0; j < (change / coins[i]); j++) {
                    result[size++] = coins[i];
                }
                break;
            }
        }
        return Arrays.copyOf(result, size);
    }
}