package ru.job4j.blockingqueue;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 18.10.2019
 */
public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAndConsumerWorksThenDataSame() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        int[] source = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = new int[10];

        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        queue.offer(source[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        Thread consumer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        result[i] = queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        assertTrue(Arrays.equals(source, result));
    }

    @Test
    public void whenProducerFasterThanConsumer() throws InterruptedException {

        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    simpleBlockingQueue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    simpleBlockingQueue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

}