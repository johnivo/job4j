package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 04.04.2019
 */
public class SimpleArray<E> implements Iterable<E> {

    Object[] models;
    private int index;

    public SimpleArray(int size) {
        this.models = new Object[size];
    }

    public void add(E model) {
        this.models[index++] = model;
    }

    public void set(int position, E model) {
        check(position);
        this.models[position] = model;
    }

    public void remove(int position) {
        check(position);
        int size = models.length;
        System.arraycopy(models, position + 1, models, position, --size - position);
        models[size] = null;
    }

    public E get(int position) {
        check(position);
        return (E) this.models[position];
    }

    private void check(int position) {
        if (index <= position || position < 0) {
            throw new IndexOutOfBoundsException("Позиция вне диапазона значений");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer < index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) models[pointer++];
            }
        };
    }
}