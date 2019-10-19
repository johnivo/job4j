package ru.job4j.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 18.10.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * Очередь - общий ресурс.
     */
    @GuardedBy("this")
    private final Queue<T> queue;

    /**
     * Размер очереди.
     */
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    /**
     * Добавляет указанный элемент в очередь и будит потребителя.
     * Если очередь заполнена, усыпляет этот поток, освобождает lock - текущий экземпляр SimpleBlockingQueue.
     * @param value добавляемое значение.
     */
    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() >= size) {
                System.out.println("Queue is full, waiting for consumer.");
                this.wait();

            }
            queue.add(value);
            System.out.println(String.format("Producer added value = %s", value));
            this.notify();
        }
    }

    /**
     * Извлекает и удаляет заголовок очереди, будит поставщика.
     * Если очередь пуста, усыпляет этот поток, освобождает lock - текущий экземпляр SimpleBlockingQueue.
     * @return value заголовок очереди.
     */
    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty, waiting for producer.");
                this.wait();
            }
            T value = queue.poll();
            System.out.println(String.format("Consumer received value = %s", value));
            this.notify();
            return value;
        }
    }

}
