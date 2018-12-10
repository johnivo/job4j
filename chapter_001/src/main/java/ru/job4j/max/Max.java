package ru.job4j.max;

/**
 * Максимум из двух чисел.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 10.12.2018
 */
public class Max {

    /**
     * Возвращает максимальное число из двух.
     * @param first первое число.
     * @param second второе число.
     * @return Максимум.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}