package ru.job4j.array;

/**
 * Массив заполнен true или false.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 13.12.2018
 */
public class Check {

    /**
     * Метод проверяет, что все элементы в массиве являются true или false.
     * @param data заданный массив.
     * @return true or false.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (boolean x : data) {
            if (x != data[0]) {
                result = false;
                break;
            }
        }
        return result;
    }
}