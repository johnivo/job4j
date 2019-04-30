package ru.job4j.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Алгоритм проверки являются ли два слова анаграммой.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.04.2019
 */
public class Anagram {

    /**
     * Проверяет являются ли два слова анаграммой.
     * Анаграмма - это механизм получения нового слово путем перестановки букв.
     *
     * @param second второе слово.
     * @return true если являются анаграммой.
     */
    public boolean check(String first, String second) {
        boolean result = false;
        if (first == null) {
            first = "";
        }
        if (second == null) {
            second = "";
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        char[] one = first.toCharArray();
        char[] two = second.toCharArray();
        if (first.length() == second.length()) {
            for (int i = 0; i < first.length(); i++) {
                map1.putIfAbsent(one[i], 1);
                map1.computeIfPresent(one[i], (k, v) -> v + 1);
                map2.putIfAbsent(two[i], 1);
                map2.computeIfPresent(two[i], (k, v) -> v + 1);
            }
            result = Objects.equals(map1, map2);
        }
        return result;
    }
}
