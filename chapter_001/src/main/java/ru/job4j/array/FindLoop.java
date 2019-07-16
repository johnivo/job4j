package ru.job4j.array;

/**
 * Классический поиск перебором элемента в массиве.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 13.12.2018
 */
public class FindLoop {

    /**
     * Последовательно перебирает каждый элемент массива
     * до нахождения подходящего под условие.
     * @param data заданный массив.
     * @param el искомое значение.
     * @return индекс найденного элемента или -1 если элемента нет в массиве.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Выполняет поиск по массиву, но не во всем массиве, а только в указанном диапазоне.
     * @param data массив чисел.
     * @param el элемент, который нужно найти.
     * @param start индекс с которого начинаем поиск.
     * @param finish индекс которым заканчиваем поиск.
     * @return индекс найденного элемента или -1 если элемента нет в массиве.
     */
    public int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;
        for (int index = start; index <= finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Сортирует массив по возрастанию методом выборки.
     * @param data массив чисел, который нужно отсортировать по возрастанию.
     * @return data отсортированный по возрастанию массив.
     */
    public int[] sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            int temp = data[i];
            data[i] = data[min];
            data[min] = temp;
        }
        return data;
    }

}