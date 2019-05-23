package ru.job4j.io.chat;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Консольный чат.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.05.2019
 */
public class ConsoleChat {

    /**
     * Слова-команды.
     */
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String EXIT = "закончить";

    /**
     * Поле для хранения класса ответов.
     */
    private Answer answers;

    /**
     * Поток ввода данных.
     */
    private InputStream in;

    /**
     * Конструктор.
     *
     * @param path путь к файлу с ответами.
     * @param input поток ввода данных.
     */
    public ConsoleChat(String path, InputStream input) {
        this.answers = new Answer(path);
        this.in = input;
    }

    /**
     * Осуществляет взаимодействие пользователя с чат-ботом. Слова-команды стоп/продолжить/закончить.
     * Пользователь вводит любую слово-фразу, программа отвечает случайной фразой из текстового файла.
     * Бот замолкает, если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
     * Если пользователь вводит слово «продолжить», программа снова начинает отвечать.
     * При вводе слова «закончить» программа прекращает работу.
     * Запись диалога, включая слова-команды, записывается в текстовый лог.
     *
     * @param target имя лога.
     */
    public void launch(String target) {
        List<String> log = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.in));
             //BufferedWriter writer = new BufferedWriter(new FileWriter(target))
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)))
        ) {
            writer.write(new Date().toString());
            writer.newLine();
            writer.flush();
            String userMessage;
            String botMessage;
            boolean stop = false;
            do {
                userMessage = reader.readLine();
                log.add("user: " + userMessage);
                if (STOP.equals(userMessage)) {
                    stop = true;
                } else if (CONTINUE.equals(userMessage)) {
                    stop = false;
                }
                if (!stop && !EXIT.equals(userMessage)) {
                    botMessage = this.answers.answer();
                    log.add("bot: " + botMessage);
                    System.out.println("bot: " + botMessage);
                }
            } while (!EXIT.equals(userMessage));
            for (String line : log) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Осуществляет взаимодействие пользователя с чат-ботом.
     * Реализация без использования конструкции try-with-resources.
     *
     * @param target имя лога.
     */
    public void launch2(String target) throws IOException {
        List<String> log = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.in));
        BufferedWriter writer = new BufferedWriter(new FileWriter(target));
        String userMessage;
        String botMessage;
        boolean stop = false;
        do {
            userMessage = reader.readLine();
            log.add("user: " + userMessage);
            writer.write("user: " + userMessage);
            writer.newLine();
            writer.flush();
            if (STOP.equals(userMessage)) {
                stop = true;
            } else if (CONTINUE.equals(userMessage)) {
                stop = false;
            }
            if (!stop && !EXIT.equals(userMessage)) {
                botMessage = this.answers.answer();
                log.add("bot: " + botMessage);
                writer.write("bot: " + botMessage);
                writer.newLine();
                writer.flush();
                System.out.println("bot: " + botMessage);
            }
        } while (!EXIT.equals(userMessage));
        reader.close();
        writer.close();
    }
}
