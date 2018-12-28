package ru.job4j.tracker;

import java.util.*;

/**
 * Класс трэкер.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 28.12.2018
 */
public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Произвольная числовая часть уникального ключа.
     */
    private static final Random RN  = new Random();

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описания. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод реализует добавление заявки в хранилище.
     * @param item новая заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод реализует редактирование заявки.
     * @param id номер заявки;
     * @param item новая заявка;
     * @return result удалось ли провести операцию.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод реализует удаление заявки.
     * @param id номер заявки.
     * @return result удалось ли провести операцию.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getId().equals(id)) {
                System.arraycopy(items, i + 1, items, i, position - i);
                position--;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает список всех заявок.
     * @return result список заявок.
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, this.position);
    }

    /**
     * Метод реализует получение списка заявок по имени.
     * @param key имя заявки.
     * @return result список одноименных заявок.
     */
    public Item[] findByName(String key) {
        Item[] itemsKey = new Item[this.position];
        int countKey = 0;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getName().equals(key)) {
                itemsKey[countKey++] = items[i];
            }
        }
        return Arrays.copyOf(itemsKey, countKey);
    }

    /**
     * Метод находит заявку по id номеру.
     * @param id номер заявки;
     * @return result искомая заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}