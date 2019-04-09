package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.04.2019
 */
public class UnidirectionalLinkedListTest {

    private UnidirectionalLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new UnidirectionalLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetZeroResultTwo() {
        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenTwiceUseDeleteThenUseGetZeroResultOne() {
        assertThat(list.delete(), is(3));
        assertThat(list.delete(), is(2));
        assertThat(list.get(0), is(1));
    }

    @Test
    public void whenUseDeleteThenUseGetSizeResultTwo() {
        list.delete();
        assertThat(list.getSize(), is(2));
    }
}