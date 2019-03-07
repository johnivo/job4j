package ru.job4j.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 07.03.2019
 */
public class MatrixToList {

    public List<Integer> matrixToList(Integer[][] matrix) {
        List<Integer> list = Stream.of(matrix)
                //.flatMap(e -> Stream.of(e))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        list.forEach(System.out::print);
        return list;
    }

    public List<Integer> listsToList(List<List<Integer>> matrix) {
        List<Integer> list = matrix.stream()
                //.flatMap(e -> e.stream())
                .flatMap(List::stream)
                .collect(Collectors.toList());
        list.forEach(System.out::print);
        return list;
    }
}
