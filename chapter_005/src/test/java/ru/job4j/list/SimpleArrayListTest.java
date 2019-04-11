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
public class SimpleArrayListTest {

    private SimpleArrayList<Integer> container;
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        this.container = new SimpleArrayList();
        IntStream.range(0, 10).forEach(this.container::add);
        this.it = this.container.iterator();
    }

    @Test
    public void whenDefaultCapacityOverflowThenCapacityDoubles() {
        container.add(100);
        assertThat(container.get(10), is(100));
        assertThat(container.getSize(), is(11));
        assertThat(container.size(), is(20));
    }

    @Test
    public void whenTargetCapacityOverflowThenCapacityDoubles() {
        this.container = new SimpleArrayList(2);
        container.add(1);
        container.add(2);
        container.add(3);
        assertThat(container.get(1), is(2));
        assertThat(container.getSize(), is(3));
        assertThat(container.size(), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenTryToGetElementWithIncorrectIndexThenThrowsIndexOutOfBoundsException() {
        container.get(15);
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        this.container = new SimpleArrayList(3);
        container.add(1);
        container.add(2);
        container.add(5);
        Iterator<Integer> it = this.container.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        this.container = new SimpleArrayList(3);
        container.add(1);
        container.add(2);
        container.add(5);
        Iterator<Integer> it = this.container.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(5));
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