package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 27.01.2019
 */
public class ConvertMatrix2ListTest {

    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void when2on3ArrayThenList6() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        Integer[][] input = {
                {7, 8, 7},
                {6, 4, 5},
        };
        List<Integer> expect = Arrays.asList(
                7, 8, 7, 6, 4, 5
        );
        List<Integer> result = list.toList2(input);
        assertThat(result, is(expect));
    }
}
