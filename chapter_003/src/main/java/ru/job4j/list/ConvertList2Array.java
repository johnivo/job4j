package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Метод конвертирует лист массивов в один лист Integer.
     * Массивы в списке list могут быть разного размера.
     *
     * @param list входящий список массивов int[].
     * @return genericList общий лист Integer.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> genericList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (Integer j : list.get(i)) {
                genericList.add(j);
            }
        }
        return genericList;
    }
     //2й вариант
     //  List<Integer> genericList = new ArrayList<>();
     //  for (int[] lists : list) {
     //       for (Integer cell : lists) {
     //           genericList.add(cell);
     //       }
     //    }
     //   return genericList;
     //}

    public List<Integer> convert2(List<Integer[]> list) {
        List<Integer> genericList = list
                .stream()
                .flatMap(e -> Stream.of(e))
                .collect(Collectors.toList());
        return genericList;
    }
}
