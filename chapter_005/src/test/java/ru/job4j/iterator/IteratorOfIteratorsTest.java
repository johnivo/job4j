package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 01.04.2019
 */
public class IteratorOfIteratorsTest {
    Iterator<Integer> it;

    @Before
    public void setUp() {
        Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> it2 = Arrays.asList(4, 5, 6).iterator();
        Iterator<Integer> it3 = Arrays.asList(7, 8, 9).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
        IteratorOfIterators test = new IteratorOfIterators();
        it = test.convert(its);
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
    }

    @Test
    public void hasNextShouldReturnFalseInCaseOfEmptyIterators() {
        Iterator<Integer> it1 = (new ArrayList<Integer>()).iterator();
        Iterator<Integer> it2 = (new ArrayList<Integer>()).iterator();
        Iterator<Integer> it3 = (new ArrayList<Integer>()).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
        IteratorOfIterators test = new IteratorOfIterators();
        it = test.convert(its);
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1).iterator();
        IteratorOfIterators test = new IteratorOfIterators();
        it = test.convert(its);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }
}