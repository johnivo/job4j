package ru.job4j.generic;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.04.2019
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
