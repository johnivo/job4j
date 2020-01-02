package ru.job4j.servlets;

import java.util.Objects;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.12.2019
 */
public class Person {

    private String name;
    private String surname;
    private String city;
    private String sex;
    private String desc;

    private Integer id;

    public Person() {
    }

    public Person(String name, String surname, String city, String sex, String desc) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.sex = sex;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return name.equals(person.name)
                && surname.equals(person.surname)
                && city.equals(person.city)
                && sex.equals(person.sex)
                && desc.equals(person.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, city, sex, desc);
    }

    @Override
    public String toString() {
        return String.format("Person{ name=%s%n, surname=%s%n, city=%s%n, sex=%s%n, desc=%s%n }",
                name, surname, city, sex, desc);
    }
}
