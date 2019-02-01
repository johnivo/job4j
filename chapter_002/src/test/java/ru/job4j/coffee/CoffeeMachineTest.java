package ru.job4j.coffee;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 01.02.2019
 */
public class CoffeeMachineTest {

    @Test
    public void whenValue50ThenChanges15() {
        int value = 50;
        int price = 35;
        CoffeeMachine coffee = new CoffeeMachine(value, price);
        int[] result = coffee.changes(value, price);
        int[] expected = {10, 5};
        assertThat(result, is(expected));
    }

    @Test
    public void whenValue50ThenChanges38() {
        int value = 50;
        int price = 12;
        CoffeeMachine coffee = new CoffeeMachine(value, price);
        int[] result = coffee.changes(value, price);
        int[] expected = {10, 10, 10, 5, 2, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenValue100ThenChanges36() {
        int value = 100;
        int price = 64;
        CoffeeMachine coffee = new CoffeeMachine(value, price);
        int[] result = coffee.changes(value, price);
        int[] expected = {10, 10, 10, 5, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenValueEqualsPriceThenNoChange() {
        int value = 50;
        int price = 50;
        CoffeeMachine coffee = new CoffeeMachine(value, price);
        int[] result = coffee.changes(value, price);
        int[] expected = {};
        assertThat(result, is(expected));
    }

    @Test(expected = ArithmeticException.class)
    public void whenValueIsLessThanPriceThenException() {
        int value = 50;
        int price = 54;
        CoffeeMachine coffee = new CoffeeMachine(value, price);
        int[] result = coffee.changes(value, price);
    }
}