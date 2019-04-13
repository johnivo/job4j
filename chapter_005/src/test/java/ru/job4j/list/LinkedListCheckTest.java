package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.04.2019
 */
public class LinkedListCheckTest {

    private LinkedListCheck checker = new LinkedListCheck();
    private Node<Integer> first;
    private Node<Integer> second;
    private Node<Integer> third;

    @Before
    public void notCycled() {
        first = new Node<>(1);
        second = new Node<>(2);
        third = new Node<>(5);

        first.next = second;
        second.next = third;
        third.next = null;
    }

    @Test
    public void whenNotCycledThenFalse() {
        assertThat(checker.hasCycle(first), is(false));
    }

    @Test
    public void whenCycledLastElementThenTrue() {
        this.third.next = third;
        assertThat(checker.hasCycle(first), is(true));
    }

    @Test
    public void whenCycledSecondElementThenTrue2() {
        this.third.next = second;
        assertThat(checker.hasCycle(first), is(true));
    }
}