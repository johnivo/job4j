package ru.job4j.warehouse;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.07.2019
 */
public class ControlQualityTest {

    @Test
    public void setDiscountTest() {

        ControlQuality controlQuality = new ControlQuality();

        Fruits fruits = new Fruits();
        fruits.setName("apple golden");

        Date date = new Date();

        try {
            fruits.setCreateDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.07.18"));
            fruits.setExpiryDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.08.05"));

            date = new SimpleDateFormat("yyyy.MM.dd").parse("2019.08.01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Food> foods = List.of(fruits);
        Map<String, List<Food>> storage = controlQuality.distribute(foods, date);
        //System.out.println(storage);

        int countShop = 0;

        for (Map.Entry<String, List<Food>> entry : storage.entrySet()) {
            if ("ru.job4j.warehouse.Shop".equals(entry.getKey())) {
                countShop = entry.getValue().size();
            }
        }
        Double discount = storage.get("ru.job4j.warehouse.Shop").get(0).getDiscount();

        assertThat(countShop, is(1));
        assertThat(discount, is(0.5));
    }



    @Test
    public void whenControlQualityTest() {

        ControlQuality controlQuality = new ControlQuality();

        Meat meat = new Meat();
        meat.setName("beef steak");
        Meat meat2 = new Meat();
        meat2.setName("beef steak");

        Fruits fruits = new Fruits();
        fruits.setName("apple golden");

        Vegetables vegetables = new Vegetables();
        vegetables.setName("carrot");

        Date date = new Date();

        try {
            meat.setCreateDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.07.01"));
            meat.setExpiryDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.07.31"));

            meat2.setCreateDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.07.28"));
            meat2.setExpiryDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.08.15"));

            fruits.setCreateDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.07.18"));
            fruits.setExpiryDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.08.05"));

            vegetables.setCreateDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.07.15"));
            vegetables.setExpiryDate(new SimpleDateFormat("yyyy.MM.dd").parse("2019.08.15"));

            date = new SimpleDateFormat("yyyy.MM.dd").parse("2019.08.01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Food> foods = List.of(meat, meat2, fruits, vegetables);
        Map<String, List<Food>> storage = controlQuality.distribute(foods, date);
        //System.out.println(storage);

        int countWarehouse = 0;
        int countShop = 0;
        int countTrash = 0;

        for (Map.Entry<String, List<Food>> entry : storage.entrySet()) {

            if ("ru.job4j.warehouse.Warehouse".equals(entry.getKey())) {
                countWarehouse = entry.getValue().size();
            }

            if ("ru.job4j.warehouse.Shop".equals(entry.getKey())) {
                countShop = entry.getValue().size();
            }

            if ("ru.job4j.warehouse.Trash".equals(entry.getKey())) {
                countTrash = entry.getValue().size();
            }
        }

        assertThat(countWarehouse, is(1));
        assertThat(countShop, is(2));
        assertThat(countTrash, is(1));
    }
}