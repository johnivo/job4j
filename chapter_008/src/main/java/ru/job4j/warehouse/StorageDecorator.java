package ru.job4j.warehouse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 02.08.2019
 */
public abstract class StorageDecorator implements Storage {

    protected Storage decoratedStorage;

    public StorageDecorator(Storage decoratedStorage) {
        this.decoratedStorage = decoratedStorage;
    }

    private List<Food> foods = new ArrayList();

    @Override
    public void add(Food food) {
        this.foods.add(food);
    }

    @Override
    public void clear() {
        this.foods.clear();
    }

    @Override
    public void setStorage(Storage decoratedStorage) {
        this.decoratedStorage = decoratedStorage;
    }

    @Override
    public List<Food> getList() {
        return foods;
    }

}
