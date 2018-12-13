package ru.job4j.array;

/**
 * Перевернуть массив.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 13.12.2018
 */
public class Turn {

    /**
     * Метод переворачивает массив задом наперед.
     * @param array заданный массив.
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        for (int index = 0; index < (array.length / 2); index++) {
            int temp = array[array.length - index - 1];
            array[array.length - index - 1] = array[index];
            array[index] = temp;
        }
        return array;
    }
}