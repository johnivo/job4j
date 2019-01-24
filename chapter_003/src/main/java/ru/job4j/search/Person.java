package ru.job4j.search;

/**
 * Class Person.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 24.01.2018
 */
public class Person {

    /**
     * Поля: имя, фамилия, адрес, номер телефона, адрес.
     */
    private String name;
    private String surname;
    private String phone;
    private String address;

    /**
     * Конструктор.
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Геттеры к полям.
     */
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
