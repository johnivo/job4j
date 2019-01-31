package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TrackerTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 28.12.2018
 */
public class TrackerTest {

    /**
     * тест add - добавление заявки.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        //assertThat(tracker.findAll()[0], is(item));
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * тест replace - замена заявки.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новое имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * тест delete - удаление заявки.
     */
    @Test
    public void whenDeleteItemThenTrackerHasItems() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "testDescription1", 123L),
                        new Item("test2", "testDescription2", 124L),
                        new Item("test3", "testDescription3", 125L)};
        for (Item x : items) {
            tracker.add(x);
        }
        tracker.delete(items[1].getId());
        //Item[] result = tracker.findAll();
        List<Item> result = tracker.findAll();
        //assertThat(result[1].getName(), is("test3"));
        assertThat(result.get(1).getName(), is("test3"));
    }

    /**
     * тест findAll - получение списка заявок.
     */
    @Test
    public void whenAddItemsThenFindAllItems() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "testDescription1", 123L),
                        new Item("test2", "testDescription2", 124L),
                        new Item("test3", "testDescription3", 125L)};
        for (Item x : items) {
            tracker.add(x);
        }
        //int result = tracker.findAll().length;
        int result = tracker.findAll().size();
        assertThat(result, is(3));
    }

    /**
     * тест findByName - получение списка заявок по имени.
     */
    @Test
    public void whenFindByNameThenTrackerHasItems() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "testDescription1", 123L),
                        new Item("test2", "testDescription2", 124L),
                        new Item("test1", "testDescription3", 125L)};
        for (Item x : items) {
            tracker.add(x);
        }
        //int x = tracker.findByName("test1").length;
        int x = tracker.findByName("test1").size();
        assertThat(x, is(2));
    }

    /**
     * тест findById - поиск заявки по id.
     */
    @Test
    public void whenIdThenItem() {
        Tracker tracker = new Tracker();
        Item[] items = {
                new Item("test1", "testDescription1", 123L),
                new Item("test2", "testDescription2", 124L),
                new Item("test3", "testDescription3", 125L)
        };
        for (Item x : items) {
            tracker.add(x);
        }
        assertThat(tracker.findById(items[1].getId()), is(items[1]));
    }
}