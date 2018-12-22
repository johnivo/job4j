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
     * Метод выполняет пеоследовательный перебор каждого элемента массива до нахождения подходящего под условие.
     * @param data заданный массив.
     * @param el искомое значение.
     * @return индекс найденного элемента.
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
}