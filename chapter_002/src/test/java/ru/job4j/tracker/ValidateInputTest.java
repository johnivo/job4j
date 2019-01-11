package ru.job4j.tracker;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ValidateInputTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 11.01.2019
 */
public class ValidateInputTest {

    // буфер для результата.
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    // поле содержит дефолтный вывод в консоль.
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
        System.out.println("execute after method");
    }

    /**
     * тест NumberFormatException.
     */
    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        List<Integer> range = new ArrayList<>();
        range.add(1);
        input.ask("Enter", range);
        assertThat(
                this.mem.toString(),
                is(new StringBuilder()
                        .append("Please enter validate data again. ").append(System.lineSeparator())
                        .toString()
                )
        );
    }

    /**
     * тест MenuOutException.
     */
    @Test
    public void whenOutRangeInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"7", "1"})
        );
        List<Integer> range = new ArrayList<>();
        range.add(1);
        input.ask("Enter", range);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Please select key from menu. %n")
                )
        );
    }

}
