package ru.job4j.statistics;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 25.04.2019
 */
public class AnalizeTest {

    @Test
    public void whenAdded4ElAndChanged2ElAndDeleted1ElThenStatistics4And2And1() {
        Analize info = new Analize();
        List<Analize.User> previous = Arrays.asList(
                new Analize.User(1, "old"),
                new Analize.User(2, "old"),
                new Analize.User(3, "old"),
                new Analize.User(4, "old")
        );
        List<Analize.User> current = Arrays.asList(
                new Analize.User(1, "replace"),
                new Analize.User(2, "replace"),
                new Analize.User(3, "old"),
                new Analize.User(10, "new"),
                new Analize.User(11, "new"),
                new Analize.User(12, "new"),
                new Analize.User(13, "new")
        );
        Analize.Info summary = info.diff(previous, current);
        System.out.println(summary);
        assertThat(summary.getChanged(), is(2));
        assertThat(summary.getDeleted(), is(1));
        assertThat(summary.getAdded(), is(4));
    }
}