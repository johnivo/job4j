package ru.job4j.tree;

import java.util.Optional;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 21.04.2019
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

}
