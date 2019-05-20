package ru.job4j.io.zip;

import org.apache.commons.cli.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архиватор проекта.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.05.2019
 */
public class Zip {

    /**
     * Архивирует проект, сохраняя структуру каталогов.
     *
     * @param args входящие параметры.
     */
    public void pack(Args args) throws IOException {
        List<File> list = seekBy(args.directory(), args.exclude());
        try (ZipOutputStream zos =
                     new ZipOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(args.output(), false)
                             )
                     )
        ) {
            for (File file : list) {
                zos.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(args.directory().length() + 1)));
                try (BufferedInputStream out =
                             new BufferedInputStream(
                                     new FileInputStream(file)
                             )
                ) {
                    zos.write(out.readAllBytes());
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Сканирует содержимое заданного каталога, в т.ч. все вложенные папки и
     * возвращает список файлов за исключением списка игнора.
     *
     * @param parent путь до каталога, в котором нужно осуществлять поиск.
     * @param exts список игнорируемых расширений файлов.
     * @return list список файлов.
     */
    public List<File> seekBy(String parent, List<String> exts) {
        List<File> rsl = new ArrayList();
        File file = new File(parent);
        Queue<File> data = new LinkedList<>();
        data.offer(file);
        while (!data.isEmpty()) {
            File el = data.poll();
            if (!el.isDirectory()) {
                String name = el.getName();
                if (name.contains(".")) {
                    if (!exts.contains(name.substring(name.indexOf(".")))) {
                        rsl.add(el);
                    }
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
