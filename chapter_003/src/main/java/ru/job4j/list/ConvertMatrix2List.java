package ru.job4j.list;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 27.01.2018
 */
public class ConvertMatrix2List {

    /**
     * Метод конверитирует входящий двумерный массив целых чисел в ArrayList.
     * Проходя по всем элементам массива и добавляя в List<Integer>.
     *
     * @param array входящий массив.
     * @return ArrayList.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (Integer column : row) {
                list.add(column);
            }
        }
        return list;
    }
}
