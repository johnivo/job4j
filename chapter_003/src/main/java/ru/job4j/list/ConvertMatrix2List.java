package ru.job4j.list;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Integer> toList2(Integer[][] array) {
        List<Integer> list = Stream.of(array)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        return list;
    }

}
