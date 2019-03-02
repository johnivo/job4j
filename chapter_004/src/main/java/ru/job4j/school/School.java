package ru.job4j.school;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * School.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 02.03.2019
 */
public class School {

    public static Predicate<Student> isClassA() {
        return p -> p.getScore() >= 70 && p.getScore() <= 100;
    }

    public static Predicate<Student> isClassB() {
        return p -> p.getScore() >= 50 && p.getScore() <= 70;
    }

    public static Predicate<Student> isClassV() {
        return p -> p.getScore() > 0 && p.getScore() < 50;
    }

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        List<Student> part = students.stream().filter(
                predicate
        ).collect(Collectors.toList());
        part.forEach(System.out::println);
        return part;
    }
}
