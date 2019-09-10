package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.logic.Cell;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.Logic3T;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.09.2019
 */
public class Logic3TTest {

    @Test
    public void whenSetSizeTable3ThenGetTableSizeReturns3() {
        Logic logic3T = new Logic3T(3);

        int result = logic3T.getTableSize();

        assertThat(result, is(3));
    }

    @Test
    public void whenNewGameThenReturnedEmptyField() {
        Logic logic3T = new Logic3T(3);
        Cell cell = new Cell(0, 0);

        logic3T.makeMove(cell, "x");
        logic3T.newGame();
        String result = logic3T.getMark(cell);

        assertNull(result);
    }

    @Test
    public void whenMakeMoveThenMarkedOnField() {
        Logic logic3T = new Logic3T(3);
        Cell cell = new Cell(0, 0);

        logic3T.makeMove(cell, "x");
        String result = logic3T.getMark(cell);

        assertThat(result, is("x"));
    }

    @Test(expected = InvalidActionException.class)
    public void whenMakeMoveOnWrongCellThenTrowsAIE() {
        Logic logic3T = new Logic3T(3);
        Cell cell = new Cell(5, 0);

        logic3T.makeMove(cell, "x");
    }

    @Test(expected = InvalidActionException.class)
    public void whenMakeMoveOnBusyCellThenTrowsAIE() {
        Logic logic3T = new Logic3T(3);
        Cell cell = new Cell(1, 2);

        logic3T.makeMove(cell, "x");
        logic3T.makeMove(cell, "o");
    }

    @Test
    public void whenFirstStepThenMoveIsPossible() {
        Logic logic3T = new Logic3T(3);

        logic3T.newGame();
        boolean result = logic3T.checkPossibleOfMove();

        assertTrue(result);
    }

    @Test
    public void whenFieldFullThenMoveIsNotPossible() {
        Logic logic3T = new Logic3T(3);

        int size = logic3T.getTableSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                logic3T.makeMove(new Cell(i, j), "x");
            }
        }
        boolean result = logic3T.checkPossibleOfMove();

        assertFalse(result);
    }

    @Test
    public void whenHorFilledXThenWinnerX() {
        Logic logic3T = new Logic3T(3);

        logic3T.makeMove(new Cell(0, 0), "x");
        logic3T.makeMove(new Cell(0, 1), "x");
        logic3T.makeMove(new Cell(0, 2), "x");
        String result = logic3T.checkWinner();

        assertThat(result, is("x"));
    }

    @Test
    public void whenVertFilledOThenWinnerO() {
        Logic logic3T = new Logic3T(3);

        logic3T.makeMove(new Cell(0, 0), "o");
        logic3T.makeMove(new Cell(1, 0), "o");
        logic3T.makeMove(new Cell(2, 0), "o");
        String result = logic3T.checkWinner();

        assertThat(result, is("o"));
    }

    @Test
    public void whenDiagFilledOThenWinnerO() {
        Logic logic3T = new Logic3T(3);

        logic3T.makeMove(new Cell(0, 0), "o");
        logic3T.makeMove(new Cell(0, 1), "x");
        logic3T.makeMove(new Cell(1, 1), "o");
        logic3T.makeMove(new Cell(2, 1), "x");
        logic3T.makeMove(new Cell(2, 2), "o");
        String result = logic3T.checkWinner();

        assertThat(result, is("o"));
    }

}