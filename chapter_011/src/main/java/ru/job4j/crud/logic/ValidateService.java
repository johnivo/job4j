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
public class ValidateService {

    private static final ValidateService INSTANCE = new ValidateService();

    /**
     * Ссылка на объект хранилища пользователей.
     */
    //private final Store<User> storage = MemoryStore.getInstance();
    private final Store<User> storage = DBStore.getInstance();

    public ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public Boolean add(User user) {
        checkUser(user);
        storage.add(user);
        return true;
    }

    public Boolean update(User user, Integer id) {
        checkUser(user);
        checkId(id);
        storage.update(user, id);
        return true;
    }

    public Boolean uploadImage(User user) {
        checkUser(user);
        storage.uploadImage(user);
        return true;
    }

    public Boolean delete(Integer id) {
        checkId(id);
        storage.delete(id);
        return true;
    }

    public List<User> findAll() {
        if (storage.findAll().size() == 0) {
            System.out.println("No data, users are not found");
        }
        return storage.findAll();
    }

    public User findById(int id) {
        checkId(id);
        return storage.findById(id);
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
