package ru.job4j.warehouse;

import java.util.Date;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.07.2019
 */
public class Shop implements Storage {

    @Override
    public boolean checkDate(Food food, Date current) {
        boolean result = false;

        double passed = Storage.duration(food.getCreateDate(), current);
        double leftover = Storage.duration(food.getCreateDate(), food.getExpiryDate());
        double expire = passed / leftover;

        if (expire >= Storage.LOWER && expire <= Storage.UPPER) {
            result = true;
        } else if (expire > Storage.UPPER && expire < 1) {
            food.setDiscount(Storage.DISCOUNT);
            result = true;
        }

        return result;
    }

}
