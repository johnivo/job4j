package ru.job4j.tictactoe;

import ru.job4j.tictactoe.game.*;
import ru.job4j.tictactoe.logic.*;
import ru.job4j.tictactoe.player.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 11.09.2019
 */
public class TicTacToe {

    public static void main(String[] args) {
        Logic logic = new Logic3T(3);

        Player first = new User(logic, "user", System.in, System.out::println);
        Player second = new BotSimple(logic, "bot");

        Game cg = new ConsoleGame(logic, first, second, 5, System.out::println);

        cg.newGame();
        while (!cg.exitGame()) {
            cg.nextMove();
        }
    }

}
