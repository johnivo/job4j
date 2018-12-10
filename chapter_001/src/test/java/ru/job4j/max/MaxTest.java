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
     * max test.
     */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        assertThat(maxim.max(1, 2), is(2));
    }
}