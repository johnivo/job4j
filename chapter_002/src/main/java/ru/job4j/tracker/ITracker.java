package ru.job4j.tracker;

import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 19.06.2019
 */
public interface ITracker {

    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
