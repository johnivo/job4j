package ru.job4j.school;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * School.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 02.03.2019
 */
public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        List<Student> part = students.stream().filter(
                predicate
        ).collect(Collectors.toList());
        part.forEach(System.out::println);
        return part;
    }

    public Map<String, Student> convertToMap(List<Student> students) {
        Map<String, Student> map = students.stream()
                .distinct().collect(Collectors.toMap(
                        e -> e.getLastName(),
                        e -> e
                )
        );
        return map;
    }
}
