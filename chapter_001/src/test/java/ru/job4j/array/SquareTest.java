package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SquareTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 13.12.2018
 */
public class SquareTest {

    /**
     * тест заполнения массива тремя числами возведенными в квадрат.
     */
    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expect = new int[] {1, 4, 9};
        assertThat(rst, is(expect));
    }

    /**
     * тест заполнения массива 4 числами возведенными в квадрат.
     */
    @Test
    public void whenBound4Then14916() {
        int bound = 4;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expect = new int[] {1, 4, 9, 16};
        assertThat(rst, is(expect));
    }

    /**
     * тест заполнения массива 6 числами возведенными в квадрат.
     */
    @Test
    public void whenBound6Then149162536() {
        int bound = 6;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expect = new int[] {1, 4, 9, 16, 25, 36};
        assertThat(rst, is(expect));
        for (int x : rst) {
            System.out.print(x);
            System.out.print(" ");
        }
    }
}