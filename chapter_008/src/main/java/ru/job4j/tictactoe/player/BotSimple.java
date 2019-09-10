package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.logic.*;

/**
 * Реализация простого игрока-бота
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public class BotSimple implements Player {

    private final Logic logic;
    private final int tableSize;
    private final String name;
    private String mark;

    /**
     * Конструктор, инициализирует игровую логику и имя игрока.
     * @param logic игровая логика.
     * @param name имя игрока.
     */
    public BotSimple(Logic logic, String name) {
        this.logic = logic;
        this.tableSize = logic.getTableSize();
        this.name = name;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String getMark() {
        return this.mark;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Делает ход случайным образом
     * @return выбранная клетка
     */
    @Override
    public Cell move() {
        Cell cell = null;
        boolean moved = false;
        while (!moved) {
            int x = (int) (Math.random() * tableSize);
            int y = (int) (Math.random() * tableSize);
            cell = new Cell(x, y);
            if (logic.getMark(cell) == null) {
                logic.makeMove(cell, this.getMark());
                moved = true;
            }
        }
        return cell;
    }

}
