package ru.job4j.school;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        map.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
        return map;
    }

    public List<Student> levelOf(List<Student> students, int bound) {
        List<Student> level = students.stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.comparing(Student::getScore).reversed().thenComparing(Student::getLastName))
                .takeWhile(e -> e.getScore() > bound)
                .collect(Collectors.toList());
        level.forEach(System.out::println);
        return level;
    }

    public List<Student> levelOf2(List<Student> students, int bound) {
        List<Student> level = students.stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.comparing(Student::getScore).thenComparing(Student::getLastName))
                .dropWhile(e -> e.getScore() < bound)
                .collect(Collectors.toList());
        level.forEach(System.out::println);
        return level;
    }

    //.map( e -> Stream.ofNullable(students))
    //.map(Stream::ofNullable)
    //.map(Student::getScore)
    //.flatMap( e -> Stream.ofNullable(students))
}
