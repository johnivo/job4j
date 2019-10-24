package ru.job4j.email;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 24.10.2019
 */
public class User {

    private final String username;

    private final String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
