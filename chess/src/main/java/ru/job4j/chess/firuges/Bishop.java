package ru.job4j.chess.firuges;

//import ru.job4j.chess.firuges.ImpossibleMoveException;

/**
 * Abstract class for Bishop.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 22.01.2018
 */
public abstract class Bishop implements Figure {

    /**
     * isDiagonal - проверяем, является ли траектория движения диагональю.
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        return (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y));
    }

    /**
     * bishopWay проверяет, может ли слон так ходить и возвращает массив клеток, кот. он должен пройти.
     */
    public Cell[] bishopWay(Cell source, Cell dest) {
        // Если не диагональ, генерируем исключение - неверная траектория.
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("You can't walk like that. ");
        }
        // Расчет дельт шагов.
        int deltaX = source.x > dest.x ? -1 : 1;
        int deltaY = source.y > dest.y ? -1 : 1;
        // Строим путь - заполняем массив шагов.
        int size = Math.abs(source.x - dest.x);
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            int x = source.x + (i + 1) * deltaX;
            int y = source.y + (i + 1) * deltaY;
            steps[i] = Cell.values()[8 * x + y];
        }
        return steps;
    }
}