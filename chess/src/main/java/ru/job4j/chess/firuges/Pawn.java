package ru.job4j.chess.firuges;

/**
 * Abstract class for a Pawn.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 23.01.2018
 */
public abstract class Pawn implements Figure {

    public Cell[] pawnWayBlack(Cell source, Cell dest) {
        Cell[] steps;
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[] {dest};
        } else {
            throw new ImpossibleMoveException("You can't walk like that. ");
        }
        return steps;
    }

    public Cell[] pawnWayWhite(Cell source, Cell dest) {
        Cell[] steps;
        if (source.y == dest.y - 1 && source.x == dest.x) {
            steps = new Cell[] {dest};
        } else {
            throw new ImpossibleMoveException("You can't walk like that. ");
        }
        return steps;
    }

}
