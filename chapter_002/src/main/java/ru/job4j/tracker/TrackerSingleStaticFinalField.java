package ru.job4j.tracker;

/**
 * Энергичная загрузка (Eager loading) - загружает объект сразу после старта виртуальной машины.
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.03.2019
 */
public class TrackerSingleStaticFinalField {

    // Аналогично static field, отличие в том, что сразу создаем и инициализируем объект.
    private static final Tracker INSTANCE = new Tracker();

    private TrackerSingleStaticFinalField() {
    }

    public static Tracker getInstance() {
        return INSTANCE;
    }
}
