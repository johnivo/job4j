package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * FactorialTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 11.12.2018
 */
public class FactorialTest {

    /**
     * тест на нахождение факториала 6.
     */
    @Test
    public void whenCalculateFactorialSixThenSevenHundredTwenty() {
        Factorial fact = new Factorial();
        assertThat(fact.calc(6), is(720));
    }

    /**
     * тест на нахождение факториала 0.
     */
    @Test
    public void whenCalculateFactorialZeroThenOne() {
        Factorial fact = new Factorial();
        assertThat(fact.calc(0), is(1));
    }
}