package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MaxTest - class test.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 10.12.2018
 */
public class MaxTest {

    /**
     * тест на поиск максимума из двух чисел.
     */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        assertThat(maxim.max(1, 2), is(2));
    }


    /**
     * тест на поиск максимума из двух равных чисел.
     */
    @Test
    public void whenFirstEquallySecond() {
        Max maxim = new Max();
        assertThat(maxim.max(1, 1), is(1));
    }

    /**
     * тест на поиск максимума из трех чисел.
     */
    @Test
    public void whenFirsMoreSecondAndThird() {
        Max maxim = new Max();
        assertThat(maxim.max(2, 5, 9), is(9));
    }

    /**
     * тест на поиск максимума из трех равных чисел.
     */
    @Test
    public void whenFirsEquallySecondAndThird() {
        Max maxim = new Max();
        assertThat(maxim.max(2, 2, 2), is(2));
    }
}