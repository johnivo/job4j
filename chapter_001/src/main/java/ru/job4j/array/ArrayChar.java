package ru.job4j.array;

/**
 * Слово начинается с ....
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 13.12.2018
 */
public class ArrayChar {

    /**
     * Обертка над строкой.
     */
    private final char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Метод проверяет, что слово начинается с префикса.
     * @param prefix префикс.
     * @return true если начинается, иначе false.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < value.length; i++) {
            if (data[i] != value[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}