package ru.job4j.tracker;

import java.util.List;

/**
 * Энергичная загрузка (Eager loading) - загружает объект сразу после старта виртуальной машины.
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.03.2019
 */
public enum TrackerSingle {
    // ключевое слово enum, используется для создания ограниченного количество объектов.

    // здесь мы указываем перечисления.
    INSTANCE;

    // Далее конструкторы и методы.
    private Tracker tracker = new Tracker();

    public Item add(Item item) {
        return this.tracker.add(item);
    }

    public boolean replace(String id, Item item) {
        return this.tracker.replace(id, item);
    }

    public boolean delete(String id) {
        return this.tracker.delete(id);
    }

    public List<Item> findAll() {
        return this.tracker.findAll();
    }

    public List<Item> findByName(String nameItem) {
        return this.tracker.findByName(nameItem);
    }

    public Item findById(String id) {
        return this.tracker.findById(id);
    }

    //Чтобы получить экземпляр класса нужно напрямую обратиться к полю INSTANCE.
}
