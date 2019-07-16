package ru.job4j.array;

/**
 * Проверить, что слово заканчивается определенной последовательностью.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.07.2019
 */
public class EndsWith {

    /**
     * Проверяет, что слово заканчивается определенным набором символов.
     * @param post заданный набор символов.
     * @return true если слово заканчивается данным набором символов.
     */
    public boolean endsWith(String word, String post) {
        boolean result = true;
        char[] pst = post.toCharArray();
        char[] wrd = word.toCharArray();
        int x = wrd.length - pst.length;
        for (int i = 0; i < pst.length; i++) {
            if (wrd[i + x] != pst[i]) {
                result = false;
            }
        }
        return result;
    }
}
