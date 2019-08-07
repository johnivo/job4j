package ru.job4j.warehouse;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.07.2019
 */
public class ControlQualityTest {

    @Test
    public void setDiscountTest() {

        List<Storage> storages = new ArrayList<>();
        Shop shop = new Shop();
        storages.add(shop);

        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDateTime now = LocalDateTime.now();

        Fruits fruits = new Fruits("apple golden", now.minusDays(90), now.plusDays(10), 100.00, 0.0);

        List<Food> foods = List.of(fruits);
        Map<Storage, List<Food>> storage = controlQuality.distribute(foods, now);
        System.out.println(storage);

        int countShop = 0;
        for (Map.Entry<Storage, List<Food>> entry : storage.entrySet()) {
            if (shop.equals(entry.getKey())) {
                countShop = entry.getValue().size();
            }
        }
        Double discount = storage.get(shop).get(0).getDiscount();

        assertThat(countShop, is(1));
        assertThat(discount, is(0.5));
    }

    @Test
    public void whenControlQualityTest() {

        List<Storage> storages = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        List.of(warehouse, shop, trash).stream().
                collect(
                        Collectors.toCollection(() -> storages)
                );

        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDateTime now = LocalDateTime.now();

        Meat meat = new Meat("beef steak", now.minusDays(10), now.plusDays(90), 100.00, 0.0);
        Meat meat2 = new Meat("beef steak2", now.minusDays(50), now.plusDays(50), 100.00, 0.0);
        Fruits fruits = new Fruits("apple golden", now.minusDays(80), now.plusDays(20), 100.00, 0.0);
        Vegetables vegetables = new Vegetables("carrot", now.minusDays(30), now.minusDays(5), 100.00, 0.0);

        List<Food> foods = List.of(meat, meat2, fruits, vegetables);
        Map<Storage, List<Food>> storage = controlQuality.distribute(foods, now);

        System.out.println(storage);

        int countWarehouse = 0;
        int countShop = 0;
        int countTrash = 0;
        for (Map.Entry<Storage, List<Food>> entry : storage.entrySet()) {
            if (warehouse.equals(entry.getKey())) {
                countWarehouse = entry.getValue().size();
            }
            if (shop.equals(entry.getKey())) {
                countShop = entry.getValue().size();
            }
            if (trash.equals(entry.getKey())) {
                countTrash = entry.getValue().size();
            }
        }

        assertThat(countWarehouse, is(1));
        assertThat(countShop, is(2));
        assertThat(countTrash, is(1));
    }

    @Test
    public void whenAdvancedControlQualityTest() {

        List<Storage> storages = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        SecondWarehause secondWarehause = new SecondWarehause(warehouse);
        Refrigerator ref = new Refrigerator(trash);
        RecyclingFactory factory = new RecyclingFactory(trash);

        List.of(ref, factory, trash, shop, secondWarehause).stream().
                collect(
                        Collectors.toCollection(() -> storages)
                );

        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDateTime now = LocalDateTime.now();

        Meat meat = new Meat("beef steak", now.minusDays(10), now.plusDays(90), 100.00, 0.0);
        Meat meat2 = new Meat("beef steak2", now.minusDays(50), now.minusDays(5), 100.00, 0.0);
        Fruits fruits = new Fruits("apple golden", now.minusDays(80), now.plusDays(20), 100.00, 0.0);
        Fruits fruits2 = new Fruits("lemon", now.minusDays(60), now.minusDays(5), 100.00, 0.0);
        Vegetables vegetables = new Vegetables("carrot", now.minusDays(30), now.minusDays(5), 100.00, 0.0);

        List<Food> foods = List.of(meat, meat2, fruits, fruits2, vegetables);
        Map<Storage, List<Food>> storage = controlQuality.distribute(foods, now);
        System.out.println(storage);

        assertThat(ref.getList().size(), is(1));
        assertThat(factory.getList().size(), is(1));
        assertThat(secondWarehause.getList().size(), is(1));
        assertThat(shop.getList().size(), is(1));
        assertThat(trash.getList().size(), is(1));
    }

    @Test
    public void whenResortingAfterChangingStorageConditionsResultIsInverted() {

        List<Storage> storages = new ArrayList<>();
        Shop shop = new Shop();
        Trash trash = new Trash();

        RecyclingFactory factory = new RecyclingFactory(trash);

        List.of(factory, trash).stream().
                collect(
                        Collectors.toCollection(() -> storages)
                );

        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDateTime now = LocalDateTime.now();

        Fruits fruits2 = new Fruits("lemon", now.minusDays(60), now.minusDays(5), 100.00, 0.0);

        List<Food> foods = List.of(fruits2);
        Map<Storage, List<Food>> storage = controlQuality.distribute(foods, now);
        System.out.println(storage);

        assertThat(factory.getList().size(), is(1));
        assertThat(trash.getList().size(), is(0));

        factory.setStorage(shop);
        controlQuality.resort(storage, now);
        System.out.println(storage);

        assertThat(factory.getList().size(), is(0));
        assertThat(trash.getList().size(), is(1));
    }
}