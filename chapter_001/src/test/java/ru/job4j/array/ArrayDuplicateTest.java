package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * ArrayDuplicateTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 15.12.2018
 */
public class ArrayDuplicateTest {

    /**
     * в тесте сохраняется порядок строк в массиве после удаления дубликатов.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input = {"mike", "tanya", "john", "mike", "bob", "mike"};
        String[] result = duplicate.remove(input);

        for (String x : result) {
            System.out.print(x);
            System.out.print(" ");
        }
        System.out.println();

        String[] except = {"mike", "tanya", "john", "bob"};
        assertThat(result, is(except));
    }

    /**
     * в тесте на важен порядок строк в массиве после удаления дубликатов.
     */
    @Test
    public void whenArrayHasAllDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input = {"mike", "mike", "tanya", "tanya", "john", "john"};
        String[] result = duplicate.remove(input);

        for (String x : result) {
            System.out.print(x);
            System.out.print(" ");
        }
        System.out.println();

        String[] except = {"mike", "tanya", "john"};
        assertThat(result, arrayContainingInAnyOrder(except));
    }
}