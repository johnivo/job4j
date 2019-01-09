package ru.job4j.tracker;

import java.util.*;

/**
 * Реализация меню трекера.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 08.01.2019
 */
public class MenuTracker {

    /**
     * Хранит ссылку на объект.
     */
    private final Input input;
    /**
     * Хранит ссылку на объект.
     */
    private final Tracker tracker;
    /**
     * Хранит ссылку на массив типа UserAction.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива.
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new EditItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
        this.actions.add(new ExitProgram());
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * 0 Добавление новой заявки.
     */
    public class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            String created = input.ask("Please, provide item time created:");
            Item item = new Item(name, desc, Long.parseLong(created));
            tracker.add(item);
            System.out.println("New Item: " + item.toString());
        }

        @Override
        public String info() {
            return "0. Add new Item.";
        }
    }

    /**
     * 1 Вывод списока всех заявок.
     */
    public class ShowItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ All available items: --------------");
            Item[] items = tracker.findAll();
            if (items.length != 0) {
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("------------ No items available. -----------");
            }
        }

        @Override
        public String info() {
            return "1. Show all items.";
        }
    }

    /**
     * 2 Редактирование выбранной заявки.
     */
    public class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Editing item: --------------");
            String id = input.ask("Please, provide id item:");
            String name = input.ask("Please, edit item name:");
            String desc = input.ask("Please, edit item description:");
            String created = input.ask("Please, edit item create:");
            Item item = new Item(name, desc, Long.parseLong(created));
            if (tracker.replace(id, item)) {
                System.out.println("Item with id:" + id + " edited!");
            } else {
                System.out.println("------------ No item with such id. -----------");
            }
        }

        @Override
        public String info() {
            return "2. Edit item.";
        }
    }

    /**
     * 3 Удаление выбранной заявки.
     */
    public class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Deleting item: --------------");
            String id = input.ask("Please, provide id item:");
            if (tracker.delete(id)) {
                System.out.println("Item with id:" + id + " deleted!");
            } else {
                System.out.println("------------ No item with such id. -----------");
            }
        }

        @Override
        public String info() {
            return "3. Delete item.";
        }
    }

    /**
     * 4 Поиск заявки по id.
     */
    public class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Find item by id: --------------");
            String id = input.ask("Please, provide id item:");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Item found: " + item.toString());
            } else {
                System.out.println("------------ No item with such id. -----------");
            }
        }

        @Override
        public String info() {
            return "4. Find item by Id.";
        }
    }

    /**
     * 5 Поиск одноименных заявок.
     */
    public class FindItemsByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Find item by name: --------------");
            String nameItem = input.ask("Please, provide name item:");
            Item[] items = tracker.findByName(nameItem);
            if (items.length != 0) {
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("------------ No items with such name. -----------");
            }
        }

        @Override
        public String info() {
            return "5. Find items by name.";
        }
    }

    /**
     * 6 Выход из программы.
     */
    public class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            }

        @Override
        public String info() {
            return "6. Exit Program.";
        }
    }
}