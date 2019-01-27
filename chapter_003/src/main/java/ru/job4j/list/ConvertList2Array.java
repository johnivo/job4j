package ru.job4j.list;

import java.util.Iterator;
import java.util.List;

/**
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 27.01.2018
 */
public class ConvertList2Array {

    /**
     * Метод равномерно разбивает лист на заданное количество строк двумерного массива.
     *
     * @param list входящий массив.
     * @param rows количество строк.
     * @return двумерный массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % rows != 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];
        Iterator<Integer> it = list.iterator();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (it.hasNext()) {
                    array[i][j] = it.next();
                } else {
                    break;
                }
            }
        }
        return array;
    }
}
