package ru.job4j.warehouse;

import java.util.Date;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.07.2019
 */
public class Warehouse implements Storage {

    @Override
    public boolean checkDate(Food food, Date current) {
        boolean result = false;

        double passed = Storage.duration(food.getCreateDate(), current);
        double leftover = Storage.duration(food.getCreateDate(), food.getExpiryDate());
        double expire = passed / leftover;

        if (expire < Storage.LOWER && expire > 0) {
            result = true;
        }

        return result;
    }

}
