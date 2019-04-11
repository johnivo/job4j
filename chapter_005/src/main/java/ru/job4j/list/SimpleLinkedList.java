package ru.job4j.list;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Динамический контейнер на базе связанного списка.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 11.04.2019
 */
public class SimpleLinkedList<E> implements Iterable<E>  {

    /**
     * Размер списка.
     */
    private int size;

    /**
     * Счётчик структурных изменений (для реализации fail-fast поведения итератора).
     */
    private int modCount;

    /**
     * Указатель на первый элемент списка.
     */
    private Node<E> first;

    /**
     * Метод добавляет новый элемент в начало списка.
     *
     * @param value заданный элемент.
     * @return true.
     */
    public boolean add(E value) {
        Node<E> newLink = new Node<>(value);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
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
        Node<E> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * Вспомогательный метод, получает размер списка.
     *
     * @return размер списка.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }

    /**
     * Возвращает итератор для последовательного прохода по элементам списка.
     *
     * @return переопределенный итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = SimpleLinkedList.this.first;
            private Node<E> lastReturned = null;
            int expectedModCount = SimpleLinkedList.this.modCount;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer < SimpleLinkedList.this.size;
            }

            @Override
            public E next() {
                if (this.expectedModCount != SimpleLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.lastReturned = this.current;
                this.current = this.current.next;
                this.pointer++;
                return this.lastReturned.item;
            }
        };
    }
}
