package ru.job4j.list;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 30.01.2019
 */
public class UserConvertTest {

    /**
     * Test convert List to Map.
     */
    @Test
    public void whenListFrom3UsersThenMap() {
        UserConvert userConvert = new UserConvert();
        List<User> lists = new ArrayList<>();
        User user1 = new User(10, "Olga", "London");
        User user2 = new User(12, "Nik", "Moscow");
        User user3 = new User(3, "Bob", "New York");
        lists.add(user1);
        lists.add(user2);
        lists.add(user3);
        HashMap<Integer, User> result = userConvert.process(lists);
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(10, lists.get(0));
        expect.put(12, lists.get(1));
        expect.put(3, user3);
        assertThat(result, is(expect));
    }

    /**
     * Test convert List to Map.
     */
    @Test
    public void whenListFrom2UsersThenMap() {
        UserConvert userConvert = new UserConvert();
        List<User> lists = new ArrayList<>();
        User user1 = new User(10, "Olga", "London");
        User user2 = new User(12, "Nik", "Moscow");
        HashMap<Integer, User> result = userConvert.process(Arrays.asList(user1, user2));
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(10, user1);
        expect.put(12, user2);
        assertThat(result, is(expect));
    }

    /**
     * Test convert List to Map.
     */
    @Test
    public void whenListFrom2UsersThenMapUserWithId12GetCity() {
        UserConvert userConvert = new UserConvert();
        List<User> lists = new ArrayList<>();
        User user1 = new User(10, "Olga", "London");
        User user2 = new User(12, "Nik", "Moscow");
        lists.add(user1);
        lists.add(user2);
        HashMap<Integer, User> map = userConvert.process(lists);
        String result = map.get(12).getCity();
        assertThat(result, is("Moscow"));
    }
}