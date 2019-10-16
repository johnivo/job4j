package ru.job4j.list;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.10.2019
 */
public interface BaseList<E> extends Iterable<E> {

    boolean add(E value);

    E get(int index);

    E[] grow();



}
