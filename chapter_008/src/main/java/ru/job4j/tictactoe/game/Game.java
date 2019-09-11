package ru.job4j.tictactoe.game;

/**
 * Описывает игру
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public interface Game {

    void newGame();

    void showTable();

    void nextMove();

    boolean exitGame();

}
