package ru.job4j.interactcalc;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.07.2019
 */
public class StartCalc {

    public static void main(String[] args) {

        Input input = new ConsoleInput();
        Calculator calc = new Calculator();
        InteractCalc interactCalc = new InteractCalc(input, calc, System.out::println);

        interactCalc.action();

    }

}
