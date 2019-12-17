package ru.job4j.crud.logic;

import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.store.MemoryStore;
import ru.job4j.crud.store.Store;

import java.util.List;

/**
 * заглушка для тестов, используем MemoryStore
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 11.12.2019
 */
public class ValidateStub implements Validate {

    private final Store storage = MemoryStore.getInstance();

    @Override
    public Boolean add(User user) {
        storage.add(user);
        return true;
    }

    @Override
    public Boolean update(User user, Integer id) {
        storage.update(user, id);
        return true;
    }

    @Override
    public Boolean uploadImage(User user) {
        storage.uploadImage(user);
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        storage.delete(id);
        return true;
    }

    @Override
    public List<User> findAll() {
        return storage.findAll();
    }

    @Override
    public User findById(int id) {
        return storage.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return storage.findByLogin(login);
    }

    @Override
    public User isCredential(String login, String password) {
        return storage.isCredential(login, password);
    }
}
