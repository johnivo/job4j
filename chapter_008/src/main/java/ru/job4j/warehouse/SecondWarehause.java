package ru.job4j.warehouse;

import java.time.LocalDateTime;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.08.2019
 */
public class SecondWarehause extends StorageDecorator {

    public SecondWarehause(Storage decoratedStorage) {
        super(decoratedStorage);
    }

    @Override
    public boolean checkExpiration(Food food, LocalDateTime current) {
        return decoratedStorage.checkExpiration(food, current);
    }

    @Override
    public String toString() {
        return "SecondWarehause";
    }

}
