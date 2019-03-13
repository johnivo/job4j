package ru.job4j.school;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        Student st1 = new Student("A", 90);
        Student st2 = new Student("B", 80);
        Student st3 = new Student("C", 65);
        Student st4 = new Student("D", 60);
        Student st5 = new Student("E", 55);
        Student st6 = new Student("F", 30);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3, st4, st5, st6));
        School part = new School();
        int result = part.collect(students, p -> p.getScore() >= 70 && p.getScore() <= 100).size();
        assertThat(result, is(2));
    }

    @Test
    public void whenFilterForListClassB() {
        Student st1 = new Student("A", 90);
        Student st2 = new Student("B", 80);
        Student st3 = new Student("C", 65);
        Student st4 = new Student("D", 60);
        Student st5 = new Student("E", 55);
        Student st6 = new Student("F", 30);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3, st4, st5, st6));
        School part = new School();
        int result = part.collect(students, p -> p.getScore() >= 50 && p.getScore() <= 70).size();
        assertThat(result, is(3));
    }

    @Test
    public void whenFilterForListClassC() {
        Student st1 = new Student("A", 90);
        Student st2 = new Student("B", 80);
        Student st3 = new Student("C", 65);
        Student st4 = new Student("D", 60);
        Student st5 = new Student("E", 55);
        Student st6 = new Student("F", 30);
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

    @Test
    public void whenListConvertToMap() {
        Student st1 = new Student("A", 90);
        Student st2 = new Student("C", 80);
        Student st3 = new Student("B", 65);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3));
        School school = new School();
        int result = school.convertToMap(students).size();
        assertThat(result, is(3));
    }

    @Test
    public void whenListConvertToMap2() {
        Student st1 = new Student("B", 90);
        Student st2 = new Student("A", 80);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2));
        School school = new School();
        Map<String, Student> map = school.convertToMap(students);
        boolean result = map.keySet().contains("B");
        //for (Map.Entry<String, Student> entry : map.entrySet()) {
        //    System.out.println(entry);
        //}
        //map.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
        assertThat(result, is(true));
    }

    @Test
    public void whenLevelOfGreaterUpperBoundThen() {
        Student st1 = new Student("E", 90);
        Student st2 = new Student("B", 90);
        Student st3 = null;
        Student st4 = new Student("D", 50);
        Student st5 = new Student("A", 75);
        Student st6 = new Student("C", 30);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3, st4, st5, st6));
        School school = new School();
        List<Student> lvl = school.levelOf(students, 60);
        int result = lvl.size();
        assertThat(result, is(3));
    }

    @Test
    public void whenLevelOfGreaterUpperBoundThen2() {
        Student st1 = new Student("E", 90);
        Student st2 = new Student("B", 90);
        Student st3 = null;
        Student st4 = new Student("D", 50);
        Student st5 = new Student("A", 75);
        Student st6 = new Student("C", 30);
        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(st1, st2, st3, st4, st5, st6));
        School school = new School();
        List<Student> lvl = school.levelOf2(students, 60);
        boolean result = lvl.contains(st5);
        assertThat(result, is(true));
    }

}
