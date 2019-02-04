package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class SortUser.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 02.02.2018
 */
public class SortUser {

    /**
     * Метод принимает список пользователей и сортирует по возрасту в порядке возрастания.
     * Сортировка List в TreeSet.
     *
     * @param list входящий список пользователей типа List<User>.
     * @return отсортированный TreeSet пользователей.
     */
    Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Метод принимает список и сортирует его по длине имени.
     *
     * @param list входящий список пользователей типа List<User>.
     * @return отсортированный лист.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return Integer.compare(o1.getName().length(), o2.getName().length());
                    }
                }
        );
        return list;
    }

    /**
     * Метод принимает список и сортирует его сначала по имени в лексикографическом порядке, потом по возрасту.
     *
     * @param list входящий список пользователей типа List<User>.
     * @return отсортированный лист.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        Integer rst = o1.getName().compareTo(o2.getName());
                        return rst != 0 ? rst : Integer.compare(o1.getAge(), o2.getAge());
                    }
                }
        );
        return list;
    }
}
