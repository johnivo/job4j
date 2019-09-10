package ru.job4j.tictactoe.game;

import ru.job4j.tictactoe.logic.*;
import ru.job4j.tictactoe.player.*;

import java.util.function.Consumer;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public class ConsoleGame implements Game {

    private final Logic logic;
    private final int tableSize;

    private final Player first;
    private final Player second;
    private Player nextMovePlayer;

    private final int winCounter;
    private int gameCounter;
    private int xWins;
    private int oWins;

    private final Consumer<String> output;
    //private final PrintStream ps;

    private boolean exit;

    /**
     * Конструктор, инициализирует игровую логику, игроков, счетчик побед, вывод.
     * @param logic игровая логика.
     * @param first первый игрок.
     * @param second второй игрок.
     * @param winCounter счетчик побед.
     * @param output вывод.
     */
    public ConsoleGame(Logic logic, Player first, Player second, int winCounter, Consumer<String> output) {
        this.logic = logic;
        this.first = first;
        this.second = second;
        this.winCounter = winCounter;
        this.output = output;
        //this.ps = ps;
        this.gameCounter = 1;
        this.tableSize = logic.getTableSize();
        this.first.setMark("x");
        this.second.setMark("o");
        //this.nextMovePlayer = first;
        this.nextMovePlayer = second;
        this.exit = false;
    }

    /**
     * Начинает новую игру
     * выводит приветствие, начинает новую партию.
     */
    @Override
    public void newGame() {
        output.accept(
                String.format(
                        "Партия №%s, счет (%s %s:%s %s). Игра до %s побед.",
                        gameCounter, first.getMark(), xWins, oWins, second.getMark(), winCounter
                )
        );
        this.gameCounter++;
        this.logic.newGame();
        showTable();
    }

    /**
     * Выводит поле на консоль.
     */
    @Override
    public void showTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                String mark = logic.getMark(new Cell(i, j));
                sb.append(String.format("%s ", (mark == null ? "-" : mark)));
            }
            sb.append(System.lineSeparator());
        }
        output.accept(sb.toString());
    }

    /**
     * Осуществляет следующий ход игрока
     * выводит обновленное поле и проверяет есть ли победитель
     */
    @Override
    public void nextMove() {
        Cell cell = this.nextMovePlayer.move();

        output.accept(
                String.format(
                        this.nextMovePlayer.getName() + " сделал ход в клетку (%s;%s):",
                        cell.getX(), cell.getY()
                )
        );

        this.nextMovePlayer = first.equals(this.nextMovePlayer) ? second : first;
        showTable();
        checkWinner();
    }

    /**
     * Проверяет, есть ли победитель в игре до n побед.
     */
    private void checkWinner() {

        boolean hasWinner = false;
        if ("o".equals(logic.checkWinner())) {
            output.accept("Победили нолики! Начните новую партию.");
            this.oWins++;
            hasWinner = true;
        } else if ("x".equals(logic.checkWinner())) {
            output.accept("Победили крестики! Начните новую партию.");
            this.xWins++;
            hasWinner = true;
        }

        if (oWins >= winCounter) {
            output.accept(
                    String.format(
                            "Игра закончена, победили нолики, счет (%s %s:%s %s)",
                            first.getMark(), xWins, oWins, second.getMark()
                    )
            );
            exit = true;
        } else if (xWins >= winCounter) {
            output.accept(
                    String.format(
                            "Игра закончена, победили крестики, счет (%s %s:%s %s)",
                            first.getMark(), xWins, oWins, second.getMark()
                    )
            );
            exit = true;
        }

        if (hasWinner && !exit) {
            newGame();
        } else if (!logic.checkPossibleOfMove()) {
            output.accept("Ничья! Начните новую партию.");
            newGame();
        }
    }

    /**
     * Флаг выхода из игры.
     */
    @Override
    public boolean exit() {
        return exit;
    }

    public static void main(String[] args) {

        Logic logic = new Logic3T(3);
        Player one = new BotSimple(logic, "bot");
        Player two = new User(logic, "user", System.in, System.out::println);
        Game cg = new ConsoleGame(logic, one, two, 5, System.out::println);

        cg.newGame();
        while (!cg.exit()) {
            cg.nextMove();
        }

    }
}
