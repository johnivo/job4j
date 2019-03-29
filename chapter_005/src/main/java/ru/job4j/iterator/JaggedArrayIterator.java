package ru.job4j.iterator;

import java.util.Iterator;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 28.03.2019
 */
public class JaggedArrayIterator implements Iterator {

    private final int[][] values;
    private int row = 0;
    private int column = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length > row && values[row].length > column;
    }

    @Override
    public Object next() {
        int result = values[row][column++];
        if (column == values[row].length) {
            column = 0;
            row++;
        }
        return result;
    }
}
