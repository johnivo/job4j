package ru.job4j.warehouse;

import java.time.LocalDateTime;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.08.2019
 */
public class Refrigerator extends StorageDecorator {

    public Refrigerator(Storage decoratedStorage) {
        super(decoratedStorage);
    }

    @Override
    public boolean checkExpiration(Food food, LocalDateTime current) {
        boolean result = false;

        if (decoratedStorage.checkExpiration(food, current) && food.canReproduct() && food.lowTemp()) {
            result = true;
        }

        return result;
    }

    @Override
    public String toString() {
        return "Refrigerator";
    }
}
