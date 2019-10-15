package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.10.2019
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> storage;

    public UserStorage() {
        this.storage = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        boolean rst = false;

        if (!storage.containsKey(user.getId())) {
            storage.put(user.getId(), user);
            rst = true;
        }

        return rst;
    }

    public synchronized boolean update(User user) {
        boolean rst = false;

        if (storage.containsKey(user.getId())) {
            storage.put(user.getId(), user);
            rst = true;
        }

        return rst;
    }

    public synchronized boolean delete(User user) {
        boolean rst = false;

        if (storage.containsKey(user.getId())) {
            storage.remove(user.getId());
            rst = true;
        }

        return rst;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rst = false;

        if (storage.containsKey(fromId)
                && storage.containsKey(toId)
                && storage.get(fromId).getAmount() >= amount) {
            storage.get(fromId).setAmount(storage.get(fromId).getAmount() - amount);
            storage.get(toId).setAmount(storage.get(toId).getAmount() + amount);
            rst = true;
        }

        return rst;
    }

    public synchronized Integer size() {
        return storage.size();
    }

    public synchronized User getUser(int key) {
        return storage.get(key);
    }

    public synchronized Map<Integer, User> getStorage() {
        return this.storage;
    }
}
