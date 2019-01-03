package ru.job4j.pseudo;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 02.01.2019
 */
public class TriangleTest {

    /**
     * тест TriangleTest.
     */
    @Test
    public void whenDrawSquare() {
        Triangle triangle = new Triangle();
        System.out.println(triangle.draw());
        assertThat(
                triangle.draw(),
                is(new StringBuilder()
                        .append("  +  \n")
                        .append(" +++ \n")
                        .append("+++++\n")
                        .toString()
                )
        );
    }
}
