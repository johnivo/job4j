package ru.job4j.sort;

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
}
