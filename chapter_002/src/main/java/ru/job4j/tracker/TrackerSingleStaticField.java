package ru.job4j.tracker;

/**
 * Ленивая загрузка (Lazy loading) происходит, когда мы явно обращаемся к объекту.
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.03.2019
 */
public class TrackerSingleStaticField {

    // поле instance, которое содержит экземпляр объекта и метод getInstance().
    private static Tracker instance;

    // чтобы закрыть возможность создавать экземпляр класса,
    // нужно явно создать конструтор по умолчанию и присвоить ему модификатор private.
    private TrackerSingleStaticField() {
    }

    // при вызове метод проверяет статическое поле instance, если поле не загружено, оно инициализирует его новосозданном объектом.
    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }
}
