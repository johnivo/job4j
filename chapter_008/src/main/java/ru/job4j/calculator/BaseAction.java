package ru.job4j.calculator;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.07.2019
 */
public abstract class BaseAction implements UserAction {

    /**
     * Значение ключа.
     */
    private final String key;

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
    protected BaseAction(final String key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
