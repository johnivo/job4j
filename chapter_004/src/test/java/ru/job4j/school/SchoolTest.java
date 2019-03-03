package ru.job4j.school;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 02.03.2019
 */
public class SchoolTest {

    @Test
    public void whenFilterForListClassA() {
        Student st1 = new Student(90);
        Student st2 = new Student(80);
        Student st3 = new Student(65);
        Student st4 = new Student(60);
        Student st5 = new Student(55);
        Student st6 = new Student(30);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3, st4, st5, st6));
        School part = new School();
        int result = part.collect(students, p -> p.getScore() >= 70 && p.getScore() <= 100).size();
        assertThat(result, is(2));
    }

    @Test
    public void whenFilterForListClassB() {
        Student st1 = new Student(90);
        Student st2 = new Student(80);
        Student st3 = new Student(65);
        Student st4 = new Student(60);
        Student st5 = new Student(55);
        Student st6 = new Student(30);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3, st4, st5, st6));
        School part = new School();
        int result = part.collect(students, p -> p.getScore() >= 50 && p.getScore() <= 70).size();
        assertThat(result, is(3));
    }

    @Test
    public void whenFilterForListClassC() {
        Student st1 = new Student(90);
        Student st2 = new Student(80);
        Student st3 = new Student(65);
        Student st4 = new Student(60);
        Student st5 = new Student(55);
        Student st6 = new Student(30);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3, st4, st5, st6));
        School part = new School();
        List<Student> expect = new ArrayList<>();
        expect.addAll(
                Arrays.asList(st6)
        );
        List<Student> result = part.collect(students, p -> p.getScore() > 0 && p.getScore() < 50);
        assertThat(result, is(expect));
    }
}
