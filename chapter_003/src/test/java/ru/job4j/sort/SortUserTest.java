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
 *@since 02.02.2019
 */
public class SortUserTest {

    /**
     * Test sort with Comparable.
     */
    @Test
    public void whenSortedListFrom3UsersThenTreeSet() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("Olga", 24),
                        new User("Nik", 30),
                        new User("Bob", 21)

                )
        );
        Set<User> result = sortUser.sort(users);
        Set<User> expect = new TreeSet<>();
        expect.add(users.get(2));
        expect.add(users.get(0));
        expect.add(users.get(1));
        assertThat(result, is(expect));
    }

    /**
     * Test sort with Comparable.
     */
    @Test
    public void whenSortedListFrom4UsersThenTreeSet() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        User user1 = new User("Olga", 24);
        User user2 = new User("Max", 46);
        User user3 = new User("Nata", 15);
        User user4 = new User("Bob", 30);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        Set<User> sort = sortUser.sort(users);
        System.out.println(sort);
        String result = sort.iterator().next().getName();
        assertThat(result, is("Nata"));
    }
}
