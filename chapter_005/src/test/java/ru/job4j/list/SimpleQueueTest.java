package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.04.2019
 */
public class SimpleQueueTest {

    @Test
    public void whenQueueIsEmptyThenPollInvocationShouldReturnNull() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        assertNull(queue.poll());
        queue.push(2);
        assertThat(queue.poll(), is(2));
    }

    @Test
    public void whenUsedPushThenSizeQueueIsIncreases() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.sizeInbox(), is(3));
        assertThat(queue.size(), is(3));
        queue.push(4);
        assertThat(queue.sizeInbox(), is(4));
        assertThat(queue.size(), is(4));
    }

    @Test
    public void whenValueIsEnteredFirstThenItFirstCameOut() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(2);
        queue.push(5);
        queue.push(6);
        queue.push(9);
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(5));
        assertThat(queue.poll(), is(6));
        assertThat(queue.poll(), is(9));
        assertNull(queue.poll());
    }

    @Test
    public void whenUsedPollThenSizeQueueIsDecreases() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(2);
        queue.push(5);
        queue.push(9);
        assertThat(queue.sizeInbox(), is(3));
        assertThat(queue.sizeOutbox(), is(0));
        assertThat(queue.size(), is(3));

        assertThat(queue.poll(), is(2));
        assertThat(queue.sizeInbox(), is(0));
        assertThat(queue.sizeOutbox(), is(2));
        assertThat(queue.size(), is(2));

        assertThat(queue.poll(), is(5));
        assertThat(queue.sizeInbox(), is(0));
        assertThat(queue.sizeOutbox(), is(1));
        assertThat(queue.size(), is(1));
    }

}