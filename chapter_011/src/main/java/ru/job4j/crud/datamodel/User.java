package ru.job4j.crud.datamodel;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Модель пользователя
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.11.2019
 */
public class User {

    private Integer id;
    private String name;
    private String login;
    private String email;
    private LocalDateTime createDate;

    //поле содержит имя файла с аватаром
    private String photoId;

    public User() {
    }

    public User(Integer id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDateTime.now();
    }

    public User(Integer id, String name, String login, String email, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(Integer id, String name, String login, String email, LocalDateTime createDate, String photoId) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.photoId = photoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id)
                && name.equals(user.name)
                && login.equals(user.login)
                && email.equals(user.email)
                && createDate.equals(user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, createDate);
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", login='" + login + '\'' +
//                ", email='" + email + '\'' +
//                ", createDate=" + createDate +
//                '}';
//    }

    @Override
    public String toString() {
        return String.format("User{ id=%d, name=%s, login=%s, email=%s, createDate=%s }",
                id, name, login, email, createDate);
    }
}
