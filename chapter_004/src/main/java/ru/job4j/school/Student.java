package ru.job4j.school;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Student.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 02.03.2019
 */
public class Student {

    private int score;

    public Student(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{"
                +
                "score=" + score
                +
                '}';
    }
}
