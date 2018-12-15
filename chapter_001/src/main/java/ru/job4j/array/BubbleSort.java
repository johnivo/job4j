package ru.job4j.array;

/**
 * Сортировка массива методом перестановки.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 14.12.2018
 */
public class BubbleSort {

    /**
     * Метод упорядочивает массив по алгоритму сортировки пузырьком.
     * @param array заданный массив.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }
}