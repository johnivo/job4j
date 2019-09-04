package ru.job4j.gc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Создать структуру данных типа кеш. Кеш должен быть абстрактный. То есть необходимо, что бы можно было задать
 * ключ получения объекта кеша и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 *
 * Создать программу эмулирующее поведение данного кеша. Программа должна считывать текстовые файлы из системы и
 * выдавать текст при запросе имени файла. Если в кеше файла нет. Кеш должен загрузить себе данные.
 * По умолчанию в кеше нет ни одного файла. Текстовые файл должны лежать в одной директории.
 *
 * Пример. Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 04.09.2019
 */
public class Cache implements ICache {

    private static final Logger LOG = LogManager.getLogger(Cache.class.getName());

    /**
     * Поле содержит путь к каталогу с файлами.
     */
    private final String path;

    /**
     * Поле для кэша, содержит пары: имя файла=содержимое файла.
     */
    HashMap<String, SoftReference<String>> cache = new HashMap();

    //для примера на слабых ссылках
    HashMap<String, WeakReference<String>> cache2 = new HashMap();

    /**
     * Конструктор.
     *
     * @param path путь к каталогу.
     */
    public Cache(final String path) {
        this.path = path;
    }

    /**
     * Возвращает текст при запросе имени файла из кеша
     * Если в кеше файла нет, то сначала он загружает себе данные
     *
     * @param key имя файла.
     * @return возвращает содержимое текстового файла.
     */
    @Override
    public String getData(String key) {

        if (cache.get(key) == null) {

            try (BufferedReader br = new BufferedReader(new FileReader(new File(path, key)))) {
                StringJoiner content = new StringJoiner(System.lineSeparator());
                br.lines().forEach(
                        line -> content.add(line)
                );
                cache.put(key, new SoftReference<>(content.toString()));

            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return cache.get(key).get();
    }

    //для примера на слабых ссылках
    public String getData2(String key) {
        if (cache2.get(key) == null) {
            try (BufferedReader br = new BufferedReader(new FileReader(new File(path, key)))) {
                StringJoiner content = new StringJoiner(System.lineSeparator());
                br.lines().forEach(
                        line -> content.add(line)
                );
                cache2.put(key, new WeakReference<>(content.toString()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return cache2.get(key).get();
    }

    public static void main(String[] args) {

        String path = new File("chapter_009/src/main/resources").getAbsolutePath();
        System.out.println(path);
        Cache cache = new Cache(path);
        String addresses = cache.getData("Address.txt");
        System.out.println(addresses);
        System.out.println();

        SoftReference<String> softRef = new SoftReference<>(new String("SoftRef"));
        WeakReference<String> weakRef = new WeakReference<>(new String("WeakRef"));

        cache.cache.put("soft", softRef);
        cache.cache2.put("weak", weakRef);

        System.gc();

        String sr = cache.getData("soft");
        System.out.println("when soft ref = " + sr);

        String wr = cache.getData2("weak");
        System.out.println("when weak ref = " + wr);
    }

}
