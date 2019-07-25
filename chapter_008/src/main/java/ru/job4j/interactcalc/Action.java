package ru.job4j.interactcalc;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 21.07.2019
 */
public interface Action {

    String operation();

    Double arithmetical(Double first, Double second);

    Boolean getArgs();

}
