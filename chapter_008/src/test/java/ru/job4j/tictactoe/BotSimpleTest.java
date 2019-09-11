package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.logic.*;
import ru.job4j.tictactoe.player.*;

import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public class BotSimpleTest {

    @Test
    public void whenBotMoveThenCellIsWithinField() {
        Logic logic3T = new Logic3T(3);
        Player user = new User(logic3T, "user", System.in, System.out::println);
        Player bot = new BotSimple(logic3T, user, "Simple bot");

        Cell cell = bot.move();

        assertTrue(cell.getX() >= 0 && cell.getX() < 3);
        assertTrue(cell.getY() >= 0 && cell.getY() < 3);
    }

}