package ru.job4j.stream;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.stream.ArrayProcessing.processing2;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 27.03.2019
 */
public class ArrayProcessingTest {

    @Test
    public void whenUseProcessingOfArray() {
        var a = new ArrayProcessing();
        int[] arr = {5, 2, 1, 10, 2};
        int result = a.processing(arr);
        assertThat(result, is(108));
    }

    @Test
    public void whenUseSecondProcessingOfArray() {
        int[] arr = {5, 0, 2, 4};
        int result = processing2(arr);
        assertThat(result, is(20));
    }
}