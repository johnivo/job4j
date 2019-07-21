package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Class for calculating arithmetic operations.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.07.2019
 */
public class Calculator {

    /**
     * Лист для хранения результатов вычислений.
     */
    private List<Double> storage = new ArrayList<>();

    /**
     * Лист для хранения вторых аргументов использовавшихся в выражениях.
     */
    private List<Double> secondStorage = new ArrayList<>();

    /**
     * результат вычисления.
     */
    private double result;

    /**
     * Хранилище последовательности вычислений.
     */
    private Map<Double, BiFunction> last = new LinkedHashMap<>();

    /**
     * Возвращает список результатов всех вычислений.
     * @return storage список результатов.
     */
    public List<Double> getStorage() {
        return this.storage;
    }

    /**
     * Возвращает список вторых аргументов в выражениях.
     * @return secondStorage список 2х аргументов.
     */
    public List<Double> getSecondStorage() {
        return this.secondStorage;
    }

    /**
     * Возвращает последовательность вычислений.
     * @return last ассоциативный массив вычислений.
     */
    public Map<Double, BiFunction> getLastAction() {
        return this.last;
    }

    /**
     * Выполняет вычисление операции над двумя аргументами и принимает результат.
     * @param one первый аргумент.
     * @param two второй аргумент.
     * @param op функция.
     * @param media потребитель.
     */
    public void calculation(Double one, Double two,
                     BiFunction<Double, Double, Double> op,
                     Consumer<Double> media) {
        result = op.apply(one, two);

        this.storage.add(result);
        this.secondStorage.add(two);

        this.last.put(two, op);

        media.accept(result);
    }

}
