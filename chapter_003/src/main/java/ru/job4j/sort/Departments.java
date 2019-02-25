package ru.job4j.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

/**
 * Class Departments.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 25.02.2018
 */
public class Departments {

    /**
     * departments список кодов подразделений.
     */
    private ArrayList<String> departments;

    /**
     * Конструктор.
     *
     * @param departments .
     */
    public Departments(ArrayList<String> departments) {
        this.departments = departments;
    }

    /**
     * Геттер к списку.
     */
    public ArrayList<String> getDepartments() {
        return this.departments;
    }

    /**
     * Метод принимает список кодов подразделений и добавляет недостающие в иерархии.
     * выполняет сортировку natural ordering.
     *
     * @param departments входящий список кодов.
     * @return множество кодов.
     */
    Set<String> sortUp(ArrayList<String> departments) {
        Set<String> sortedDep = new TreeSet<>();
        for (String dep : departments) {
            for (int i = 0; i < dep.length(); i++) {
                if (dep.charAt(i) == '\\') {
                    sortedDep.add(dep.substring(0, i));
                }
            }
            if (dep.endsWith("1") || dep.endsWith("2")) {
                sortedDep.add(dep);
            }
        }
        return sortedDep;
    }

    /**
     * Метод принимает список кодов подразделений и добавляет недостающие в иерархии.
     * выполняет сортировку по убыванию.
     *
     * @param departments входящий список кодов.
     * @return отсортированный список.
     */
    List<String> sortDown(ArrayList<String> departments) {
        Set<String> setDep = sortUp(departments);
        List<String> listDep = new ArrayList<>(setDep);
        listDep.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int result = 0;
                        int min = Math.min(o1.length(), o2.length());
                        for (int i = 0; i < min; i++) {
                            if (o1.charAt(i) != o2.charAt(i)) {
                                result = o2.charAt(i) - o1.charAt(i);
                                break;
                            }
                        }
                        return result;
                    }
                }
        );
        return listDep;
    }
}