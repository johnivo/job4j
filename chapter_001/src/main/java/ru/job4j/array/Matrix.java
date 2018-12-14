package ru.job4j.array;

/**
 * Таблица умножения.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 14.12.2018
 */
public class Matrix {

    /**
     * Метод заполняет таблицу умножения.
     *
     * @param size размер матрицы.
     * @return таблица умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}