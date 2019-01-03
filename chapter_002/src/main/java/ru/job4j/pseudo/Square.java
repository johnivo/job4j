package ru.job4j.pseudo;

/**
 * Square.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 03.01.2019
 */
public class Square implements Shape {

    /**
     * Метод формирует строку из символов в виде квадрата.
     * @return строка в виде квадрата.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++\n");
        pic.append("+  +\n");
        pic.append("+  +\n");
        pic.append("++++\n");
        return pic.toString();
    }
}
