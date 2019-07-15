package ru.job4j.loop;

/**
 * Необходимо посчитать количество простых чисел от 1 до x.
 * Простое число, натуральное (целое положительное) число которое делится только на себя и на единицу.
 * Напомним, что 1 не является простым числом.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.07.2019
 */
public class PrimeNumber {

    public int calc(int finish) {
        int count = 0;
        int temp = 0;
        for (int i = 2; i <= finish; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    temp++;
                }
            }
            if (temp == 2) {
                count++;
            }
            temp = 0;
        }
        return count;
    }

}
