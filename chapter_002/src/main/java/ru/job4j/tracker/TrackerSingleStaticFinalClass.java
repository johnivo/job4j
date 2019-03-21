package ru.job4j.tracker;

/**
 * Ленивая загрузка (Lazy loading) происходит, когда мы явно обращаемся к объекту.
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.03.2019
 */
public class TrackerSingleStaticFinalClass {

    private TrackerSingleStaticFinalClass() {
    }

    public static Tracker getInstance() {
        return Holder.INSTANCE;
    }

    // Объект класса находится в поле внутреннего класса.
    private static final class Holder {
        private static final Tracker INSTANCE = new Tracker();
    }
}
