package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.InvalidActionException;
import ru.job4j.tictactoe.logic.Cell;
import ru.job4j.tictactoe.logic.Logic;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Реализация игрока-пользователя
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public class User implements Player {

    private final Logic logic;
    private final String name;
    private String mark;

    private final Scanner scanner;
    private final Consumer<String> output;
    //private final PrintStream ps;

    /**
     * Конструктор, инициализирует игровую логику, имя игрока, ввод-вывод.
     * @param logic игровая логика.
     * @param name имя игрока.
     * @param is поток ввода.
     * @param output вывод.
     */
    public User(Logic logic, String name, InputStream is, Consumer<String> output) {
        this.logic = logic;
        this.name = name;
        this.scanner = new Scanner(is);
        this.output = output;
        //this.ps = ps;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return this.mark;
    }

    /**
     * Выполняет ход в заданную клетку
     * @return выбранная клетка
     */
    @Override
    public Cell move() {

        Cell cell = null;
        boolean moved = false;
        while (!moved) {
            cell = getCell();
            try {
                logic.makeMove(cell, this.getMark());
                moved = true;
            } catch (InvalidActionException iae) {
                iae.getMessage();
            }
        }
        return cell;
    }

    /**
     * Парсит введенные пользователем данные
     * @return клетка с заданными координатами (xy)
     */
    private Cell getCell() {
        Cell cell = null;

        output.accept("введите координаты точки в формате двух последовательных чисел xy:");

        String input = scanner.next();
        try {
            int x = Integer.parseInt(input.substring(0, 1));
            int y = Integer.parseInt(input.substring(1, 2));
            cell = new Cell(x, y);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return cell;
    }

}
