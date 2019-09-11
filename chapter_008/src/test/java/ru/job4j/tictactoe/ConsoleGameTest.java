package ru.job4j.tictactoe;

import com.google.common.base.Joiner;
import org.junit.Test;
import ru.job4j.tictactoe.game.*;
import ru.job4j.tictactoe.logic.*;
import ru.job4j.tictactoe.player.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 11.09.2019
 */
public class ConsoleGameTest {

    private static final String LN = System.lineSeparator();

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(baos);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenUserSelectedXThenGameScoreStartsWithX() {

        Logic logic = new Logic3T(3);
        String firstUsersStep = Joiner.on(LN).join(
                "x",
                ""
        );
        Player user = new User(logic, "user", new ByteArrayInputStream(firstUsersStep.getBytes()), output);
        Player bot = new BotSimple(logic, user, "bot");
        Game game = new ConsoleGame(logic, user, bot, 5, output);

        game.newGame();

        String expected = Joiner.on(LN).join(
                "Игра крестики-нолики. Выберите фигуру x или o:",
                "Партия №1, счет (x 0:0 o). Игра до 5 побед.",
                "00 01 02 ",
                "10 11 12 ",
                "20 21 22 ",
                "",
                ""
        );
        assertThat(baos.toString(), containsString("счет (x 0:0 o)"));
        assertThat(baos.toString(), is(expected));
    }

}