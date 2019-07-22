package ru.job4j.interactcalc;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.07.2019
 */
public class StubInput implements Input {

    /**
     * Поле содержит последовательность ответов пользователя.
     */
    private final String[] value;

    /**
     * Поле считает количество вызовов метода ask.
     * При каждом вызове надо передвинуть указатель на новое число.
     */
    private int position;

    /**
     * Конструктор.
     * @param value массив ответов.
     */
    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * Симулятор поведения пользователя.
     * При каждом вызове метода ask счетчик увеличивается и
     * при следующем вызове он вернет новое значение.
     * @param question вопрос.
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    @Override
    public String next() {
        return null;
    }
}
