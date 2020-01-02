package ru.job4j.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.12.2019
 */
public class PersonStorage {

    private static final PersonStorage INSTANCE = new PersonStorage();
    private final ConcurrentHashMap<Integer, Person> storage = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    private PersonStorage() {
        this.add(new Person("test", "test", "test", "test", "test"));
    }

    public static PersonStorage getInstance() {
        return INSTANCE;
    }

    public boolean add(Person person) {
        person.setId(id.getAndIncrement());
        return this.storage.put(person.getId(), person) == null;
    }

    public List<Person> findAll() {
        List<Person> result = new ArrayList<>();
        result.addAll(this.storage.values());
        return result;
    }

}
