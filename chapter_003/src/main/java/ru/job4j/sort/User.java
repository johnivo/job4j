package ru.job4j.sort;

/**
 * Class User.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 02.02.2018
 */
public class User implements Comparable<User> {

    /**
     * Поля: имя, возраст.
     */
    private String name;
    private int age;

    /**
     * Конструктор.
     *
     * @param name имя.
     * @param age возраст.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    /**
     * Геттеры к полям.
     */
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public int compareTo(User o) {
        return Integer.valueOf(this.age).compareTo(o.age);
    }
}
