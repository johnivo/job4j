package ru.job4j.chess.firuges;

/**
 * Abstract class for the King.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 23.01.2018
 */
public abstract class King implements Figure {

    public boolean isKing(Cell source, Cell dest) {
        return ((Math.abs(source.y - dest.y) ==  1) && (Math.abs(source.x - dest.x) ==  0)
                || (Math.abs(source.y - dest.y) ==  1) && (Math.abs(source.x - dest.x) ==  1)
                || (Math.abs(source.y - dest.y) ==  0) && (Math.abs(source.x - dest.x) ==  1));
    }

    public Cell[] kingWay(Cell source, Cell dest) {
        if (!isKing(source, dest)) {
            throw new ImpossibleMoveException("You can't walk like that. ");
        }
        return new Cell[] {dest};
    }
}
