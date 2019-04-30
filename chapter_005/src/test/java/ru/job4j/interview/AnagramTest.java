package ru.job4j.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.04.2019
 */
public class AnagramTest {

    @Test
    public void isAnagramTest() {
        Anagram anagram = new Anagram();
        assertThat(anagram.check("мама", "амма"), is(true));
        assertThat(anagram.check("мама", "мааа"), is(false));
        assertThat(anagram.check(null, "мама"), is(false));
        assertThat(anagram.check("мама", null), is(false));
        assertThat(anagram.check(null, null), is(true));
        assertThat(anagram.check("", ""), is(true));
        assertThat(anagram.check("my anagram.", "my .nagaram"), is(true));
    }
}