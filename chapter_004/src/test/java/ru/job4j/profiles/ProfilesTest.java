package ru.job4j.profiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 05.03.2019
 */
public class ProfilesTest {

    @Test
    public void whenCollectThreeAddressesToList() {
        Address ad1 = new Address("B", "a", 1, 1);
        Address ad2 = new Address("C", "b", 2, 2);
        Address ad3 = new Address("A", "c", 3, 3);
        Address ad4 = new Address("A", "c", 3, 3);
        Profile pr1 = new Profile(ad1);
        Profile pr2 = new Profile(ad2);
        Profile pr3 = new Profile(ad3);
        Profile pr4 = new Profile(ad4);
        List<Profile> list = new ArrayList<>();
        list.addAll(Arrays.asList(pr1, pr2, pr3, pr4));
        Profiles profiles = new Profiles();
        int result = profiles.collect(list).size();
        assertThat(result, is(3));
    }

    @Test
    public void whenCollectTwoAddressesToList() {
        Profile pr1 = new Profile(new Address("B", "c", 2, 2));
        Profile pr2 = new Profile(new Address("B", "b", 1, 1));
        List<Profile> list = new ArrayList<>();
        list.addAll(Arrays.asList(pr1, pr2));
        Profiles profiles = new Profiles();
        Address result = profiles.collect(list).get(1);
        assertThat(new StringBuilder()
                        .append(result.getCity()).append(" ")
                        .append(result.getStreet()).append(" ")
                        .append(result.getHome()).append(" ")
                        .append(result.getApartment())
                        .toString(),
                is("B b 1 1"));
    }

    @Test
    public void whenCollectOneAddressesToList() {
        List<Profile> list = new ArrayList<>();
        list.addAll(
                Arrays.asList(
                        new Profile(new Address("B", "b", 1, 1)),
                        new Profile(new Address("B", "b", 1, 1)),
                        new Profile(new Address("B", "b", 1, 1))
                )
        );
        Profiles profiles = new Profiles();
        String result = profiles.collect(list).get(0).getStreet();
        assertThat(result, is("b"));
    }
}