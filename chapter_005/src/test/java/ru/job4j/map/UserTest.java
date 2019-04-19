package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.04.2019
 */
public class UserTest {

    @Test
    public void testUserModelWithOverridingEqualsAndHashCode() {
        Calendar birthday = new GregorianCalendar(2018, 11, 28);
        User first = new User("John", 2, birthday);
        User second = new User("John", 2, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(first, "one");
        map.put(second, "two");
        System.out.println(map);
        assertThat(first.equals(second), is(true));
        assertThat(map.size(), is(1));
    }

    @Test
    public void testUserModelWithoutOverridingEqualsAndHashCode() {
        Calendar birthday = new GregorianCalendar(2018, 11, 28);
        User first = new User("John", 2, birthday);
        User second = new User("John", 2, birthday);

        Map<User, Object> mapA = new HashMap<>();
        mapA.put(first, "one");
        mapA.put(second, "two");
        System.out.println(mapA + " size " + mapA.size()); //2 до переопределения, 1 после.

        Map<User, Object> mapB = new HashMap<>();
        mapB.put(first, "one");
        mapB.put(first, "two");
        System.out.println(mapB + " size " + mapB.size()); //1, перезапишется, т.к. ключ это один и тот же объект.
    }
}