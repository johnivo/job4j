package ru.job4j.io.finder;

import com.google.common.base.Joiner;
import org.apache.commons.cli.*;

/**
 * Вспомогательный класс, обрабатывающий параметры-настрйки.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.06.2019
 */
public class Args {

    /**
     * Имя директории, в которой осуществляется поиск.
     */
    private String directory;

    /**
     * Имя файла целиком, маска, регулярное выражение.
     */
    private String name;

    /**
     * Вариант поиска по маске.
     */
    private Boolean mask;

    /**
     * Вариант поиска по полному совпадению имени.
     */
    private Boolean fullMatch;

    /**
     * Вариант поиска по регулярному выражению.
     */
    private Boolean regEx;

    /**
     * Имя лога.
     */
    private String output;

    /**
     * Задаваемые параметры.
     */
    private String[] args;

    /**
     * Разделитель строк.
     */
    private static final String LN = System.lineSeparator();


    /**
     * Конструктор.
     *
     * @param args задаваемые параметры
     *             -d - directory - каталог, в котором осуществляется поиск
     *             -n - name - имя файла, маска, либо регулярное выражение
     *             -m - match - поиск по маске
     *             -f - fullMatch - полное совпадение имени
     *             -r - regEx - регулярное выражение
     *             -o - output - результат записывается в файл
     */
    public Args(String[] args) {
        this.args = args;
        if (args.length == 0) {
            help();
        }
        parser();
    }

    public String directory() {
        return this.directory;
    }

    public String name() {
        return this.name;
    }

    public Boolean mask() {
        return this.mask;
    }

    public Boolean fullMatch() {
        return this.fullMatch;
    }

    public Boolean regEx() {
        return this.fullMatch;
    }

    public String output() {
        return this.output;
    }

    /**
     * Парсер командной строки.
     */
    private void parser() {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        options.addOption("d", "directory", true, "directory in which to search");
        options.addOption("n", "name", true, "name to search");
        options.addOption("m", "mask", false, "mask search");
        options.addOption("f", "fullMatch", false, "full name match");
        options.addOption("r", "regEx", false, "regular expression");
        options.addOption("o", "output", true, "output log file");
        try {
            CommandLine commandLine = parser.parse(options, this.args);

            if (commandLine.hasOption("d")) {
                System.out.print("Option d is present. The value is: ");
                System.out.println(commandLine.getOptionValue("d"));
                String arguments = commandLine.getOptionValue("d");
                this.directory = arguments;
            }

            if (commandLine.hasOption("n")) {
                System.out.print("Option n is present. The value is: ");
                System.out.println(commandLine.getOptionValue("n"));
                String arguments = commandLine.getOptionValue("n");
                this.name = arguments;
            }

            if (commandLine.hasOption("m")) {
                System.out.print("Option m is present. The value is: ");
                System.out.println(commandLine.getOptionValue("m"));
                String arguments = commandLine.getOptionValue("m");
                this.mask = true;
                this.fullMatch = false;
                this.regEx = false;
                System.out.println("We try with mask search");
            }

            if (commandLine.hasOption("f")) {
                System.out.print("Option f is present. The value is: ");
                System.out.println(commandLine.getOptionValue("f"));
                String arguments = commandLine.getOptionValue("f");
                this.mask = false;
                this.fullMatch = true;
                this.regEx = false;
                System.out.println("We try with full name match");
            }

            if (commandLine.hasOption("r")) {
                System.out.print("Option r is present. The value is: ");
                System.out.println(commandLine.getOptionValue("r"));
                String arguments = commandLine.getOptionValue("r");
                this.mask = false;
                this.fullMatch = false;
                this.regEx = true;
                System.out.println("We try with regular expression");
            }

            if (commandLine.hasOption("o")) {
                System.out.print("Option o is present. The value is: ");
                System.out.println(commandLine.getOptionValue("o"));
                String arguments = commandLine.getOptionValue("o");
                this.output = arguments;
            }

            String[] remainder = commandLine.getArgs();
            System.out.print("Remaining arguments: ");
            for (String argument : remainder) {
                System.out.print(argument);
                System.out.print(" ");
            }
            System.out.println();

        } catch (ParseException pe) {
            System.out.print("Parse error: ");
            System.out.println(pe.getMessage());
        }
    }

    /**
     * Подсказка.
     */
    private void help() {
        System.out.println(
                Joiner.on(LN).join(
                        "Отсутствуют ключи:",
                        "-d - директория, в которой начинать поиск.",
                        "-n - имя файла, маска, либо регулярное выражение.",
                        "-m - искать по маске, либо -f - полное совпадение имени, либо -r регулярное выражение.",
                        "-o - результат записать в файл.",
                        "Пример использования: java -jar find.jar -d c:/ -n *.txt -m -o log.txt"
                )
        );
    }

}
