package ru.job4j.io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Бот - мудрый Оракл. Серверная сторона.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 27.05.2019
 */
public class Server {

    /**
     * Сокет.
     */
    private final Socket socket;

    /**
     * Номер порта.
     */
    private static final int PORT = 1111;

    /**
     * Конструктор.
     *
     * @param socket сокет.
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Реализация серверной части бота.
     * Сервер получает сообщение-команду от пользователя и отвечает на него.
     * Сервер может отправлять большие сообщения. В конце сообщения он отправляет пустую строку.
     * Оракл отвечает на приветствие Hello Oracle.
     * Если Ораклу сказали Exit, то приложение выключается.
     * На любую другую слово-фразу, сервер отвечает I don't understand you.
     */
    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))
        ) {
            String ask = null;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("Hello oracle".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!"Exit".equals(ask)) {
                    out.println("I don't understand you.");
                    out.println();
                }
            } while (!"Exit".equals(ask));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Запуск сервера.
     */
    public static void main(String[] args) {
        try (final Socket socket = new ServerSocket(PORT).accept()) {
            new Server(socket).start();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
