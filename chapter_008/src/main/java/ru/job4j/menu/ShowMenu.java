package ru.job4j.menu;

import java.io.ByteArrayOutputStream;
import java.util.Set;

/**
 * Описывает вывод меню.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 06.08.2019
 */
public interface ShowMenu {

    void showMenu(Set<String> menu, ByteArrayOutputStream out);

}
