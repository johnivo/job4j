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
 * @since 11.04.2019
 */
public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> container;
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        this.container = new SimpleLinkedList();
        IntStream.range(0, 10).forEach(this.container::add);
        this.it = this.container.iterator();
    }

    @Test
    public void whenDefaultCapacityOverflowThenCapacityDoubles() {
        container.add(100);
        assertThat(container.get(0), is(100));
        assertThat(container.getSize(), is(11));
    }

    @Test
    public void whenTargetCapacityOverflowThenCapacityDoubles() {
        this.container = new SimpleLinkedList();
        container.add(1);
        container.add(2);
        container.add(3);
        assertThat(container.get(2), is(1));
        assertThat(container.getSize(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenTryToGetElementWithIncorrectIndexThenThrowsIndexOutOfBoundsException() {
        container.get(15);
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        this.container = new SimpleLinkedList();
        container.add(1);
        container.add(2);
        container.add(5);
        Iterator<Integer> it = this.container.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        this.container = new SimpleLinkedList();
        container.add(1);
        container.add(4);
        container.add(5);
        Iterator<Integer> it = this.container.iterator();
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(1));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationOfNextMethodShouldThrowConcurrentModificationException() {
        container.add(1);
        container.add(2);
        it.next();
        it.hasNext();
        container.add(5);
        it.next();
    }

}