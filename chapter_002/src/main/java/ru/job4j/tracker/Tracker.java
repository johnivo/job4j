package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

/**
 * Класс трэкер.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 28.12.2018
 */
public class Tracker implements ITracker {
    /**
     * Лист для хранения заявок.
     */
    //private final Item[] items = new Item[100];
    private List<Item> items = new ArrayList<>();

    ///**
     //* Указатель ячейки для новой заявки.
     //*/
    //private int position = 0;

    /**
     * Произвольная числовая часть уникального ключа.
     */
    private static final Random RN  = new Random();

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описания. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод реализует добавление заявки в хранилище.
     * @param item новая заявка.
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        //this.items[this.position++] = item;
        this.items.add(item);
        return item;
    }

    /**
     * Метод реализует редактирование заявки.
     * @param id номер заявки;
     * @param item новая заявка;
     * @return result удалось ли провести операцию.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        item.setId(id);
        //for (int i = 0; i < this.position; i++) {
        //    if (items[i].getId().equals(id)) {
        //       items[i] = item;
        //        result = true;
        //        break;
        //    }
        //}
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                this.items.set(i, item);
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод реализует удаление заявки.
     * @param id номер заявки.
     * @return result удалось ли провести операцию.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        //for (int i = 0; i < this.position; i++) {
        //    if (items[i].getId().equals(id)) {
        //        System.arraycopy(items, i + 1, items, i, position - i);
        //        position--;
        //        result = true;
        //        break;
        //    }
        for (Item i: this.items) {
            if (i.getId().equals(id)) {
                result = this.items.remove(i);
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает список всех заявок.
     * @return result список заявок.
     */
    //public Item[] findAll() {
    //   return Arrays.copyOf(items, this.position);
    //}
    @Override
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод реализует получение списка заявок по имени.
     * @param nameItem имя заявки.
     * @return result список одноименных заявок.
     */
    //public Item[] findByName(String nameItem) {
     //   Item[] itemsKey = new Item[this.position];
     //   int count = 0;
     //   for (int i = 0; i < this.position; i++) {
     //       if (items[i].getName().equals(nameItem)) {
     //           itemsKey[count++] = items[i];
     //       }
     //   }
     //   return Arrays.copyOf(itemsKey, count);
    //}
     //public List<Item> findByName(String nameItem) {
     //   List<Item> itemsKey = new ArrayList<>();
     //   for (Item i: this.items) {
     //       if (i.getName().equals(nameItem)) {
     //           itemsKey.add(i);
     //       }
     //   }
     //   return itemsKey;
    //}
    @Override
    public List<Item> findByName(String nameItem) {
        List<Item> itemsKey = items.stream()
                .filter(
                        items -> items.getName().contains(nameItem)
                )
                .collect(Collectors.toList());
        return itemsKey;
    }

    /**
     * Метод находит заявку по id номеру.
     * @param id номер заявки;
     * @return result искомая заявка.
     */
    @Override
    public Item findById(String id) {
        //Item result = null;
        //for (Item item : this.items) {
        //    if (item != null && item.getId().equals(id)) {
        //        result = item;
        //        break;
        //    }
        //}
        List<Item> itemsId = items.stream()
                .filter(
                        items -> items != null && items.getId().contains(id)
                )
                .collect(Collectors.toList());
        return itemsId.get(0);
    }

    public static void main(String[] args) {

        System.out.println("start " + LocalDateTime.now());
        Tracker tracker = new Tracker();
        for (int i = 1; i < 10_000_000; i++) {
            Item item = new Item("test " + i, "desc " + i, System.currentTimeMillis());
            System.out.println(item);
            tracker.add(item);
            Runtime runtime = Runtime.getRuntime();
            System.out.println(String.format(
                    "Used memory %s B. Average item%s size %s B.",
                    (runtime.totalMemory() - runtime.freeMemory()),
                    i,
                    (runtime.totalMemory() - runtime.freeMemory()) / i)
            );

            System.out.println(String.format("estimated object size %s", sizeOf(item)));

        }

    }
}