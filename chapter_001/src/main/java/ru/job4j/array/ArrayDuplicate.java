package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаление дубликатов в массиве.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 17.12.2018
 */
public class ArrayDuplicate {

    /**
     * Метод убирает все дубликаты строк из массива.
     *
     * @param array заданный массив.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        int unique = array.length;
        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[--unique];
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, unique);
    }
}