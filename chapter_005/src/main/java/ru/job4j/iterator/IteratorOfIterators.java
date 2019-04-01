package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 01.04.2019
 */
public class IteratorOfIterators {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> genIt) {

        return new Iterator<Integer>() {

            /**
             * текущий внутренний итератор
             */
             Iterator<Integer> innerIt = genIt.next();

            /**
             * Если в текущем внутреннем итераторе нет или отсутствуют элементы
             * переходим к следующему внутреннему итератору и проверяем наличие следующего элемента в нем
             *
             * @return true or false
             */
            @Override
            public boolean hasNext() {
                if (!innerIt.hasNext()) {
                    while (genIt.hasNext()) {
                        innerIt = genIt.next();
                        if (innerIt.hasNext()) {
                            break;
                        }
                    }
                }
                return innerIt.hasNext();
            }

            /**
             * Возвращает следующий элмент текущего внутреннего итератора
             * если элемент отсутствует, пробрасывается исключение
             *
             * @return следующий элемент
             * @throws NoSuchElementException следующий элемент отсутствует.
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return innerIt.next();
            }
        };
    }
}
