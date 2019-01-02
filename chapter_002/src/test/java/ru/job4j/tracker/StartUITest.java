package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * StartUITest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 02.01.2019
 */

public class StartUITest {

    /**
     * тест createItem - добавление заявки.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        // создаём StubInput с последовательностью действий.
        Input input = new StubInput(new String[]{"0", "test name", "desc", "00", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * тест editItem - редактирование/замена заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        // напрямую добавляем заявку
        //Item item = tracker.add(new Item("test name", "desc", 123L));

        Item[] items = {new Item("test name1", "desc1", 123L),
                        new Item("test name2", "desc2", 124L),
                        new Item("test name3", "desc3", 125L)};
        for (Item x : items) {
            tracker.add(x);
        }

        // создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", items[1].getId(), "test replace", "заменили заявку", "00", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нужный элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(items[1].getId()).getName(), is("test replace"));
    }


    /**
     * тест deleteItem - удаление заявки.
     */
    @Test
    public void whenDeleteThenTrackerHasValue() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test name1", "desc1", 123L),
                        new Item("test name2", "desc2", 124L),
                        new Item("test name3", "desc3", 125L)};
        for (Item x : items) {
            tracker.add(x);
        }
        Input input = new StubInput(new String[]{"3", items[1].getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[1].getName(), is("test name3"));
    }
}
