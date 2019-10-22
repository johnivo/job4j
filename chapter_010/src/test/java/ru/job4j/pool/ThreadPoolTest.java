package ru.job4j.pool;

import org.junit.Test;
import ru.job4j.blockingqueue.SimpleBlockingQueue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.10.2019
 */
public class ThreadPoolTest {

    class Task implements Runnable {

        private Integer id;

        private Task(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println(String.format("Started %s on %s", this, Thread.currentThread().getName()));
                Thread.sleep(1000);
                System.out.println(String.format("Finished %s on %s", this, Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" + "id=" + id + '}';
        }
    }

    @Test
    public void whenTasksAddedToQueueThenTheyExecutedByThreadPool() {

        SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(10);
        ThreadPool pool = new ThreadPool(queue);

        try {

            for (int i = 0; i < 20; i++) {
                pool.work(new Task(i));
            }

            Thread.sleep(5000);
            pool.shutdown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}