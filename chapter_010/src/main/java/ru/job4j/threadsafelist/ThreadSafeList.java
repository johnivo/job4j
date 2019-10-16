package ru.job4j.threadsafelist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.BaseList;
import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.10.2019
 */
@ThreadSafe
public class ThreadSafeList<E> implements BaseList<E> {
    @GuardedBy("this")
    private final SimpleArrayList<E> simpleArrayList = new SimpleArrayList<>();

    @Override
    public synchronized boolean add(E value) {
        return this.simpleArrayList.add(value);
    }

    @Override
    public synchronized E get(int index) {
        return this.simpleArrayList.get(index);
    }

    @Override
    public synchronized E[] grow() {
        return this.simpleArrayList.grow();
    }

    /**
     * Возвращает итератор, работающий в режиме fail-safe,
     * не выбрасывает ConcurrentModificationException,
     * поэтому все изменения после получения коллекции не будут отображаться в итераторе.
     * @return fail-safe итератор
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.simpleArrayList).iterator();
    }

    /**
     * Копирует список в текущем состоянии, т.е. на момент запуска итератора
     * @param simpleArrayList ссылка на список
     * @return snapshot копия списка
     */
    public synchronized SimpleArrayList<E> copy(SimpleArrayList<E> simpleArrayList) {
        SimpleArrayList<E> snapshot = new SimpleArrayList<>();

        for (E value : simpleArrayList) {
            snapshot.add(value);
        }
        return snapshot;
    }

    public synchronized int size() {
        return this.simpleArrayList.size();
    }

    public synchronized int getSize() {
        return this.simpleArrayList.getSize();
    }
}
