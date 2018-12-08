package ru.job4j.converter;

/**
 * Converter - currency converter class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 08.12.2018
 */
public class Converter {

    /**
     * euro rate.
     */
    private final int euroRate = 76;

    /**
     * dollar rate.
     */
    private final int dollarRate = 67;

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / euroRate;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары.
     */
    public int rubleToDollar(int value) {
        return value / dollarRate;
    }

    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return Рубли.
     */
    public int euroToRuble(int value) {
        return value * euroRate;
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return Рубли.
     */
    public int dollarToRuble(int value) {
        return value * dollarRate;
    }
}