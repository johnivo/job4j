package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BubbleSortTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 14.12.2018
 */
public class BubbleSortTest {

    /**
     * тест проверяет сортировку массива методом перестановки.
     */
    @Test
    public void whenSortArrayWithSevenElementsThenSortedArray() {
        BubbleSort bubble = new BubbleSort();
        int[] input = new int[]{5, 8, 2, 7, 3, 15, 10};
        int[] result = bubble.sort(input);
        int[] expect = new int[]{2, 3, 5, 7, 8, 10, 15};
        assertThat(result, is(expect));
    }
}