package ru.job4j.menu;

/**
 * Описывает работу с пунктом меню - выбор и выполнение действия.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 06.08.2019
 */
public interface ExecuteMenuItem {

    void select(String name);

    void execute(MenuItem menuItem);

}
