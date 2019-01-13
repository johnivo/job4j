package ru.job4j.tracker;


/**
 * Абстрактный класс для дублирующихся методов.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 14.01.2019
 */
public abstract class BaseAction implements UserAction {

    /**
     * Значение ключа.
     */
    private final int key;

    /**
     * Наименование действия.
     */
    private final String name;

    /**
     * Конструтор, инициализирующий поля.
     *
     * @param key ключ.
     * @param name действие.
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}