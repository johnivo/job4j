package ru.job4j.crud.datamodel;

/**
 * Описывает модель роли.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 06.12.2019
 */
public class Role {

    private String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
