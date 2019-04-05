package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 04.04.2019
 */
public class SimpleArrayTest {

    private SimpleArray<Integer> simple;
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        this.simple = new SimpleArray(10);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.add(5);
        this.it = this.simple.iterator();
    }

    @Test
    public void whenCreateContainerThenShouldReturnTheSameType() {
        SimpleArray<String> simple = new SimpleArray<String>(5);
        simple.add("test");
        String result = simple.get(0);
        assertThat(result, is("test"));
    }

    @Test
    public void whenTypeIntThenShouldReturnTheSameType() {
        int result = simple.get(0);
        assertThat(result, is(1));
        assertThat(simple.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invocationOfRemoveMethodShouldThrowArrayIndexOutOfBoundsException() {
        simple.remove(7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invocationOfSetMethodShouldThrowArrayIndexOutOfBoundsException() {
        simple.set(5, 3);
    }

    @Test
    public void whenInvocationOfRemoveMethodThenOtherElementsShouldMoveToTheLeft() {
        simple.remove(1);
        simple.remove(1);
        assertThat(simple.get(1), is(4));
        assertThat(simple.get(2), is(5));
    }

    @Test
    public void whenInvocationOfSetMethodThenElementShouldAcceptNewValue() {
        simple.set(0, 3);
        assertThat(simple.get(0), is(3));
    }

    @Test
    public void hasNextShouldReturnFalseInCaseOfEmptyIterators() {
        Iterator<Integer> it = (new SimpleArray<Integer>(3)).iterator();
        assertThat(it.hasNext(), is(false));
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
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        it.next();
    }
}