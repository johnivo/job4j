package ru.job4j.pool;

import ru.job4j.blockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.10.2019
 */
public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> taskQueue;

    private volatile boolean isStopped = false;

    public ThreadPool(SimpleBlockingQueue<Runnable> taskQueue) {

        this.taskQueue = taskQueue;
        int sizeOfPool = Runtime.getRuntime().availableProcessors();
        System.out.println("sizeOfPool = " + sizeOfPool);

        for (int i = 0; i < sizeOfPool; i++) {
            Thread taskExecutor = new TaskExecutor(taskQueue);
            threads.add(taskExecutor);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void work(Runnable task) throws InterruptedException {
        if (isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        this.taskQueue.offer(task);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        isStopped = true;
    }

}
