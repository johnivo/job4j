package ru.job4j.io;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Сканер файловой системы
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.05.2019
 */
public class Search {

    /**
     * Сканирует содержимое заданного каталога, в т.ч. все вложенные папки и
     * возвращает список всех файлов с конкретным расширением.
     *
     * @param parent путь до каталога, с которого нужно осуществлять поиск.
     * @param exts   расширения файлов, которые мы ходим получить.
     * @return list список файлов.
     */
    public List<File> files(String parent, List<String> exts) {
        List<File> rsl = new ArrayList();
        File file = new File(parent);
        Queue<File> data = new LinkedList<>();
        data.offer(file);
        while (!data.isEmpty()) {
            File el = data.poll();
            if (!el.isDirectory()) {
                String name = el.getName();
                String extension = name.substring(name.indexOf("."));
                if (exts.contains(extension)) {
                    rsl.add(el);
                }
            } else {
                for (File child : el.listFiles()) {
                    data.offer(child);
                }
            }
        }
        return rsl;
    }
}