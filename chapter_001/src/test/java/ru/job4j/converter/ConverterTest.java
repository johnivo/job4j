package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConverterTest - class test.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 08.12.2018
 */
public class ConverterTest {

    /**
     * rub/euro test.
     */
    @Test
    public void when76RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(76);
        assertThat(result, is(1));
    }

    /**
     * rub/dollar test.
     */
    @Test
    public void when67RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(67);
        assertThat(result, is(1));
    }

    /**
     * euro/rub test.
     */
    @Test
    public void when10EuroToRubleThen760() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(10);
        assertThat(result, is(760));
    }

    /**
     * dollar/rub test.
     */
    @Test
    public void when10DollarToRubleThen670() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(10);
        assertThat(result, is(670));
    }
}