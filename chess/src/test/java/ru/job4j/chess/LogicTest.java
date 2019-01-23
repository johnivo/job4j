package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.containsString;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.FigureNotFoundException;
import ru.job4j.chess.firuges.ImpossibleMoveException;
import ru.job4j.chess.firuges.OccupiedWayException;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.*;

/**
 * LogicTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 23.01.2019
 */
public class LogicTest {

    /**
     * Test move right up BishopBlack.
     */
    @Test
    public void whenBishopBlackMoveRightUpThenTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F8));
        boolean result = logic.move(Cell.F8, Cell.D6);
        assertThat(result, is(true));
    }

    /**
     * Test move left down BishopBlack.
     */
    @Test
    public void whenBishopBlackMoveLeftDownThenTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.D6));
        boolean result = logic.move(Cell.D6, Cell.F8);
        assertThat(result, is(true));
    }

    /**
     * Test way BishopBlack.
     */
    @Test
    public void whenBishopBlackWayThenCellSteps() {
        BishopBlack bishopBlack = new BishopBlack(Cell.F8);
        Cell[] result = bishopBlack.way(Cell.F8, Cell.C5);
        Cell[] expect = {Cell.E7, Cell.D6, Cell.C5};
        assertThat(result, is(expect));
    }

    /**
     * Test move forward KingBlack.
     */
    @Test
    public void whenKingBlackMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new KingBlack(Cell.E8));
        boolean result = logic.move(Cell.E8, Cell.E7);
        assertThat(result, is(true));
    }

    /**
     * Test move forward RookBlack.
     */
    @Test
    public void whenRookMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new RookBlack(Cell.G6));
        boolean result = logic.move(Cell.G6, Cell.G3);
        assertThat(result, is(true));
    }

    /**
     * Test move right RookBlack.
     */
    @Test
    public void whenRookMoveRightThenTrue() {
        Logic logic = new Logic();
        logic.add(new RookBlack(Cell.G6));
        boolean result = logic.move(Cell.G6, Cell.D6);
        assertThat(result, is(true));
    }

    /**
     * Test way RookBlack.
     */
    @Test
    public void whenRookBlackWayThenCellSteps() {
        RookBlack rookBlack = new RookBlack(Cell.G6);
        Cell[] result = rookBlack.way(Cell.G6, Cell.D6);
        Cell[] expect = {Cell.F6, Cell.E6, Cell.D6};
        assertThat(result, is(expect));
    }

    /**
     * Test move PawnBlack.
     */
    @Test
    public void whenPawnBlackMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.G7));
        boolean result = logic.move(Cell.G7, Cell.G6);
        assertThat(result, is(true));
    }

    /**
     * Test move PawnWhite.
     */
    @Test
    public void whenPawnWhiteMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new PawnWhite(Cell.A2));
        boolean result = logic.move(Cell.A2, Cell.A3);
        assertThat(result, is(true));
    }

    /**
     * Test way PawnBlack.
     */
    @Test
    public void whenPawnBlackWayThenCellSteps() {
        PawnBlack pawnBlack = new PawnBlack(Cell.A7);
        Cell[] result = pawnBlack.way(Cell.A7, Cell.A6);
        Cell[] expect = {Cell.A6};
        assertThat(result, is(expect));
    }

    /**
     * Test move forward QeenBlack.
     */
    @Test
    public void whenQeenBlackMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new QeenBlack(Cell.E8));
        boolean result = logic.move(Cell.E8, Cell.E3);
        assertThat(result, is(true));
    }

    /**
     * Test move right up QeenBlack.
     */
    @Test
    public void whenQeenBlackMoveRightUpThenTrue() {
        Logic logic = new Logic();
        logic.add(new QeenBlack(Cell.E8));
        boolean result = logic.move(Cell.E8, Cell.E5);
        assertThat(result, is(true));
    }

    /**
     * Test way QeenBlack.
     */
    @Test
    public void whenQeenBlackWayThenCellSteps() {
        QeenBlack qeenBlack = new QeenBlack(Cell.E8);
        Cell[] result = qeenBlack.way(Cell.E8, Cell.E5);
        Cell[] expect = {Cell.E7, Cell.E6, Cell.E5};
        assertThat(result, is(expect));
    }

    /**
     * Test move forward KnightBlack.
     */
    @Test
    public void whenKnightBlackMoveForwardThenTrue() {
        Logic logic = new Logic();
        logic.add(new KnightBlack(Cell.F5));
        boolean result = logic.move(Cell.F5, Cell.G7);
        assertThat(result, is(true));
    }

    /**
     * Test way KnightBlack.
     */
    @Test
    public void whenKnightBlackWayThenCellSteps() {
        KnightBlack knightBlack = new KnightBlack(Cell.F5);
        Cell[] result = knightBlack.way(Cell.F5, Cell.D6);
        Cell[] expect = {Cell.D6};
        assertThat(result, is(expect));
    }

    /**
     * Test ImpossibleMoveException for KnightBlack.
     */
    @Test
    public void testImpossibleMoveExceptionForKnightBlack() {
        try {
            Logic logic = new Logic();
            logic.add(new KnightBlack(Cell.F8));
            logic.move(Cell.F8, Cell.F6);
        } catch (ImpossibleMoveException ime) {
            assertThat(ime.getMessage(), containsString("You can't walk like that. "));
        }
    }

    /**
     * Test ImpossibleMoveException for BishopBlack.
     */
    @Test
    public void testImpossibleMoveExceptionForBishopBlack() {
        try {
            Logic logic = new Logic();
            logic.add(new BishopBlack(Cell.F8));
            logic.move(Cell.F8, Cell.G8);
        } catch (ImpossibleMoveException ime) {
            assertThat(ime.getMessage(), containsString("You can't walk like that. "));
        }
    }

    /**
     * Test OccupiedWayException for KnightBlack.
     */
    @Test
    public void testOccupiedWayExceptionForKnightBlack() {
        try {
            Logic logic = new Logic();
            logic.add(new KnightBlack(Cell.H3));
            logic.add(new KnightWhite(Cell.F4));
            logic.move(Cell.H3, Cell.F4);
        } catch (OccupiedWayException owe) {
            assertThat(owe.getMessage(), containsString("Move closed. "));
        }
    }

    /**
     * Test OccupiedWayException for BishopBlack.
     */
    @Test
    public void testOccupiedWayExceptionBishopBlack() {
        try {
            Logic logic = new Logic();
            logic.add(new BishopBlack(Cell.H3));
            logic.add(new BishopWhite(Cell.F5));
            logic.move(Cell.H3, Cell.F5);
        } catch (OccupiedWayException owe) {
            assertThat(owe.getMessage(), containsString("Move closed. "));
        }
    }

    /**
     * Test OccupiedWayException for RookBlack.
     */
    @Test
    public void testOccupiedWayExceptionRookBlack() {
        try {
            Logic logic = new Logic();
            logic.add(new RookBlack(Cell.H3));
            logic.add(new BishopWhite(Cell.H6));
            logic.move(Cell.H3, Cell.H7);
        } catch (OccupiedWayException owe) {
            assertThat(owe.getMessage(), containsString("Move closed. "));
        }
    }

    /**
     * Test FigureNotFoundException.
     */
    @Test
    public void testFigureNotFoundException() {
        try {
            Logic logic = new Logic();
            //logic.add(new BishopBlack(Cell.F5));
            logic.move(Cell.H3, Cell.G4);
        } catch (FigureNotFoundException fne) {
            assertThat(fne.getMessage(), containsString("Empty cell, no figure. "));
        }
    }
}