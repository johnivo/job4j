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
    public void whenUserVsBotThenGameScoreStartsWithGreeting() {
        Logic logic = new Logic3T(3);
        Player user = new User(logic, "user", System.in, output);
        Player bot = new BotSimple(logic, "bot");
        Game game = new ConsoleGame(logic, user, bot, 5, output);

        game.newGame();

        String expected = Joiner.on(LN).join(
                "Партия №1, счет (x 0:0 o). Игра до 5 побед.",
                "00 01 02 ",
                "10 11 12 ",
                "20 21 22 ",
                "",
                ""
        );
        assertThat(baos.toString(), is(expected));
    }

    @Test
    public void whenUser1WinGameToOneVictoryThenExitTrue() {

        Logic logic = new Logic3T(3);
        String firstUsersStep = Joiner.on(LN).join("00", "01", "02");
        Player user1 = new User(logic, "user1", new ByteArrayInputStream(firstUsersStep.getBytes()), output);
        String secondUsersStep = Joiner.on(LN).join("10", "20");
        Player user2 = new User(logic, "user2", new ByteArrayInputStream(secondUsersStep.getBytes()), output);
        Game game = new ConsoleGame(logic, user1, user2, 1, output);

        for (int i = 1; i <= 5; i++) {
            game.nextMove();
        }
        boolean result = game.exitGame();

        assertThat(result, is(true));
    }

    @Test
    public void whenUser1ToTwoVictoriesThenExitTrue() {

        Logic logic = new Logic3T(3);
        String firstUsersStep = Joiner.on(LN).join("00", "01", "02",
                "00", "01", "02"
        );
        Player user1 = new User(logic, "user1", new ByteArrayInputStream(firstUsersStep.getBytes()), output);
        String secondUsersStep = Joiner.on(LN).join("10", "20",
                "10", "20", "11"
        );
        Player user2 = new User(logic, "user2", new ByteArrayInputStream(secondUsersStep.getBytes()), output);
        Game game = new ConsoleGame(logic, user1, user2, 2, output);

        for (int i = 1; i <= 11; i++) {
            game.nextMove();
        }
        boolean result = game.exitGame();

        assertThat(result, is(true));
    }

    @Test
    public void whenUser1VsUser2ThenUser1Wins() {
        Logic logic = new Logic3T(3);
        String firstUsersStep = Joiner.on(LN).join("11", "01", "21", "");
        Player user1 = new User(logic, "user1", new ByteArrayInputStream(firstUsersStep.getBytes()), output);
        String secondUsersStep = Joiner.on(LN).join("22", "12", "");
        Player user2 = new User(logic, "user2", new ByteArrayInputStream(secondUsersStep.getBytes()), output);
        Game game = new ConsoleGame(logic, user1, user2, 1, output);

        while (!game.exitGame()) {
            game.nextMove();
        }

        assertThat(baos.toString(), containsString("user1 сделал ход в клетку (1;1):"));
        assertThat(baos.toString(), containsString("Игра закончена, победили крестики, счет (x 1:0 o)"));
    }

    @Test
    public void whenUser1VsUser2ThenDrawGame() {
        Logic logic = new Logic3T(3);
        String firstUsersStep = Joiner.on(LN).join("11", "00", "12", "21", "02");
        Player user1 = new User(logic, "user1", new ByteArrayInputStream(firstUsersStep.getBytes()), output);
        String secondUsersStep = Joiner.on(LN).join("22", "20", "01", "10");
        Player user2 = new User(logic, "user2", new ByteArrayInputStream(secondUsersStep.getBytes()), output);
        Game game = new ConsoleGame(logic, user1, user2, 1, output);

        for (int i = 1; i <= 9; i++) {
            game.nextMove();
        }

        assertThat(baos.toString(), containsString("Ничья! Начните новую партию."));
    }

    @Test
    public void whenBot1VsBot2ThenSomeWins() {
        Logic logic = new Logic3T(3);
        Player bot1 = new BotSimple(logic, "bot1");
        Player bot2 = new BotSimple(logic, "bot2");
        Game game = new ConsoleGame(logic, bot1, bot2, 5, output);

        while (!game.exitGame()) {
            game.nextMove();
        }

        assertThat(baos.toString(), containsString("Игра закончена, победили"));
    }

}