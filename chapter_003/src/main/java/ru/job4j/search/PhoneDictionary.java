package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PhoneDictionary.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 24.01.2018
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        //List<Person> result = new ArrayList<>();
        // или var result = new ArrayList<>();
        // for (Person person : persons) {
        // или for (var person : persons) {
        //    if (person.getName().contains(key)
        //            || person.getSurname().contains(key)
        //            || person.getPhone().contains(key)
        //            || person.getAddress().contains(key)) {
        //        result.add(person);
        //    }
        //}
        //List<Person> result = persons.stream()
        var result = persons.stream()
                .filter(
                        e -> e.getName().contains(key)
                                || e.getSurname().contains(key)
                                || e.getPhone().contains(key)
                                || e.getAddress().contains(key)
                )
                .collect(Collectors.toList());
        return result;
    }
}
