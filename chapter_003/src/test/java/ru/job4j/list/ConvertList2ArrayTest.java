package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
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
public class ConvertList2ArrayTest {

    /**
     * Test convert List to Array.
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * Test convert List to Array.
     */
    @Test
    public void when9ElementsThen10() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 9, 5, 6, 7, 8),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4, 9},
                {5, 6, 7, 8, 0},
        };
        assertThat(result, is(expect));
    }

    /**
     * Test convert to List.
     */
    @Test
    public void whenList3ArraysThenListInteger() {
        ConvertList2Array convertList = new ConvertList2Array();
        List<int[]> lists = new ArrayList<>();
        lists.add(new int[]{1, 2});
        lists.add(new int[]{3, 4, 5, 6});
        lists.add(new int[]{0, 1});
        List<Integer> result = convertList.convert(lists);
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4, 5, 6, 0, 1
        );
        assertThat(result, is(expect));
    }

    /**
     * Test convert to List.
     */
    @Test
    public void whenList2ArraysThenListInteger() {
        ConvertList2Array convertList = new ConvertList2Array();
        List<int[]> lists = Arrays.asList(
                new int[]{1, 2},
                new int[]{3, 4, 5, 6});
        List<Integer> result = convertList.convert(lists);
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );
        assertThat(result, is(expect));
    }
}
