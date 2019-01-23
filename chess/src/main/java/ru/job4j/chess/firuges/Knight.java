package ru.job4j.chess.firuges;

/**
 * Abstract class for a Knight.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 23.01.2018
 */
public abstract class Knight implements Figure {

    public boolean isKnight(Cell source, Cell dest) {
        return ((Math.abs(source.y - dest.y) ==  1) && (Math.abs(source.x - dest.x) ==  2)
                || (Math.abs(source.y - dest.y) ==  2) && (Math.abs(source.x - dest.x) ==  1));
    }

    public Cell[] knightWay(Cell source, Cell dest) {
        if (!isKnight(source, dest)) {
            throw new ImpossibleMoveException("You can't walk like that. ");
        }
        return new Cell[] {dest};
    }
}
