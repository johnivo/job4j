package ru.job4j.matrix;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 07.03.2019
 */
public class MatrixToListTest {

    @Test
    public void whenMatrixConvertToList() {
        Integer[][] matrix = {{1, 2}, {3, 4}};
        MatrixToList list = new MatrixToList();
        int result = list.matrixToList(matrix).size();
        assertThat(result, is(4));
    }

    @Test
    public void whenListsConvertToList() {
        List<List<Integer>> matrix = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        );
        MatrixToList list = new MatrixToList();
        int result = list.listsToList(matrix).size();
        assertThat(result, is(4));
    }
}
