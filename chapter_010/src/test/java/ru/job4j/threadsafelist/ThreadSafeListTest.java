package ru.job4j.threadsafelist;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.10.2019
 */
public class ThreadSafeListTest {

    @Test
    public void whenConcurrentAdd20ThenListSizeIs20() throws InterruptedException {
        final ThreadSafeList threadSafeList = new ThreadSafeList();

        Thread first = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (threadSafeList.add(i)) {
                    System.out.println(String.format(Thread.currentThread().getName()) + " " + i);
                }
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (threadSafeList.add(i * 100)) {
                    System.out.println(String.format(Thread.currentThread().getName()) + " " + i * 100);
                }
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println();
        for (int i = 0; i < threadSafeList.size(); i++) {
            System.out.println(threadSafeList.get(i));
        }

        assertThat(threadSafeList.size(), is(20));

    }

    /**
     * Класс описывает тред, реализующий проход по списку.
     */
    private class ThreadSafeIterator<E> extends Thread {
        private final ThreadSafeList<E> threadSafeList;

        private ThreadSafeIterator(ThreadSafeList threadSafeList) {
            this.threadSafeList = threadSafeList;
        }

        @Override
        public void run() {
            Iterator<E> it = this.threadSafeList.iterator();
            while (it.hasNext()) {
                E value = it.next();
                System.out.println(Thread.currentThread().getName() + " = " + value);
            }
        }
    }

    @Test
    public void whenUsingConcurrentIterationAndListChangesThenResultsAreDifferent() throws InterruptedException {
        final ThreadSafeList<Integer> threadSafeList = new ThreadSafeList();
        for (int i = 0; i < 5; i++) {
            threadSafeList.add(i);
        }
        Thread first = new ThreadSafeIterator(threadSafeList);
        Thread second = new ThreadSafeIterator(threadSafeList);

        first.start();
        second.start();

        first.join();
        second.join();

        threadSafeList.add(1000);
        threadSafeList.add(2000);

        Thread third = new ThreadSafeIterator(threadSafeList);
        third.start();
        third.join();

        assertThat(threadSafeList.getSize(), is(7));
    }

}