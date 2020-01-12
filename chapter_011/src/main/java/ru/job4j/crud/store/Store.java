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
public interface Store {

    void add(User user);

    void update(User user, Integer id);

    void delete(Integer id);

    List<User> findAll();

    User findById(int id);

    void uploadImage(User user);

    User findByLogin(String login);

    User isCredential(String login, String password);

    List<String> getCountries();

    List<String> getCities(String country);
}
