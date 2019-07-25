package ru.job4j.interactcalc;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 26.07.2019
 */
public class EngineerCalculator extends Calculator {

    /**
     * Конструктор.со списком операций, загружаемых по-умолчанию.
     */
    public EngineerCalculator() {
        super();
        this.loadOperation(new Sine());
        this.loadOperation(new Cosine());
    }

    @Override
    public String menu() {
        final String LN = System.lineSeparator();
        String menu = Joiner.on(LN).join(
                "Trigonometrical operations: sin, cos",
                super.menu()
        );
        return menu;
    }

    /**
     * Синус.
     */
    public static final class Sine implements Action {

        @Override
        public String operation() {
            return "sin";
        }

        @Override
        public Double arithmetical(Double first, Double second) {
            return Math.sin(first);
        }

    }

    /**
     * Косинус.
     */
    public static final class Cosine implements Action {

        @Override
        public String operation() {
            return "cos";
        }

        @Override
        public Double arithmetical(Double first, Double second) {
            return Math.cos(first);
        }

    }

}
