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

    class Org implements Comparable<Org> {

        private String code;

        public Org(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }

        @Override
        public int compareTo(Org o) {
            return this.code.compareTo(o.getCode());
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
            return this.code.equals(org.code);
        }

        @Override
        public int hashCode() {
            return  Objects.hash(code);
        }
    }

    class ReverseComporator implements Comparator<Org> {
        @Override
        public int compare(Org o1, Org o2) {
            int result = 0;
            int min = Math.min(o1.code.length(), o2.code.length());
            String a = o1.code.substring(0, min);
            String b = o2.code.substring(0, min);
            for (int i = 0; i < min; i++) {
                result = b.compareTo(a);
                if (result != 0) {
                    break;
                }
            }
            return result != 0 ? result : Integer.compare(o1.code.length(), o2.code.length());
        }
    }

    public List<String> sort(String[] codes, boolean key) {
        List<String> allDep = new ArrayList<>(Arrays.asList(codes));
        for (String dep : codes) {
            for (int i = 0; i < dep.length(); i++) {
                if (dep.charAt(i) == '\\') {
                    String headDep = dep.substring(0, i);
                      if (!allDep.contains(headDep)) {
                        allDep.add(headDep);
                    }
                }
            }
        }
        Set<Org> orgs;
        if (key) {
            orgs = new TreeSet<>();
        } else {
            orgs = new TreeSet<>(new ReverseComporator());
        }
        for (String s: allDep) {
            orgs.add(new Org(s));
        }

        List<String> result = new ArrayList<>();
        for (Org org: orgs) {
            result.add(org.getCode());
        }

        return result;
    }
}