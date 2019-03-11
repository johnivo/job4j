package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.OccupiedWayException;
import ru.job4j.chess.firuges.FigureNotFoundException;

import java.util.stream.IntStream;

//import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            for (Cell step : steps) {
                if (this.findBy(step) != -1) {
                    throw new OccupiedWayException("Move closed. ");
                }
            }
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        } else {
            throw new FigureNotFoundException("Empty cell, no figure. ");
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        //int rst = -1;
        //for (int index = 0; index != this.figures.length; index++) {
        //    if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
        //        rst = index;
        //        break;
        //    }
        //}
        int rst = IntStream
                .range(0, this.figures.length)
                .filter(
                        e -> figures[e] != null && figures[e].position().equals(cell)
                )
                .findAny()
                .orElse(-1);
        return rst;
    }
}
