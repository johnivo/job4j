package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ArrayMergeTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 14.12.2018
 */
public class ArrayMergeTest {

    /**
     * тест проверяет слияние двух отсортированных массивов.
     */
    @Test
    public void whenMergeFirstArrayWithSecondArrayThenSortedMergedArray() {
        int[] inputFirst = {0, 9};
        int[] inputSecond = {3, 4, 7, 8};
        ArrayMerge arraysMerge = new ArrayMerge();
        int[] result = arraysMerge.merging(inputFirst, inputSecond);
        int[] expected = new int[]{0, 3, 4, 7, 8, 9};
        assertThat(result, is(expected));
    }
}
