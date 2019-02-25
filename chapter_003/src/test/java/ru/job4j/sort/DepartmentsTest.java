package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 25.02.2019
 */
public class DepartmentsTest {
    private final String[] arrayDep = new String[]{
            "K1\\SK1",
            "K1\\SK2",
            "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2",
            "K2",
            "K2\\SK1\\SSK1",
            "K2\\SK1\\SSK2"
    };

    /**
     * Test sort with Comparable.
     */
    @Test
    public void whenSortUp() {
        ArrayList<String> deps = new ArrayList<>();
        deps.addAll(
                Arrays.asList(
                        arrayDep
                )
        );
        Departments sortDep = new Departments(deps);
        //System.out.println(deps);
        Set<String> result = sortDep.sortUp(deps);
        //System.out.println(result);
        Set<String> expect = new TreeSet<>();
        expect.addAll(
                Arrays.asList(
                        "K1",
                        "K1\\SK1",
                        "K1\\SK2",
                        "K1\\SK1\\SSK1",
                        "K1\\SK1\\SSK2",
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
        ArrayList<String> deps = new ArrayList<>();
        deps.addAll(
                Arrays.asList(
                        arrayDep
                )
        );
        Departments sortDep = new Departments(deps);
        //System.out.println(deps);
        List<String> result = sortDep.sortDown(deps);
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
}