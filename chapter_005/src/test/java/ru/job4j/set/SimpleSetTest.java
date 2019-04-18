package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.04.2019
 */
public class SimpleSetTest {

    @Test
    public void whenDuplicatesAreAddedThenTheyIgnore() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(5);
        set.add(5);
        set.add(3);
        set.add(3);
        for (Integer e : set) {
            System.out.println(e);
        }
        assertThat(set.size(), is(3));
    }

    @Test
    public void whenNullsAddedThenOnlyFirstsNullIsAdded() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(null);
        set.add(null);
        set.add(5);
        assertThat(set.size(), is(3));
        assertThat(set.get(2), is(5));
    }
}