package ru.job4j.stream;

import java.util.Arrays;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 27.03.2019
 */
public class ArrayProcessing {

    public int processing(int[] array) {
        int result = Arrays.stream(array)
                .filter(e -> e % 2 == 0)
                .reduce(0, (acc, e) -> acc + e * e);
        return result;
    }

    public static int processing2(int[] array) {
        int result = Arrays.stream(array)
                .filter(e -> e % 2 == 0)
                .map(e -> e * e)
                .sum();
        return result;
    }
}