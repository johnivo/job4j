package ru.job4j.crud.logic;

import ru.job4j.crud.datamodel.User;
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
     * Ссылка на объект MemoryStore, в котором находится хранилище пользователей.
     */
    private final Store<User> storage = MemoryStore.getInstance();

    public ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public Boolean add(User user) {
        checkInput(user);
        storage.add(user);
        return true;
    }

    public Boolean update(User user) {
        checkInput(user);
        storage.update(user);
        return true;
    }

    public Boolean delete(User user) {
        checkInput(user);
        storage.delete(user);
        return true;
    }

    public List<User> findAll() {
        if (storage.findAll().size() == 0) {
            System.out.println("No data, users are not found");
        }
        return storage.findAll();
    }

    public User findById(int id) {
        if (id < 0) {
            throw new IllegalStateException(String.format("%d such id is invalid ", id));
        }
        return storage.findById(id);
    }

    private void checkInput(User user) {
        if (user == null) {
            throw new NullPointerException("User does not exist, the reference is null.");
        }
    }

}
