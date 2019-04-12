package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 11.04.2019
 */
public class SimpleArrayList<E> implements Iterable<E> {

    /**
     * Буфер-массив, хранилище элементов списка.
     */
    private E[] container;

    /**
     * Начальная вместимость списка по-умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Количество добавленных элементов в список.
     */
    private int size;

    /**
     * Счётчик структурных изменений (для реализации fail-fast поведения итератора).
     */
    private int modCount;

    /**
     * Конструктор, формирует пустой лист с начальной вместимостью по-умолчанию.
     */
    public SimpleArrayList() {
        //this.container = new Object[DEFAULT_CAPACITY];
        this(DEFAULT_CAPACITY);
    }

    /**
     * Конструктор, формирует пустой лист с заданной начальной вместимостью.
     *
     * @param initialCapacity начальная вместимость.
     * @throws IllegalArgumentException при попытке задать отрицательную вместимость.
     */
    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.container = (E[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Добавляет элемент в конец листа.
     *
     * @param value
     * @return true.
     */
    public boolean add(E value) {
        if (size < container.length) {
            container[size++] = value;
        } else {
            container = grow();
            container[size++] = value;
        }
        modCount++;
        return true;
    }

    /**
     * Возвращает элемент из листа заданному по индексу.
     *
     * @param index
     * @return элемент.
     */
    public E get(int index) {
        if (size <= index || index < 0) {
            throw new IndexOutOfBoundsException("Позиция вне диапазона значений");
        }
        return (E) this.container[index];
    }

    /**
     * Вспомогательный метод, удваивает вместимость листа.
     *
     * @return увеличенный лист.
     */
    private E[] grow() {
        int oldCapacity = container.length;
        int newCapacity = oldCapacity * 2;
        return Arrays.copyOf(container, newCapacity);
    }

    /**
     * Вспомогательный метод, получает полный размер листа (с учетом пустых ячеек).
     *
     * @return размер листа (с учетом пустых ячеек).
     */
    public int size() {
        return this.container.length;
    }

    /**
     * Вспомогательный метод, получает размер заполненной части листа.
     *
     * @return размер листа.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Возвращает итератор для последовательного прохода по элементам списка.
     *
     * @return переопределенный итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int expectedModCount = SimpleArrayList.this.modCount;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer < SimpleArrayList.this.size;
            }

            @Override
            public E next() {
                if (this.expectedModCount != SimpleArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[pointer++];
            }
        };
    }
}
