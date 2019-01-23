package ru.job4j.chess.firuges;

/**
 * Abstract class for the Qeen.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 23.01.2018
 */
public abstract class Qeen implements Figure {

    public boolean isDiagonal(Cell source, Cell dest) {
        return (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y));
    }

    public boolean isLine(Cell source, Cell dest) {
        return (source.x == dest.x || source.y == dest.y);
    }

    public Cell[] qeenWay(Cell source, Cell dest) {
        if (!isLine(source, dest) && !isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("You can't walk like that. ");
        }
        if (isLine(source, dest)) {
            if (source.x != dest.x) {
                int deltaX = source.x > dest.x ? -1 : 1;
                int size = Math.abs(source.x - dest.x);
                Cell[] steps = new Cell[size];
                for (int i = 0; i < size; i++) {
                    int x = source.x + (i + 1) * deltaX;
                    int y = source.y;
                    steps[i] = Cell.values()[8 * x + y];
                }
                return steps;
            } else {
                int deltaY = source.y > dest.y ? -1 : 1;
                int size = Math.abs(source.y - dest.y);
                Cell[] steps = new Cell[size];
                for (int i = 0; i < size; i++) {
                    int x = source.x;
                    int y = source.y + (i + 1) * deltaY;
                    steps[i] = Cell.values()[8 * x + y];
                }
                return steps;
            }
        } else {
            int deltaX = source.x > dest.x ? -1 : 1;
            int deltaY = source.y > dest.y ? -1 : 1;
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
}
