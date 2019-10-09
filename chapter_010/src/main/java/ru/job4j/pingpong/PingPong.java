package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.10.2019
 */
public class PingPong extends Application {

    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Запускает игру.
     *
     * Устанавливаем размеры игрового поля.
     * Создаем группу.
     * Создаем объект класса прямоугольник размером 10*10 и координатами его левого верхнего угла, добавляем его в группу.
     * Создаем поток с переданным объектом RectangleMove, описывающим траекторию движения rect, и активируем его.
     * Устанавливаем для сцены типа Stage заданные константные размеры, название и выводим на монитор.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);

        new Thread(new RectangleMove(rect, limitX, limitY)).start();

        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(
                event -> System.exit(0)
        );
    }

}
