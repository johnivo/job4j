package ru.job4j.sort;

import java.util.*;

/**
 * Class DepartmentsSort.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 3.03.2019
 */
public class DepartmentsSort {
    private List<String> departments;

    public DepartmentsSort(List<String> departments) {
        this.departments = departments;
    }

    public List<String> sortUp(List<String> departments) {
        departments.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                }
        );
        return departments;
    }

    public List<String> sortDown(List<String> departments) {
        departments.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int result = 0;
                        int min = Math.min(o1.length(), o2.length());
                        String a = o1.substring(0, min);
                        String b = o2.substring(0, min);
                        for (int i = 0; i < min; i++) {
                            result = b.compareTo(a);
                            if (result != 0) {
                                break;
                            }
                        }
                        return result != 0 ? result : Integer.compare(o1.length(), o2.length());
                    }
                }
        );
        return departments;
    }

    public List<String> addDep(List<String> departments) {
        List<String> allDep = new ArrayList<>(departments);
        for (String dep : departments) {
            for (int i = 0; i < dep.length(); i++) {
                if (dep.charAt(i) == '\\') {
                    String headDep = dep.substring(0, i);
                    if (!allDep.contains(headDep)) {
                        allDep.add(headDep);
                    }
                }
            }
        }
        return allDep;
    }
}