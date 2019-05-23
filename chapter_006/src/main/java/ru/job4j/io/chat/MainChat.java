package ru.job4j.io.chat;

import java.io.IOException;

/**
 * Запуск консольного чата.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.05.2019
 */
public class MainChat {

    public static void main(String[] args) throws IOException {
        String path = "c:\\projects\\job4j\\chapter_006\\src\\main\\resources\\lexicon.txt";
        String target = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "chat.log";
        ConsoleChat chat = new ConsoleChat(path, System.in);
        chat.launch(target);
    }
}
