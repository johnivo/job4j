package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 25.01.2019
 */
public class PriorityQueueTest {

    /**
     * Test find by name.
     */
    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriority2() {
        var queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        //Task result = queue.take();
        var result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}
