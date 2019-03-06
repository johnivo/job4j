package ru.job4j.school;

/**
 * Student.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 02.03.2019
 */
public class Student {

    private String lastName;
    private int score;

    public Student(String lastName, int score) {
        this.lastName = lastName;
        this.score = score;
    }

    public String getLastName() {
        return lastName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" + "lastName='" + lastName + '\'' + ", score=" + score + '}';
    }
}
