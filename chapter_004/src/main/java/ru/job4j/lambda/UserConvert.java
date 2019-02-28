package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class UserConvert {
    public static class User {
        private final String name;


        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{"
                    +
                    "name='" + name + '\''
                    +
                    '}';
        }
    }

    public List<User> convert(List<String> names, Function<String, User> op) {
        List<User> users = new ArrayList<>();
        names.forEach(
                n -> users.add(op.apply(n))
        );
        return users;
    }

    public static void badMethod() throws Exception {
    }

    public static interface Wrapper<T> {
        T get();
        void set(T value);
        boolean isEmpty();
    }

    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        Wrapper<Exception> ex = null;
        names.forEach(
                n ->  {
                    try {
                        badMethod();
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        if (!ex.isEmpty()) {
            throw ex.get();
        }
    }
}