package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.logic.Cell;

/**
 * Описывает поведение игрока.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public interface Player {

    Cell move();

    void setMark(String mark);

    String getMark();

    String getName();

    //String getMoveMessage();

}
