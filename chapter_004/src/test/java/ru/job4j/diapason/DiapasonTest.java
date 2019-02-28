package ru.job4j.diapason;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 28.02.2019
 */
public class DiapasonTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(1, 4, x -> Math.pow(x, 2) + x + 1);
        List<Double> expected = Arrays.asList(3D, 7D, 13D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmFunctionThenLogarithmResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(1, 3, x -> Math.log(x));
        List<Double> expected = Arrays.asList(0D, 0.6931471805599453D);
        assertThat(result, is(expected));
    }
}