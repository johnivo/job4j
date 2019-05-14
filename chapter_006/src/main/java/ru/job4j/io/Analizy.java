package ru.job4j.io;

import java.io.*;

/**
 * Преобразование данных одного файла в другой на примере анализа файла регистрации событий сервера.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.05.2019
 */
public class Analizy {

    /**
     * Метка для поиска ошибки 400.
     */
    private static final String BR = "400";

    /**
     * Метка для поиска ошибки 500.
     */
    private static final String ISE = "500";

    /**
     * Считывает данные из файла регистрации событий сервера.
     * Находит диапазоны, когда сервер не работал и записывает в целевой файл.
     * Например:
     * 200 10:56:01
     * 500 10:57:01
     * 400 10:58:01
     * 200 10:59:01
     * 500 11:01:02
     * 200 11:02:02
     * тут два периода - 10:57:01 до 10:59:01 и 11:01:02 до 11:02:02
     * Начальное время - это время когда статус 400 или 500.
     * Конечное время это когда статус меняется с 400 или 500 на 200 300.
     * Формат файла для записи результатов следующий
     * 10:57:01;10:59:01;
     * 11:01:02;11:02:02;
     *
     * @param source имя файла лога.
     * @param target имя файла с результатами.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))
        ) {
            String line;
            boolean marker = false;
            while ((line = read.readLine()) != null) {
                if (line.contains(BR) || line.contains(ISE)) {
                    if (!marker) {
                        writer.write(line, 4, line.length() - 4);
                        writer.write(";");
                        marker = true;
                    }
                } else {
                    if (marker) {
                        writer.write(line, 4, line.length() - 4);
                        writer.write(";");
                        writer.write(System.lineSeparator());
                        marker = false;
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
