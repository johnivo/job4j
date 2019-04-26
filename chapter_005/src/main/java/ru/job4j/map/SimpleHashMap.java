package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Ассоциативный массив на базе хэш-таблицы.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 20.04.2019
 */
public class SimpleHashMap<K, V> implements Iterable<V> {

    /**
     * Начальная вместимость по-умолчанию (1 << 4 = 16).
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * Коэффициент загрузки, при его достижении размер массива удваивается.
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Массив для хранения пар ключ-значение.
     */
    private Node<K, V>[] table;

    /**
     * Количество добавленных элементов в массив.
     */
    private int size;

    /**
     * Счётчик структурных изменений (для реализации fail-fast поведения итератора).
     */
    private int modCount;

    /**
     * Конструктор формирует пустую карту с начальной вместимостью по-умолчанию.
     */
    public SimpleHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Конструктор формирует пустую карту с заданной начальной вместимостью.
     *
     * @param initialCapacity начальная вместимость.
     * @throws IllegalArgumentException при попытке задать некорректную вместимость.
     */
    public SimpleHashMap(int initialCapacity) {
        if (initialCapacity < 0 && initialCapacity % 2 != 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.resize(initialCapacity);
    }

    /**
     * Хеш-функция на основе хеш-кода ключа.
     * Сдвигаем старшие разряды числа(начального хеш-кода ключа) вправо на 16 позиций (>>> 16)
     * и выполняем операцию XOR (^ побитовое логическое или).
     * Этим страхуемся от неудачной функции hashcode().
     *
     * @param key ключ.
     * @return hash значение хеш-функции.
     */
    private int hash(K key) {
        int hash = 0;
        if (key != null) {
            hash = key.hashCode();
            hash ^= hash >>> 16;
        }
        return hash;
    }

    /**
     * Вычисляет корзину/индекс ячейки/бакет в массиве, в которой будет храниться новый элемент.
     *
     * @param hash результат хеш-фукнции для нового элемента.
     * @param length количество ячеек/размер массива.
     * @return индекс ячейки.
     */
    private int index(int hash, int length) {
        return (length - 1) & hash;
    }

    /**
     * Добавляет в карту новый объект на основе заданной пары ключ-значение.
     *
     * @param key ключ.
     * @param value значение.
     * @return true.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int i = this.index(this.hash(key), this.table.length);
        if (this.table[i] == null) {
            this.table[i] = new Node<>(key, value);
            this.size++;
            this.modCount++;
            result = true;
            if (this.size >= DEFAULT_LOAD_FACTOR * this.table.length) {
                resize(this.table.length << 1);
            }
        } else {
            if (key != null && key.equals(this.table[i].key)) {
                this.table[i] = new Node<>(key, value);
                this.modCount++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Создает новое хранилище заданной вместимости.
     * Перемещает в него элементы из текущего, если они не null.
     *
     * @param newSize заданная вместимость.
     * @return карта новой вместимости.
     */
    private void resize(int newSize) {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newSize];
        if (this.table != null) {
            for (Node<K, V> node : this.table) {
                if (node != null) {
                    int i = this.index(this.hash(node.key), newSize);
                    newTable[i] = node;
                }
            }
        }
        this.table = newTable;
    }

    /**
     * Возвращает значение по заданному по ключу.
     *
     * @param key ключ.
     * @return value значение.
     */
    public V get(K key) {
        int i = this.index(this.hash(key), this.table.length);
        Node<K, V> node = this.table[i];
        return node != null && Objects.equals(key, node.key) ? node.value : null;
    }

    /**
     * Удаляет из карты объект по заданному по ключу.
     *
     * @param key ключ.
     * @return true.
     */
    public boolean delete(K key) {
        boolean result = false;
        int i = this.index(this.hash(key), this.table.length);
        Node<K, V> node = this.table[i];
        if (node != null && key.equals(node.key)) {
            this.table[i] = null;
            this.modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Вспомогательный метод, получает полный размер карты (с учетом пустых ячеек).
     *
     * @return размер карты (с учетом пустых ячеек).
     */
    public int size() {
        return this.table.length;
    }

    /**
     * Вспомогательный метод, получает размер заполненной части карты.
     *
     * @return размер карты.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Вспомогательный метод, получает множество пар ключ-значение.
     *
     * @return множество пар ключ-значение, в котором могут быть значения null.
     */
    public Node<K, V>[] entrySet() {
        return this.table;
    }

    /**
     * Возвращает итератор для последовательного прохода по элементам карты.
     *
     * @return переопределенный итератор.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {

            int expectedModCount = SimpleHashMap.this.modCount;
            private int pointer;
            private int counter;

            @Override
            public boolean hasNext() {
                for (int i = pointer; i < table.length; i++) {
                    if (table[i] != null) {
                        pointer = i;
                        break;
                    }
                }
                return counter < SimpleHashMap.this.size;
            }

            @Override
            public Node<K, V> next() {
                if (this.expectedModCount != SimpleHashMap.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                counter++;
                return table[pointer++];
            }
        };
    }

    /**
     * Внутренний класс для хранения данных в виде пар ключ-значение.
     */
    private static class Node<K, V> {

        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }
    }
}
