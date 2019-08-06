package ru.job4j.menu;

import java.util.Map;
import java.util.Set;

/**
 * Описывает создание меню.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 06.08.2019
 */
public interface CreateMenu {

     Set<String> create(Map<String, MenuItem> menu);

}
