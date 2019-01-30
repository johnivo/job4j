package ru.job4j.list;

/**
 * Class User.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 30.01.2018
 */
public class User {

    /**
     * Поля: уникальный номер, имя пользователя, город.
     */
    private int id;
    private String name;
    private String city;

    /**
     * Конструктор.
     *
     * @param id уникальный номер.
     * @param name имя.
     * @param city город.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Геттеры к полям.
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}