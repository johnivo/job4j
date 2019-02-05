package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Class ListCompare.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 05.02.2018
 */
public class ListCompare implements Comparator<String> {

    /**
     * Метод переопределяет компаратор для сравнения двух массивов символов.
     * Т.е. сравниваются элементы двух списков, находящихся на одних и тех же позициях (по одним и тем же индексом).
     * Сравнение осуществляется в лексикографическом порядке.
     *
     * @param left первый входящий список типа List<String>.
     * @param right второй входящий список типа List<String>.
     * @return result.
     */
    @Override
    public int compare(String left, String right) {
        int result = Integer.compare(left.length(), right.length());
        int counter = Math.min(left.length(), right.length());
        for (int i = 0; i < counter; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                result = Character.compare(left.charAt(i), right.charAt(i));
                break;
            }
        }
        return result;
    }
}