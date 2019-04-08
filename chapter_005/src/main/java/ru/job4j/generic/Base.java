package ru.job4j.generic;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.04.2019
 */
public abstract class Base {

    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
