package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * FindLoopTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 13.12.2018
 */
public class FindLoopTest {

    /**
     * тест классического поиска перебором элемента 3 в массиве {5, 10, 3}.
     */
    @Test
    public void whenArrayHasValue3Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 3;
        int result = find.indexOf(input, value);
        int expect = 2;
        assertThat(result, is(expect));
    }


    /**
     * тест классического поиска перебором элемента 7 в массиве {5, 10, 3}.
     */
    @Test
    public void whenArrayHasValue7Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 7;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenFind3() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 2, 10, 2, 4};
        int value = 2;
        int start = 2;
        int finish = 4;
        int result = find.indexOf(input, value, start, finish);
        int expect = 3;
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort5() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {3, 4, 1, 2, 5};
        int[] result = find.sort(input);
        int[] expect = new int[] {1, 2, 3, 4, 5};
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort6() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {3, 4, 1, 9, 2, 5};
        int[] result = find.sort(input);
        int[] expect = new int[] {1, 2, 3, 4, 5, 9};
        assertThat(result, is(expect));
    }
}