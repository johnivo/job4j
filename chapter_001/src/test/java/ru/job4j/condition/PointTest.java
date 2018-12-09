package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * PointTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 09.12.2018
 */
public class PointTest {

    /**
     * point test.
     */
    @Test
    public void distanceTo() {
        Point a = new Point(1, 5);
        Point b = new Point(4, 3);
        double result = a.distanceTo(b);
        assertThat(result, closeTo(3.6, 0.1));
    }
}