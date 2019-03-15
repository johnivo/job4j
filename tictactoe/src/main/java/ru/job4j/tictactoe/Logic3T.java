package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isWinner(Predicate<Figure3T> predicate) {
        return this.fillBy(predicate, 0, 0, 1, 0)
                || this.fillBy(predicate, 0, 1, 1, 0)
                || this.fillBy(predicate, 0, 2, 1, 0)
                || this.fillBy(predicate, 0, 0, 0, 1)
                || this.fillBy(predicate, 1, 0, 0, 1)
                || this.fillBy(predicate, 2, 0, 0, 1)
                || this.fillBy(predicate, 0, 0, 1, 1)
                || this.fillBy(predicate, this.table.length - 1, 0, -1, 1);
    }

    public boolean isWinnerX() {
        return this.isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return this.isWinner(Figure3T::hasMarkO);
    }

    //public boolean hasGap() {
    //    boolean result = false;
    //    for (int i = 0; i != this.table.length; i++) {
    //        for (int j = 0; j != this.table[i].length; j++) {
    //            if (!table[i][j].hasMarkX() & !table[i][j].hasMarkO()) {
    //                result = true;
    //                break;
    //            }
    //        }
    //    }
    //    return result;
    //}

    public boolean hasGap() {
        //var r = false;
        boolean r = !Stream.of(this.table)
                //.flatMap(e -> Arrays.stream(e))
                .flatMap(Arrays::stream)
                .allMatch(e -> e.hasMarkO() || e.hasMarkX());
        return r;
    }
}