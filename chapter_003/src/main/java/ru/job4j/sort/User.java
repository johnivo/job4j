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
        return new StringBuilder()
                .append("name ")
                .append(name)
                .append(" age ")
                .append(age)
                .toString();
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
