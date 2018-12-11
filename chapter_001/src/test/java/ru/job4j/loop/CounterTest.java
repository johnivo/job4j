package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CounterTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 11.12.2018
 */
public class CounterTest {

    /**
     * тест подсчета суммы четных чисел в диапазоне от 2 до 8.
     */
    @Test
    public void whenSumEvenNumbersFromTwoToEightThenTwenty() {
        Counter sum = new Counter();
        assertThat(sum.add(2, 8), is(20));
    }

    /**
     * тест подсчета суммы четных чисел в диапазоне от 2 до 7.
     */
    @Test
    public void whenSumEvenNumbersFromTwoToSevenThenTwelve() {
        Counter sum = new Counter();
        assertThat(sum.add(2, 7), is(12));
    }

    /**
     * тест подсчета суммы четных чисел в диапазоне от 0 до 7.
     */
    @Test
    public void whenSumEvenNumbersFromOneToSevenThenTwelve() {
        Counter sum = new Counter();
        assertThat(sum.add(2, 7), is(12));
    }
}