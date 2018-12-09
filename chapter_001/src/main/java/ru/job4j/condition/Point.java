package ru.job4j.condition;

/**
 * Расчет расстояния между двумя точками на плоскости.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 09.12.2018
 */
public class Point {

    /**
     * координата по оси x.
     */
    private final int x;
    /**
     * координата по оси y.
     */
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Расчет расстояния между точками A и B.
     * @param that - точка B.
     * @return - дистанция.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2,5);
        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками A и B : " + result);
    }
}