package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.04.2019
 */
public class SimpleSet<E> implements Iterable<E> {

    /**
     * Используем функциональные возможности класса SimpleArrayList.
     * Представляющего реализацию динамического списка на базе массива.
     */
    private SimpleArrayList<E> list = new SimpleArrayList();

    /**
     * Добавляет элемент в множество, если отсутствуют дубликаты.
     *
     * @param value
     * @return true.
     */
    public void add(E value) {
        if (!contains(value)) {
            this.list.add(value);
        }
    }

    /**
     * Проверяет наличие заданного значения в множестве.
     *
     * @param value искомое значение.
     * @return true, если объект присутствует.
     */
    public boolean contains(E value) {
        boolean result = false;
        for (E e : this.list) {
            //if (value != null && value.equals(e) || value == null && e == null) {
            if (Objects.equals(e, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает итератор для прохода по элементам множества.
     *
     * @return итератор для динамического списка.
     */
    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    /**
     * Вспомогательный метод, получает размер множества.
     */
    public int size() {
        return this.list.getSize();
    }

    /**
     * Вспомогательный метод, получает элемент множества по заданному индексу.
     */
    public E get(int index) {
        return this.list.get(index);
    }
}
