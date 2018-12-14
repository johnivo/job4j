package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MatrixTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 14.12.2018
 */
public class MatrixTest {

    /**
     * тест проверяет заполнение таблицы умножения заданного размера.
     */
    @Test
    public void when3on3() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(3);
        int[][] expect = {
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        };
        assertThat(table, is(expect));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}