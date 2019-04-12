package ru.job4j.list;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.04.2019
 */
public class SimpleStackTest {

    @Test
    public void whenUsedPushThenSizeStackIsIncreases() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.size(), is(3));
        stack.push(4);
        assertThat(stack.size(), is(4));
    }

    @Test
    public void whenValueIsEnteredLastThenItFirstCameOut() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(2);
        stack.push(5);
        stack.push(9);
        assertThat(stack.poll(), is(9));
    }

    @Test
    public void whenUsedPollThenSizeStackIsDecreases() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(2);
        stack.push(5);
        stack.push(9);
        assertThat(stack.poll(), is(9));
        assertThat(stack.size(), is(2));
        assertThat(stack.poll(), is(5));
        assertThat(stack.size(), is(1));
    }
}