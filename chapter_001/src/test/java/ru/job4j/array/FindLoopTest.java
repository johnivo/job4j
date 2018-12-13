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
}