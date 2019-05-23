package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Вспомогательный класс-поставщик ответов из текстового файла с данными.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.05.2019
 */
public class Answer {

    /**
     * Файл с ответами.
     */
    private File source;

    /**
     * Хранилище всех ответов.
     */
    private List<String> lexicon;

    /**
     * Конструктор.
     *
     * @param path путь к файлу с ответами.
     */
    public Answer(String path) {
        this.source = new File(path);
        this.lexicon = answers();
    }

    /**
     * Считывает данные из файла с ответами и добавляет в список-лексикон.
     *
     * @return список возможных ответов.
     */
    private List<String> answers() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.source))) {
            reader.lines().forEach(list::add);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    /**
     * Возвращает случайный ответ из списка-лексикона.
     *
     * @return рандомная строка.
     */
    public String answer() {
        return lexicon.get(new Random().nextInt(lexicon.size()));
    }

    /**
     * Возвращает список всех ответов.
     *
     * @return список ответов.
     */
    public List<String> answersList() {
        return this.lexicon;
    }

}
