package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Проверяет байтовый поток на наличие пар ключ-значение.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 07.05.2019
 */
public class Config {

    /**
     * Файл с данными.
     * Например:
     * ## PostgreSQL
     *
     * hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     * hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio
     * hibernate.connection.driver_class=org.postgresql.Driver
     * hibernate.connection.username=postgres
     * hibernate.connection.password=password
     *
     */
    private final String path;

    /**
     * Хранилище для пар ключ-значение.
     */
    private final Map<String, String> values = new LinkedHashMap<String, String>();

    /**
     * Маркер для поиска пары.
     */
    private static final String MARKER = "=";


    /**
     * Конструктор.
     *
     * @param path имя файла.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Загружает из файла пары ключ-значение в Map values.
     *
     * @return values карту пар ключ-значение.
     */
    public Config load() {
        this.values.clear();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(
                    line -> {
                        if (line.contains(MARKER)) {
                            int pos = line.indexOf(MARKER);
                            values.put(line.substring(0, pos), line.substring(pos + 1));
                        } else {
                            values.put(line, "");
                        }
                    }
            );
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return this;
    }

    /**
     * Возвращает из Map values значение по заданному ключу.
     *
     * @param key ключ.
     * @return value значение.
     */
    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return out.toString();
    }

    /**
     * Вспомогательный метод, получает размер заполненной части карты.
     *
     * @return размер карты.
     */
    public int size() {
        return this.values.size();
    }
}
