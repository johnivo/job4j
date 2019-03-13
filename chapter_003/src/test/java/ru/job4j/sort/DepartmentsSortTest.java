package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 3.03.2019
 */
public class DepartmentsSortTest {
    //private List<String> list = new ArrayList<>(
    //        Arrays.asList(
    //            "K1\\SK1",
    //            "K1\\SK2",
    //            "K1\\SK1\\SSK1",
    //            "K1\\SK1\\SSK2",
    //            "K2",
    //            "K2\\SK1\\SSK1",
    //            "K2\\SK1\\SSK2"
    //            )
    //        );
    private List<String> list = List.of(
                    "K1\\SK1",
                    "K1\\SK2",
                    "K1\\SK1\\SSK1",
                    "K1\\SK1\\SSK2",
                    "K2",
                    "K2\\SK1\\SSK1",
                    "K2\\SK1\\SSK2"
    );

    @Test
    public void whenSortUp() {
        DepartmentsSort sortDep = new DepartmentsSort();
        List<String> result = sortDep.sort(list.toArray(new String[list.size()]), true);
        //System.out.println(result);
        List<String> expect = List.of(
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDown() {
        DepartmentsSort sortDep = new DepartmentsSort();
        List<String> result = sortDep.sort(list.toArray(new String[list.size()]), false);
        //System.out.println(result);
        List<String> expect = List.of(
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        );
        assertThat(result, is(expect));
    }
}
