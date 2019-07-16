package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.07.2019
 */
public class EndsWithTest {

    @Test
    public void whenEndsWithThenTrue() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Hello", "lo");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotEndsWithThenFalse() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Hello", "la");
        assertThat(result, is(false));
    }

    @Test
    public void whenEndsWithThenTrue2() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Madagascar", "gascar");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotEndsWithThenFalse2() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Madagascar", "gabcar");
        assertThat(result, is(false));
    }

}