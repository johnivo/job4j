package ru.job4j.pseudo;

/**
 * Triangle.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 03.01.2019
 */
public class Triangle implements Shape {

    /**
     * Метод формирует строку из символов в виде треугольника.
     * @return строка в виде треугольника.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("  +  \n");
        pic.append(" +++ \n");
        pic.append("+++++\n");
        return pic.toString();
    }

}
