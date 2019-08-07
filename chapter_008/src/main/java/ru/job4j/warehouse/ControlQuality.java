package ru.job4j.warehouse;

import java.time.LocalDateTime;
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
    private List<Storage> storages;

    /**
     * Хранилище продуктов в виде карты (имя склада=список продуктов).
     */
    private Map<Storage, List<Food>> foodMap = new HashMap<>();

    /**
     * Конструктор со списком хранилищ, загружаемых по-умолчанию.
     */
    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    /**
     * Загружает хранилище в список.
     * @param storage хранилище.
     */
    public void add(Storage storage) {
        this.storages.add(storage);
    }

    /**
     * Заполняет карту хранилище=список продуктов.
     * @param foods список продуктов.
     * @param current текущая дата.
     * @return foodMap хранилище продуктов.
     */
    public Map<Storage, List<Food>> distribute(List<Food> foods, LocalDateTime current) {
        foods.stream().forEach(f -> load(f, current));
        storages.stream().forEach(s -> foodMap.put(s, s.getList()));
        return foodMap;
    }

    /**
     * Перераспределяет продукты по соответствующим местам использования.
     * @param food продукт.
     * @param current текущая дата.
     */
    public void load(Food food, LocalDateTime current) {
        for (Storage s : storages) {
            if (s.checkExpiration(food, current)) {
                s.add(food);
                break;
            }
        }
    }

    /**
     * Извлекает все продукты и перераспределяет их заново.
     * @param foodMap карта хранилище=список продуктов.
     * @param current текущая дата.
     */
    public Map<Storage, List<Food>> resort(Map<Storage, List<Food>> foodMap, LocalDateTime current) {
        foodMap.clear();
        List<Food> temp = new ArrayList<>();
        storages.stream().forEach(
                s -> {
                    temp.addAll(s.getList());
                    s.clear();
                });
        foodMap = this.distribute(temp, current);
        return foodMap;
    }

//    public Map<String, List<Food>> distribute(List<Food> allFoods, LocalDateTime current) {
//        for (Storage s : storages) {
//            List<Food> foods = allFoods.stream()
//                    .filter(
//                            f -> s.checkExpiration(f, current)
//                    )
//                    .collect(Collectors.toList());
//            foodMap.put(s.getClass().getSimpleName(), foods);
//        }
//        return foodMap;
//    }
//
//    public Map<String, List<Food>> distribute2(List<Food> foods, LocalDateTime current) {
//        String nameStorage = "0";
//        for (Storage s : storages) {
//            List<Food> storage = new ArrayList<>();
//            for (Food f : foods) {
//                if (s.checkExpiration(f, current)) {
//                    nameStorage = s.getClass().getName();
//                    storage.add(f);
//                }
//            }
//            foodMap.put(nameStorage, storage);
//        }
//        return foodMap;
//    }

}
