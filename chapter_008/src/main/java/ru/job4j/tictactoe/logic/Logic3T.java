package ru.job4j.tictactoe.logic;

import ru.job4j.tictactoe.InvalidActionException;

import java.util.function.Predicate;

/**
 * Реализация логики игры для поля размером 3*3
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.09.2019
 */
public class Logic3T implements Logic {

    /**
     * Размер поля.
     */
    private int size;

    /**
     * Массив содержит игровое поле.
     */
    private String[][] table;

    /**
     * Счетчик ходов.
     */
    private int moveCounter = 0;

    /**
     * Поле хранит победителя (x или o).
     */
    private String winner;

    /**
     * Конструктор, инициализирует игровое поле нужного размера
     * @param size размер игрового поля.
     */
    public Logic3T(int size) {
        this.size = size;
        this.table = new String[size][size];
    }

    /**
     * Начинает новую игру.
     * очищает поле, сбрасывает победителя
     */
    @Override
    public void newGame() {
        this.moveCounter = 0;
        this.table = new String[this.size][this.size];
        this.winner = null;
    }

    /**
     * Возвращает размер игрового поля
     */
    @Override
    public int getTableSize() {
        return this.size;
    }

    /**
     * Возвращает значение заданной клетки поля
     * @param cell клетка
     * @return символ в клетке
     */
    @Override
    public String getMark(Cell cell) {
        int size = table.length;
        if (cell.getX() >= size || cell.getY() >= size) {
            throw new InvalidActionException(String.format("Размер поля %s на %s, повторите ввод", size, size));
        }
        return table[cell.getX()][cell.getY()];
    }

    /**
     * Выполняет ход:
     * проверяет, свободна ли заданная клетка
     * если свободна - ставит в ней заданную отметку
     * @param cell клетка
     * @param mark символ в клетке
     */
    @Override
    public void makeMove(Cell cell, String mark) {
        String markCell = getMark(cell);
        if (markCell != null) {
            throw new InvalidActionException(String.format("Клетка (%s;%s) занята, повторите ввод", cell.getX(), cell.getY()));
        } else {
            table[cell.getX()][cell.getY()] = mark;
            moveCounter++;
        }
    }

    /**
     * Проверяет допустимое количество ходов.
     * @return возможность хода.
     */
    @Override
    public boolean checkPossibleOfMove() {
        return moveCounter < table.length * table.length;
    }

    /**
     * Проверяет, кто победил,
     * если игрок заполнил полностью вертикаль, горизонталь или диагональ.
     * @return победитель "x" или "o", либо null.
     */
    @Override
    public String checkWinner() {
        if (isWinnerX()) {
            winner = "x";
        } else if (isWinnerO()) {
            winner = "o";
        }
        return winner;
    }


    /**
     * Проверяет победу x.
     * @return x победили.
     */
    public boolean isWinnerX() {
        return this.isWinner("x"::equals);
    }

    /**
     * Проверяет победу o.
     * @return o победили.
     */
    public boolean isWinnerO() {
        return this.isWinner("o"::equals);
    }

    /**
     * Проверяет все вертикали, горизонтали и диагоняли поля на соответствие предикату.
     *
     * @param predicate - задает условие проверки (x или o).
     * @return наличие линии символов, соответствющих предикату.
     */
    private boolean isWinner(Predicate<String> predicate) {
        return this.fillBy(predicate, 0, 0, 1, 0)
                || this.fillBy(predicate, 0, 1, 1, 0)
                || this.fillBy(predicate, 0, 2, 1, 0)
                || this.fillBy(predicate, 0, 0, 0, 1)
                || this.fillBy(predicate, 1, 0, 0, 1)
                || this.fillBy(predicate, 2, 0, 0, 1)
                || this.fillBy(predicate, 0, 0, 1, 1)
                || this.fillBy(predicate, this.table.length - 1, 0, -1, 1);
    }

    /**
     * Проверяет все символы в линии на соответствие предикату.
     *
     * @param predicate задает условие проверки (x или o).
     * @param startX начальная координата по оси x
     * @param startY начальная координата по оси y
     * @param deltaX инкремент по оси x
     * @param deltaY инкремент по оси y
     * @return наличие линии символов, соответствющих предикату.
     */
    public boolean fillBy(Predicate<String> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int i = 0; i != this.table.length; i++) {
            String cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

}
