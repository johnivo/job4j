package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TriangleTest - test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version 1
 *@since 10.12.2018
 */
public class TriangleTest {


    /**
     * Triangle test.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // Создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(1, 5);
        Point c = new Point(7, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = 16.5D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Triangle test.
     */
    @Test
    public void triangleExist() {
        // Создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(1, 5);
        Point c = new Point(7, 2);
        // Создаем объект класса треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Находим длины сторон треугольника.
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        // Проверяем существует ли треугольник с такими длинами сторон.
        assertThat(triangle.exist(ab, ac, bc), is(true));
    }
}