package ru.job4j.interactcalc;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.07.2019
 */
public class InteractCalcTest {

    /**
     * Буфер для результата.
     */
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();

//    /**
//     * Дефолтный вывод в консоль.
//     */
//    private final PrintStream out = System.out;

    /**
     * Заменяет стандартный вывод System.out на mem.
     */
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(mem);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    /**
     * Разделитель строк.
     */
    private static final String LN = System.lineSeparator();

    /**
     * Add test.
     */
    @Test
    public void whenTo1Added2Then3() {
        final Input input = new StubInput(new String[] {"1", "+", "2", LN});
        final Calculator calc = new Calculator();
        final InteractCalc interactCalc = new InteractCalc(input, calc, output);

        interactCalc.action();

        assertThat(calc.getResult(), is(3.0));
    }

    /**
     * Subtraction test.
     */
    @Test
    public void whenFrom2Subtracted1Then1() {
        final Input input = new StubInput(new String[] {"2", "-", "1", LN});
        final Calculator calc = new Calculator();
        final InteractCalc interactCalc = new InteractCalc(input, calc, output);

        interactCalc.action();

        assertThat(calc.getResult(), is(1.0));
    }

    /**
     * Multiplication test.
     */
    @Test
    public void when2MultipliedBy2Then4() {
        final Input input = new StubInput(new String[] {"2", "*", "2", LN});
        final Calculator calc = new Calculator();
        final InteractCalc interactCalc = new InteractCalc(input, calc, output);

        interactCalc.action();

        assertThat(calc.getResult(), is(4.0));
    }

    /**
     * Division test.
     */
    @Test
    public void when6DividedBy2Then3() {
        final Input input = new StubInput(new String[] {"6", "/", "2", LN});
        final Calculator calc = new Calculator();
        final InteractCalc interactCalc = new InteractCalc(input, calc, output);

        interactCalc.action();

        assertThat(calc.getResult(), is(3.0));
    }

    /**
     * Combination operation test.
     */
    @Test
    public void whenTestReselectTwice() {
        final Input input = new StubInput(new String[] {"1", "+", "3", "=", "=", LN});
        final Calculator calc = new Calculator();
        final InteractCalc interactCalc = new InteractCalc(input, calc, output);

        interactCalc.action();

        assertThat(calc.getResult(), is(10.0));
    }

    /**
     * Combination operation test.
     */
    @Test
    public void whenTestReselectAndReuseOfPreviousCalculation() {
        final Input input = new StubInput(new String[] {"1", "+", "3", "=", "m", "-", "3", LN});
        final Calculator calc = new Calculator();
        final InteractCalc interactCalc = new InteractCalc(input, calc, output);

        interactCalc.action();

        assertThat(calc.getResult(), is(4.0));
    }

    /**
     * Combination operation test.
     */
    @Test
    public void whenTestReselectAndNewCalculation() {
        final Input input = new StubInput(new String[] {"1", "+", "3", "=", "n", "0", "+", "0", LN});
        final Calculator calc = new Calculator();
        final InteractCalc interactCalc = new InteractCalc(input, calc, output);

        interactCalc.action();

        assertThat(calc.getResult(), is(0.0));
    }
}