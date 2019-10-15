package ru.job4j.userstorage;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.10.2019
 */
public class UserStorageTest {

    @Test
    public void whenAdd2UsersThenStorageSizeIs2() {
        UserStorage storage = new UserStorage();

        assertTrue(storage.add(new User(1, 100)));
        assertTrue(storage.add(new User(2, 200)));

        assertThat(storage.size(), is(2));
    }

    @Test
    public void whenAddUserAndUpdateThenUserUpdated() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));

        assertTrue(storage.update(new User(1, 200)));
        assertThat(storage.getUser(1).getAmount(), is(200));
    }

    @Test
    public void whenAdd2UserAndRemove1UserThenStorageSizeIs1() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);

        assertTrue(storage.delete(user1));
        assertThat(storage.size(), is(1));
    }

    @Test
    public void whenTransferCorrectAmountThenTransferTookPlace() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));

        assertTrue(storage.transfer(1, 2, 50));
        assertThat(storage.getUser(1).getAmount(), is(50));
        assertThat(storage.getUser(2).getAmount(), is(250));
    }

    /**
     * Класс описывает тред, добавляющий юзера.
     */
    private class ThreadUserStorage extends Thread {
        private final UserStorage storage;
        private User user;

        private ThreadUserStorage(final UserStorage storage, User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            storage.add(user);
        }
    }

    @Test
    public void whenConcurrentAdd2UsersThenStorageSizeIs100() throws InterruptedException {
        final UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        Thread first = new ThreadUserStorage(storage, user1);
        Thread second = new ThreadUserStorage(storage, user2);

        first.start();
        second.start();

        first.join();
        second.join();

        for (Map.Entry<Integer, User> entry : storage.getStorage().entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }

        assertThat(storage.size(), is(2));
    }

    @Test
    public void whenConcurrentAdd100UsersThenStorageSizeIs100() throws InterruptedException {
        final UserStorage storage = new UserStorage();

        Thread first = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                User user = new User(i, i * 100);
                storage.add(user);
                System.out.println(String.format(Thread.currentThread().getName()) + " " + user.toString());
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                User user = new User(i, i);
                storage.add(user);
                System.out.println(String.format(Thread.currentThread().getName()) + " " + user.toString());
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println();
        for (int i = 0; i < storage.size(); i++) {
            System.out.println(storage.getUser(i));
        }

        assertThat(storage.size(), is(100));
    }

    @Test
    public void whenConcurrentTransfersThenAllCorrectTransfersTookPlace() throws InterruptedException {
        final UserStorage storage = new UserStorage();
        User user1 = new User(1, 1000);
        User user2 = new User(2, 1000);
        storage.add(user1);
        storage.add(user2);

        Thread first = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (storage.transfer(1, 2, 100)) {
                    System.out.println(String.format(Thread.currentThread().getName()) + " " + user1.toString());
                }
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (storage.transfer(2, 1, 100)) {
                    System.out.println(String.format(Thread.currentThread().getName()) + " " + user2.toString());
                }
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println();
        for (int i = 1; i <= storage.size(); i++) {
            System.out.println(storage.getUser(i));
        }

        assertThat(storage.getUser(1).getAmount(), is(1000));
        assertThat(storage.getUser(2).getAmount(), is(1000));
    }

}