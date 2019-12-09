package ru.job4j.crud.logic;

import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.store.DBStore;
import ru.job4j.crud.store.MemoryStore;
import ru.job4j.crud.store.Store;

import java.util.List;

/**
 * Logic layout - слой содержит выполнение бизнес логики.
 * Например, проверить существует ли такой уже пользователь или нет. Если существует, то вернуть ошибку в слой Presentation.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.11.2019
 */
public class ValidateService implements Validate {

    private static final Validate INSTANCE = new ValidateService();

    /**
     * Ссылка на объект хранилища пользователей.
     */
    //private final Store<User> storage = MemoryStore.getInstance();
    private final Store<User> storage = DBStore.getInstance();

    public ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public Boolean add(User user) {
        checkUser(user);
        storage.add(user);
        return true;
    }

    @Override
    public Boolean update(User user, Integer id) {
        checkUser(user);
        checkId(id);
        storage.update(user, id);
        return true;
    }

    @Override
    public Boolean uploadImage(User user) {
        checkUser(user);
        storage.uploadImage(user);
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        checkId(id);
        storage.delete(id);
        return true;
    }

    @Override
    public List<User> findAll() {
        if (storage.findAll().size() == 0) {
            System.out.println("No data, users are not found");
        }
        return storage.findAll();
    }

    @Override
    public User findById(int id) {
        checkId(id);
        return storage.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        checkField(login);
        return storage.findByLogin(login);
    }

    @Override
    public User isCredential(String login, String password) {
        checkField(login);
        checkField(password);
        return storage.isCredential(login, password);
    }

    private void checkField(String field) {
        if (field == null) {
            throw new NullPointerException("Credentials does not exist, the reference is null.");
        }
    }

    private void checkUser(User user) {
        if (user == null) {
            throw new NullPointerException("User does not exist, the reference is null.");
        }
    }

    private void checkId(Integer id) {
        if (id < 0 || id == null) {
            throw new IllegalStateException(String.format("%d such id is invalid ", id));
        }
    }

}
