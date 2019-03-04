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

    class Org {

        private List<String> list;

        public Org(List<String> list) {
            this.list = list;
        }

        public List<String> getList() {
            return this.list;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Org org = (Org) o;
            return this.list.equals(org.list);
        }

        @Override
        public int hashCode() {
            return  Objects.hash(list);
        }
    }

    public List<String> sort(List<String> list, boolean key) {
        List<String> allDep = new ArrayList<>(list);
        for (String dep : list) {
            for (int i = 0; i < dep.length(); i++) {
                if (dep.charAt(i) == '\\') {
                    String headDep = dep.substring(0, i);
                    if (!allDep.contains(headDep)) {
                        allDep.add(headDep);
                    }
                }
            }
        }
        Org orgs = new Org(allDep);
        if (key) {
            orgs.getList().sort(
                    new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareTo(o2);
                        }
                    }
            );
        } else {
            orgs.getList().sort(
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
        }
        return orgs.getList();
    }
}