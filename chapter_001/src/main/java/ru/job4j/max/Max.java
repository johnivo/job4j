package ru.job4j.max;

/**
 * Определение максимального из двух и трех чисел.
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

    /**
     * Возвращает максимальное из трех чисел.
     * @param first первое число.
     * @param second второе число.
     * @param third третье число.
     * @return Максимум.
     */
    public int max(int first, int second, int third) {
        return this.max(this.max(first, second), third);
    }
}