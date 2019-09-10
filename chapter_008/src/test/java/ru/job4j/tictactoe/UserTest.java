package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.logic.*;
import ru.job4j.tictactoe.player.*;

import java.io.*;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public class UserTest {

    /**
     * Буфер для результата.
     */
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();

    /**
     * Заменяет стандартный вывод System.out на mem.
     */
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(mem);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    /**
     * Тест хода пользователя.
     */
    @Test
    public void whenUserMove11ThenReturnsCell21() {

        String input = "11";

        try (ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes())
        ) {
            System.setIn(bais);

            Logic logic3T = new Logic3T(3);
            Player user = new User(logic3T, "User", bais, output);

            Cell cell = user.move();
            int x = cell.getX();
            int y = cell.getY();

            assertThat(x, is(1));
            assertThat(y, is(1));

            System.setIn(System.in);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}