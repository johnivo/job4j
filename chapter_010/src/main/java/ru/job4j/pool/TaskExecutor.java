package ru.job4j.pool;

import ru.job4j.blockingqueue.SimpleBlockingQueue;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.10.2019
 */
public class TaskExecutor extends Thread {

    private SimpleBlockingQueue<Runnable> taskQueue;

    public TaskExecutor(SimpleBlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted() || !taskQueue.isEmpty()) {
            try {
                Runnable task = taskQueue.poll();
                task.run();
            } catch (Exception e) {
                //e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

}
