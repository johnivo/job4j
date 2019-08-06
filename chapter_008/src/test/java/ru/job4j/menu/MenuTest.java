package ru.job4j.menu;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 06.08.2019
 */
public class MenuTest {

    private static final String LN = System.lineSeparator();

    /**
     * Заполняет входные данные.
     * @param menu карта имя=пункт.
     * @return db карта имя=пункт.
     */
    public Map<String, MenuItem> fill(MenuItem ... menu) {
        List<MenuItem> list = List.of(menu);
        Map<String, MenuItem> db = list.stream()
                .collect(Collectors.toMap(MenuItem::getName, Function.identity()));

        return db;
    }

    @Test
    public void whenCreateMenuThenGetTreeOfMenu() {
        MenuItem mi1 = new MenuItem("Пункт 1.", Arrays.asList("1.", "2."));
        MenuItem mi2 = new MenuItem("Пункт 2.");
        MenuItem mi3 = new MenuItem("Пункт 3.", Arrays.asList("1.", "2."));
        MenuItem mi4 = new MenuItem("Пункт 3.1.", Arrays.asList("1.", "2."));
        MenuItem mi5 = new MenuItem("Пункт 4.", Arrays.asList("1.", "2."));

        List<MenuItem> list = List.of(mi1, mi2, mi3, mi4, mi5);
        Map<String, MenuItem> db = this.fill(mi1, mi2, mi3, mi4, mi5);

        String expected = Joiner.on(LN).join(
                "Пункт 1.",
                "---Пункт 1.1.",
                "---Пункт 1.2.",
                "Пункт 2.",
                "Пункт 3.",
                "---Пункт 3.1.",
                "---Пункт 3.1.1.",
                "---Пункт 3.1.2.",
                "---Пункт 3.2.",
                "Пункт 4.",
                "---Пункт 4.1.",
                "---Пункт 4.2.",
                ""
        );

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(out);
        ) {
            System.setOut(ps);

            Menu menu = new Menu(list);
            menu.showMenu(menu.create(db), out);

            assertThat(out.toString(), is(expected));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}