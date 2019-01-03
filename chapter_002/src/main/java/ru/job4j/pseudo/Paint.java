package ru.job4j.pseudo;

/**
 * Paint.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 03.01.2019
 */
public class Paint {

    /**
     * Метод выводит на консоль фигуры.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        paint.draw(new Square());
    }
}
