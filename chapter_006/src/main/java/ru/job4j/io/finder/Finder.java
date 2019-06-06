package ru.job4j.io.finder;

import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Осуществляет поиск файлов в каталоге и подкаталогах.
 * Критерий поиска: имя файла может задаваться целиком, по маске, либо по регулярному выражению.
 * Результат записывается в указанный файл лога.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.06.2019
 */
public class Finder {

    /**
     * Список путей к файлам.
     */
    private List<Path> pathsList = new LinkedList<>();

    /**
     * Поле для хранения класса параметров.
     */
    private Args args;

    /**
     * Конструктор.
     *
     * @param args задаваемые параметры
     */
    public Finder(Args args) {
        this.args = args;
    }

    /**
     * Осуществляет поиск файлов в каталоге по маске, пополному совпадению имени, либо порегулярному выражению.
     * Результат записывает в файл лога.
     */
    public void search() throws IOException {
        if (this.args.mask()) {
            this.pathsList = findByMask(this.args.name());
        } else if (args.fullMatch()) {
            this.pathsList = findByFullName(this.args.name());
        } else if (args.regEx() != null) {
            this.pathsList = findByRegExp(this.args.name());
        }
        writeLog(this.args.output());
    }

    /**
     * Осуществляет поиск файлов в каталоге по заданной маске.
     *
     * @param mask маска.
     */
    private List<Path> findByMask(String mask) throws IOException {
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.directory()))
        ) {
            return filesStream
                    .map(Path::toFile)
                    .filter(
                            file -> file.getName().contains(mask.substring(1))
                    )
                    .map(File::toPath)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Осуществляет поиск файлов в каталоге по заданному имени.
     *
     * @param fullName полное имя файла.
     */
    private List<Path> findByFullName(String fullName) throws IOException {
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.directory()))
        ) {
            return filesStream
                    .filter(
                            file -> file.getFileName().toString().contains(fullName)
                    )
                    .collect(Collectors.toList());
        }
    }

    /**
     * Осуществляет поиск файлов в каталоге по заданному регулярному выражению.
     *
     * @param regEx регулярное выражение.
     */
    public List<Path> findByRegExp(String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        List<Path> result = new LinkedList<>();
        try {
            try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.directory()))
            ) {
                filesStream
                        .filter(
                                path -> pattern.matcher(path.getFileName().toString()).find()
                        )
                        .forEach(result::add);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * Записывает результат поиска в текстовый лог.
     *
     * @param target имя лога.
     */
    private void writeLog(String target) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)))
                //BufferedWriter writer = new BufferedWriter(new FileWriter(target))
        ) {
            for (Path line : pathsList) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
