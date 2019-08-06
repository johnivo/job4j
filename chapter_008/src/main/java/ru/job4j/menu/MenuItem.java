package ru.job4j.menu;

import java.util.*;

/**
 * Описание пункта меню.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 05.08.2019
 */
public class MenuItem {

    /**
     * Имя пункта меню.
     */
    private String name;

    /**
     * Список имен подпунктов - пп.
     */
    private List<String> items;

    /**
     * Конструктор, пункт меню не содержит пп.
     * @param name имя пункта.
     */
    public MenuItem(String name) {
        this.name = name;
    }

    /**
     * Конструктор, пункт меню содержит пп.
     * @param name имя пункта.
     * @param items список пп.
     */
    public MenuItem(String name, List<String> items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "items=" + items + '}';
    }

    /**
     * Возвращает имя пункта.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Возвращает список пп.
     */
    public List<String> getItems() {
        return this.items;
    }

}
