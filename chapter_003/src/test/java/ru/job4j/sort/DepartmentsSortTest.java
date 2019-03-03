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
    private List<String> departments = new ArrayList<>(
            Arrays.asList(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
                )
            );

    @Test
    public void whenAddDep() {
        DepartmentsSort sortDep = new DepartmentsSort(departments);
        int result = sortDep.addDep(departments).size();
        //List<String> res = sortDep.addDep(departments);
        //System.out.println(res);
        assertThat(result, is(9));
    }

    @Test
    public void whenSortUp() {
        DepartmentsSort sortDep = new DepartmentsSort(departments);
        List<String> result = sortDep.sortUp(departments);
        List<String> expect = new ArrayList<>();
        expect.addAll(
                Arrays.asList(
                        "K1",
                        "K1\\SK1",
                        "K1\\SK1\\SSK1",
                        "K1\\SK1\\SSK2",
                        "K1\\SK2",
                        "K2",
                        "K2\\SK1",
                        "K2\\SK1\\SSK1",
                        "K2\\SK1\\SSK2"
                )
        );
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDown() {
        DepartmentsSort sortDep = new DepartmentsSort(departments);
        List<String> result = sortDep.sortDown(departments);
        //System.out.println(result);
        List<String> expect = new ArrayList<>();
        expect.addAll(
                Arrays.asList(
                        "K2",
                        "K2\\SK1",
                        "K2\\SK1\\SSK2",
                        "K2\\SK1\\SSK1",
                        "K1",
                        "K1\\SK2",
                        "K1\\SK1",
                        "K1\\SK1\\SSK2",
                        "K1\\SK1\\SSK1"
                )
        );
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDownTwo() {
        DepartmentsSort sortDep = new DepartmentsSort(departments);
        List<String> result = sortDep.sortDown(departments);
        String[] array = result.toArray(new String[result.size()]);
        String[] expect = {
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        };
        assertThat(array, is(expect));
    }
}
