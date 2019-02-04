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
     * Константа: номинал разменных монет.
     */
    private static final int[] COINS = {1, 2, 5, 10};

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
     *
     * @param value купюра 50, 100 и тд.
     * @param price цена кофе.
     * @return сдача в виде массива монет.
     */
    int[] changes(int value, int price) {
        int[] result = new int[100];
        int change = value - price;
        int size = 0;
        int i = COINS.length - 1;
        while (change > 0) {
            while (change >= COINS[i]) {
                result[size++] = COINS[i];
                change -= COINS[i];
            }
            i--;
        }
        return Arrays.copyOf(result, size);
    }
}