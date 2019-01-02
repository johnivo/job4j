package ru.job4j.tracker;

/**
 * Точка входа в программу.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 01.01.2019
 */
public class StartUI {

    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для вывода списка всех заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявок.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявок.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа меню поиска одноименных заявок.
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                //добавление заявки вынесено в отдельный метод.
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            } else {
                System.out.println("Повторите ввод");
            }
        }
    }

    /**
     * Метод реализует добавление новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        String created = this.input.ask("Введите время создания заявки :");
        Item item = new Item(name, desc, Long.parseLong(created));
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует вывод списка всех заявок.
     */
    private void showAllItems() {
        System.out.println("------------ Список заявок на данный момент --------------");
        Item[] items = this.tracker.findAll();
        if (items.length != 0) {
            for (Item item : items) {
                System.out.println(item);
                System.out.println("------------------------------------------------------------");
            }
        } else {
            System.out.println("------------ Заявок нет -----------");
        }
    }

    /**
     * Метод реализует редактирование заявки.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите id редактируемой заявки :");
        String name = this.input.ask("Отредактируйте имя заявки :");
        String desc = this.input.ask("Отредактируйте описание заявки :");
        String created = this.input.ask("Введите время редактирования заявки :");
        Item item = new Item(name, desc, Long.parseLong(created));
        if (this.tracker.replace(id, item)) {
            System.out.println("Заявка : " + item + " отредактирована");
        } else {
            System.out.println("------------ Введен некорректный id -----------");
        }
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите id удаляемой заявки :");
        if (this.tracker.delete(id)) {
            System.out.println("------------ Заявка удалена -----------");
        } else {
            System.out.println("------------ Введен некорректный id -----------");
        }
    }

    /**
     * Метод реализует поиск заявки по id.
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по id --------------");
        String id = this.input.ask("Введите id заявки :");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("Найдена заявка : " + item);
        } else {
            System.out.println("------------ Введен некорректный id -----------");
        }
    }

    /**
     * Метод реализует поиск одноименных заявок.
     */
    private void findItemsByName() {
        System.out.println("------------ Поиск одноименных заявок --------------");
        String key = this.input.ask("Введите искомое имя :");
        Item[] items = this.tracker.findByName(key);
        if (items.length != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("------------ Введено некорректное имя -----------");
        }
    }

    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заявку "
                            + "1. Вывести список всех заявок "
                            + "2. Редактировать заявку "
                            + "3. Удалить заявку "
                            + "4. Найти заявку по ID "
                            + "5. Найти заявки по имени "
                            + "6. Выход ");
    }

    /**
     * Запуск программы.
     * @param args .
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}