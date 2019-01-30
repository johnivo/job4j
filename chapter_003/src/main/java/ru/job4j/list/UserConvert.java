package ru.job4j.list;

import java.util.List;
import java.util.HashMap;

/**
 * Class UserConvert.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 30.01.2018
 */
public class UserConvert {

    /**
     * Метод конверитирует входящий List в Map.
     * Метод принимает список пользователей и конвертирует его в Map с ключом Integer id и соответствующим ему User.
     *
     * @param list входящий список пользователей типа List<User>.
     * @return hm типа HashMap<Integer, User>.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(),user);
        }
        return map;
    }
}