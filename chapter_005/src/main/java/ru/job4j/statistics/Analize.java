package ru.job4j.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Статистика по коллекции.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 25.04.2019
 */
public class Analize {

    /**
     * Возвращает статистику изменений в коллекции.
     * Сколько добавлено новых, сколько изменено и сколько удалено пользователей.
     * Изменённым считается объект в котором изменилось имя, а id осталось прежним.
     *
     * @param previous начальные данные.
     * @param current измененные данные.
     * @return статистика изменений.
     */
    public Info diff(List<User> previous, List<User> current) {

        int added;
        int changed = 0;
        int deleted = 0;

        HashMap<Integer, User> actual = (HashMap<Integer, User>) current
                .stream()
                .collect(Collectors.toMap(User::getId, u -> u));

        for (User user : previous) {
            if (actual.containsKey(user.id) & !actual.containsValue(user)) {
                changed++;
            } else if (!actual.containsKey(user.id)) {
                deleted++;
            }
        }
        added = actual.size() - previous.size() + deleted;
        Info summary = new Info(added, changed, deleted);
        return summary;
    }


    /**
     * Профиль пользователя.
     */
    public static class User {

        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
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
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(id);
            result = 31 * result + this.name.hashCode();
            return result;
        }

    }

    /**
     * Хранилище результатов.
     */
    public static class Info {

        private int added;
        private int changed;
        private int deleted;

        Info(int add, int change, int del) {
            this.added = add;
            this.changed = change;
            this.deleted = del;
        }

        public int getAdded() {
            return this.added;
        }

        public int getChanged() {
            return this.changed;
        }

        public int getDeleted() {
            return this.deleted;
        }

        @Override
        public String toString() {
            return "Info{" + "added=" + added + ", changed=" + changed + ", deleted=" + deleted + '}';
        }
    }
}
