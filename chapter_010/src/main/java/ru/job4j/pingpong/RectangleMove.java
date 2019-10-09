package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.10.2019
 */
public class RectangleMove implements Runnable {

    /**
     * Поле содержит объект класса прямоугольник.
     */
    private final Rectangle rect;

    /**
     * Поле содержит размер стороны X игрового поля.
     */
    private final int limitX;

    /**
     * Поле содержит размер стороны Y игрового поля.
     */
    private final int limitY;

    /**
     * Поле содержит направление движения по оси X.
     */
    private int xDirection;

    /**
     * Поле содержит направление движения по оси Y.
     */
    private int yDirection;

    /**
     * Конструктор, инициализирует объект заданным прямоугольником и размерами игрового поля.
     */
    public RectangleMove(Rectangle rect, int x, int y) {
        this.rect = rect;
        this.limitX = x;
        this.limitY = y;
    }

    /**
     * Запускает движение прямоугольника внутри игрового полю.
     * При соприкосновении с границей поля прямоугольник отскакивает внутрь игрового поля.
     *
     * Начальное направление движения задается случайным образом.
     * Перед каждым шагом идет проверка, достиг ли прямоугольник границ поля.
     * После изменения координат поток исполнения останавливается на 100 мс.
     */
    @Override
    public void run() {
        this.xDirection = randomDirection();
        this.yDirection = randomDirection();
        while (true) {
            this.checkLimit();

            this.rect.setX(this.rect.getX() + this.xDirection);
            this.rect.setY(this.rect.getY() + this.yDirection);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Проверяет, достиг ли прямоугольник границ поля.
     * Если достиг - напрвление движения меняется.
     * По координате, равной соответствующей границе, в противоположную сторону.
     * По второй координате - случайным образом.
     */
    public void checkLimit() {
        if (this.rect.getX() + 10 == this.limitX) {
            this.xDirection = -1;
            this.yDirection = this.randomDirection();
        }
        if (this.rect.getX() == 0) {
            this.xDirection = 1;
            this.yDirection = this.randomDirection();
        }
        if (this.rect.getY() + 10 == this.limitY) {
            this.yDirection = -1;
            this.xDirection = this.randomDirection();
        }
        if (this.rect.getY() == 0) {
            this.yDirection = 1;
            this.xDirection = this.randomDirection();
        }
    }

    /**
     * @return Возвращает направление движения случайным выбором из -1, 0, 1.
     */
    public int randomDirection() {
        return (int) (Math.random() * 3) - 1;
    }

}
