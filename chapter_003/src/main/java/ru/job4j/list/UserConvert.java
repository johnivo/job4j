package ru.job4j.list;

import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

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
        //for (User user : list) {
        //    maps.put(user.getId(), user);
        //}
        map = (HashMap<Integer, User>) list
                .stream()
                .collect(Collectors.toMap(User::getId, e -> e)
                );
        return map;
    }
}