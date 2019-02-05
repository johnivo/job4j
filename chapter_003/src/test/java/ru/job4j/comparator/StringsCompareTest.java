package ru.job4j.comparator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 05.02.2019
 */
public class StringsCompareTest {
    @Test
    public void whenStringsAreEqualThenZero() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeMinusOne() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, is(-1));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBeOne() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Ivanovsky",
                "Ivanov"
        );
        assertThat(rst, is(1));
    }

    @Test
    public void whenFirstCharLeftGreaterThanRightResultShouldBePositive() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void whenSecondCharOfLeftGreaterThanRightShouldBePositive() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        //System.out.println(" 5 : " + rst);
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void whenThirdCharOfLeftLessThanRightShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Pearova",
                "Petrov"
        );
        //System.out.println(" 6 : " + rst);
        assertThat(rst, lessThan(0));
    }
}