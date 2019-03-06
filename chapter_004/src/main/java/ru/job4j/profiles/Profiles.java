package ru.job4j.profiles;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 05.03.2019
 */
public class Profiles {

    List<Address> collect(List<Profile> profiles) {
        List<Address> list = profiles.stream()
                .map(Profile::getAddress)
                .sorted(Comparator.comparing(Address::getCity))
                .distinct().collect(Collectors.toList());
        list.forEach(System.out::println);
        return list;
    }
}
            //ссылки на метод вместо лямбд
            //.map(profile -> profile.getAddress())
            //.sorted(Comparator.comparing(add -> add.getCity()))
            //
            //.sorted(new Comparator<Address>() {
            //            @Override
            //            public int compare(Address o1, Address o2) {
            //                return o1.getCity().compareTo(o2.getCity());
            //            }
            //        }
            //)