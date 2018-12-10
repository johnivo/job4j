package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * DummyBotTest - class test.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 10.12.2018
 */
public class DummyBotTest {

    /**
     * тест приветствия.
     */
    @Test
    public void whenGreetBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Привет, Бот."), is("Привет, умник."));
    }

    /**
     * тест пока.
     */
    @Test
    public void whenByuBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Пока."), is("До скорой встречи."));
    }

    /**
     * тест тупика.
     */
    @Test
    public void whenUnknownBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Сколько будет 2 + 2?"), is("Это ставит меня в тупик. Спросите другой вопрос."));
    }
}