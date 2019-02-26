package ru.job4j.sort;

import java.util.*;

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
     * Метод добавляет недостающие в иерархии коды подразделений.
     *
     * @param departments входящий список кодов.
     * @return полнная иерархия кодов (несортированное множество).
     */
    public Set<String> addDep(ArrayList<String> departments) {
        Set<String> deps = new HashSet<>(departments);
        for (String dep : departments) {
            for (int i = 0; i < dep.length(); i++) {
                if (dep.charAt(i) == '\\') {
                    String headDep = dep.substring(0, i);
                    if (!departments.contains(headDep))
                        deps.add(headDep);
                }
            }
        }
        return deps;
    }

    /**
     * Метод выполняет сортировку по возрастанию.
     *
     * @param departments входящий список кодов.
     * @return отсортированное множество кодов.
     */
    public Set<String> sortUp(ArrayList<String> departments) {
        Set<String> sortedDep = new TreeSet<>(departments);
        sortedDep.addAll(addDep(departments));
        return sortedDep;
    }

    /**
     * Метод выполняет сортировку по убыванию.
     *
     * @param departments входящий список кодов.
     * @return отсортированный по убыванию список кодов.
     */
    public List<String> sortDown(ArrayList<String> departments) {
        Set<String> setDep = sortUp(departments);
        List<String> listDep = new ArrayList<>(setDep);
        listDep.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int result = 0;
                        int min = Math.min(o1.length(), o2.length());
                        String a = o1.substring(0, min);
                        String b = o2.substring(0, min);
                        return b.compareTo(a);
                    }
                }
        );
        return listDep;
    }
    /**
    public List<String> sortDown(ArrayList<String> departments) {
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
    */
}