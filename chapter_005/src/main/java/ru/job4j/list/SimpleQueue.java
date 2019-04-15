package ru.job4j.list;

/**
 * Реализация очереди на двух стеках.
 * FIFO - first input first output.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.04.2019
 */
public class SimpleQueue<E> {

    /**
     * Стек для добавления элементов в очередь.
     */
    private SimpleStack<E> inbox = new SimpleStack();

    /**
     * Стек для получения элементов из очереди.
     */
    private SimpleStack<E> outbox = new SimpleStack();

    /**
     * Возвращает первый элемент очереди (верхний элемент стека получения), удаляя его из коллекции.
     *
     * @return верхний элемент (null, при пустом стеке).
     */
    public E poll() {
        this.shift();
        return this.outbox.poll();
    }

    /**
     * Добавляет в начало стека новый элемент.
     *
     * @param value добавляемое значение.
     */
    public void push(E value) {
        this.inbox.push(value);
    }

    /**
     * Перемещает элементы из первого стека во второй в обратном порядке.
     */
    private void shift() {
        if (this.inbox.size() != 0 && this.outbox.size() == 0) {
            while (this.inbox.size() > 0) {
                this.outbox.push(this.inbox.poll());
            }
        }
    }

    /**
     * Вспомогательный метод, возвращает размер очереди.
     */
    public int size() {
        return this.inbox.size() + this.outbox.size();
    }

    /**
     * Вспомогательный метод, возвращает размер входящего стека.
     */
    public int sizeInbox() {
        return this.inbox.size();
    }

    /**
     * Вспомогательный метод, возвращает размер стека для получения элементов.
     */
    public int sizeOutbox() {
        return this.outbox.size();
    }
}
