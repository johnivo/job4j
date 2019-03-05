package ru.job4j.profiles;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 05.03.2019
 */
public class Profiles {

    List<Address> collect(List<Profile> profiles) {
        List<Address> list = profiles.stream().map(
            //profile -> profile.getAddress()
                Profile::getAddress
        ).collect(Collectors.toList());
        list.forEach(System.out::println);
        return list;
    }
}
