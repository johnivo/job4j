package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.03.2019
 */
public class EvenIt implements Iterator {

    private final int[] numbers;
    private int index;

    public EvenIt(final int[] num) {
        this.numbers = num;
    }

    @Override
    public boolean hasNext() {
        boolean rst = false;
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                index = i;
                rst = true;
                break;
            }
        }
        return rst;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[index++];
    }
}