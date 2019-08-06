package ru.job4j.menu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Структура для поддержания меню.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 05.08.2019
 */
public class Menu implements IMenu {

    /**
     * Список пунктов меню.
     */
    private List<MenuItem> menu;

    /**
     * Конструктор.
     * @param menu список пунктов.
     */
    public Menu(List<MenuItem> menu) {
        this.menu = menu;
    }

    /**
     * Возвращает упорядоченную структуру меню.
     *
     * В очередь добавляем ключи-имена из карты.
     * Пока очередь имен не пуста, берем первый элемент из очереди.
     * Если в очереди нет такого имени, находим в карте соответствующий имени пункт меню
     * Проверяем список имен подпунктов, если подпункты есть - добавляем их в очередь.
     * Добавляем в дерево имя пункта.
     *
     * @param menu карта имя=пункт.
     * @return fullMenu упорядоченное дерево меню.
     */
    @Override
    public Set<String> create(Map<String, MenuItem> menu) {
        Set<String> fullMenu = new TreeSet<>();
        Queue<String> data = new LinkedList<>(menu.keySet());
        while (!data.isEmpty()) {
            String name = data.poll();
            if (!data.contains(name)) {
                MenuItem submenu = menu.get(name);
                List<String> list = submenu.getItems();
                if (list != null) {
                    list.stream().forEach((s) -> fullMenu.add(name.concat(s)));
                }
                fullMenu.add(submenu.getName());
            }
        }
        return fullMenu;
    }

    /**
     * Вывод меню.
     * @param menu меню.
     */
    @Override
    public void showMenu(Set<String> menu, ByteArrayOutputStream out) {
        try (PrintStream ps = new PrintStream(out)) {

            System.setOut(ps);

            Integer tab = ((TreeSet<String>) menu).first().length();
            for (String s : menu) {
                if (s.length() > tab) {
                    ps.println("---".concat(s));
                } else {
                    ps.println(s);
                    tab = s.length();
                }
            }
        }
    }

    /**
     * Выбор пункта меню.
     * @param name имя пункта.
     */
    @Override
    public void select(String name) {
        MenuItem menuItem = null;
        int i = 0;
        while (menuItem == null && menu != null && i < menu.size()) {
            if (menu.get(i++).getName().equals(name)) {
                menuItem = menu.get(i++);
            }
        }
        execute(menuItem);
    }

    /**
     * Выполняет действие по заданному пункту меню.
     * @param menuItem пункт меню.
     */
    @Override
    public void execute(MenuItem menuItem) {
        //реализация действия
    }

}
