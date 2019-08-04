package ru.job4j.warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.07.2019
 */
public class Trash implements Storage {

    private List<Food> foods = new ArrayList();

    @Override
    public void add(Food food) {
        this.foods.add(food);
    }

    @Override
    public List<Food> getList() {
        return foods;
    }

    @Override
    public boolean checkExpiration(Food food, LocalDateTime current) {
        boolean result = false;

        double passed = Storage.duration(food.getCreateDate(), current);
        double leftover = Storage.duration(food.getCreateDate(), food.getExpiryDate());
        double expire = passed / leftover;

        if (expire >= 1) {
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Trash";
    }
}
