package ru.job4j.array;

/**
 * Слияние двух отсортированных массивов.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 20.12.2018
 */
public class ArrayMerge {

    /**
     * Метод объединяет два отсортированных массива и возвращает общий отсортированный массив.
     * @param arrayFirst первый отсортированнвй массив.
     * @param arraySecond второй отсортированный массив.
     * @return отсортированный результирующий массив.
     */
    public int[] merging(int[] arrayFirst, int[] arraySecond) {
        int[] resultArray = new int[arrayFirst.length + arraySecond.length];
        int count = resultArray.length;
        int j = 0, k = 0;
        for (int i = 0; i < count; i++) {
            if (j < arrayFirst.length && k < arraySecond.length) {
                if (arraySecond[k] < arrayFirst[j]) {
                    resultArray[i] = arraySecond[k];
                    k++;
                } else {
                    resultArray[i] = arrayFirst[j];
                    j++;
                }
            } else if (k < arraySecond.length) {
                resultArray[i] = arraySecond[k];
                k++;
            } else {
                resultArray[i] = arrayFirst[j];
                j++;
            }
        }
        return resultArray;
    }
}
