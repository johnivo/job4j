package ru.job4j.warehouse;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс перераспределяет еду по хранилищам в зависимости от заданных условий.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.07.2019
 */
public class ControlQuality {

    /**
     * Список хранилищ продуктов.
     */
    private List<Storage> storages = new ArrayList<>();

    /**
     * Хранилище продуктов в виде карты (имя склада=список продуктов).
     */
    private Map<String, List<Food>> foodMap = new HashMap<>();

    /**
     * Конструктор со списком хранилищ, загружаемых по-умолчанию.
     */
    public ControlQuality() {
        load(new Warehouse());
        load(new Shop());
        load(new Trash());
    }

    /**
     * Загружает хранилище в список.
     * @param storage хранилище.
     */
    public void load(Storage storage) {
        this.storages.add(storage);
    }

    /**
     * Перераспределяет продукты по соответствующим местам использования.
     * @param foods список продуктов.
     * @param current текущая дата.
     * @return foodMap хранилище продуктов.
     */
    public Map<String, List<Food>> distribute(List<Food> foods, Date current) {
        for (Storage s : storages) {
            List<Food> storage = foods.stream()
                    .filter(
                            f -> s.checkDate(f, current)
                    )
                    .collect(Collectors.toList());
            foodMap.put(s.getClass().getName(), storage);
        }
        return foodMap;
    }

    public Map<String, List<Food>> distribute2(List<Food> foods, Date current) {
        String nameStorage = "0";
        for (Storage s : storages) {
            List<Food> storage = new ArrayList<>();
            for (Food f : foods) {
                if (s.checkDate(f, current)) {
                    nameStorage = s.getClass().getName();
                    storage.add(f);
                }
            }
            foodMap.put(nameStorage, storage);
        }
        return foodMap;
    }
}
