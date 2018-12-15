package ru.job4j.array;

/**
 * Квадратный массив заполнен true или false по диагоналям.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 15.12.2018
 */
public class MatrixCheck {

    /**
     * Метод проверяет массив заполнен true или false по диагоналям.
     *
     * @param data заданный массив.
     * @return true если начинается, иначе false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 1; i < data.length; i++) {
            if (data [0][0] != data[i][i]) {
                result = false;
                break;
            }
            if (data[0][data.length - 1] != data[i][data.length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}