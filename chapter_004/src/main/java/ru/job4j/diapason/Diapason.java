package ru.job4j.diapason;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;

/**
 * Подсчет функции в диапазоне.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 28.02.2018
 */
public class Diapason {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> range = new ArrayList<>();
        for (int index = start; index != end; index++) {
            range.add(func.apply((double) index));
        }
        return range;
    }
}