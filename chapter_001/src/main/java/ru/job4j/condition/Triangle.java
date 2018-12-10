package ru.job4j.condition;

/**
 * Вычисление площади треугольника.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 10.12.2018
 */
public class Triangle {
    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Вычисление полупериметра по длинам сторон.
     * @param ab расстояние между точками a b
     * @param ac расстояние между точками a c
     * @param bc расстояние между точками b c
     * @return Полупериметр.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + bc + ac)/2;
    }

    /**
     * Вычисление площади треугольника.
     * @return Вернуть прощадь, если треугольник существует или -1, если треугольника нет.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p*(p-ab)*(p-ac)*(p-bc));
        }
        return rsl;
    }

    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     * @param ab Длина от точки a b.
     * @param ac Длина от точки a c.
     * @param bc Длина от точки b c.
     * @return истина или ложь.
     */
    public boolean exist(double ab, double ac, double bc) {
        return ((ab + bc) > ac && (ab + ac) > bc && (bc + ac) > ab);
    }
}