package ru.job4j.crud.logic;

import ru.job4j.crud.datamodel.User;

import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.12.2019
 */
public interface Validate {

    Boolean add(User user);

    Boolean update(User user, Integer id);

    Boolean uploadImage(User user);

    Boolean delete(Integer id);

    List<User> findAll();

    User findById(int id);

    User findByLogin(String login);

    User isCredential(String login, String password);

    List<String> getCountries();

    List<String> getCities(String country);
}
