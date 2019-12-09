package ru.job4j.crud.store;

import ru.job4j.crud.datamodel.User;

import java.util.List;

/**
 *
 * Описывает хранилище пользователей
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.11.2019
 */
public interface Store<T extends User> {

    void add(T user);

    void update(T user, Integer id);

    void delete(Integer id);

    List<T> findAll();

    T findById(int id);

    void uploadImage(T user);

    T findByLogin(String login);

    T isCredential(String login, String password);
}
