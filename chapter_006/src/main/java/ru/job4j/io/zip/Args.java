package ru.job4j.io.zip;

import org.apache.commons.cli.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.05.2019
 */
public class Args {

    /**
     * Имя корня проекта.
     */
    private String directory;

    /**
     * Список расширений файлов, которые игнорируются.
     */
    private List<String> exclude = new ArrayList<>();

    /**
     * Имя файла архива.
     */
    private String output;

    /**
     * Задаваемые параметры.
     */
    private String[] args;


    /**
     * Конструктор.
     *
     * @param args задаваемые параметры
     * -d - directory - которую мы ходим архивировать
     * -e - exclude - исключить файлы *.xml
     * -o - output - во что мы архивируем
     */
    public Args(String[] args) throws ParseException {
        this.args = args;
        parser();
    }

    public String directory() {
        return this.directory;
    }

    public List<String> exclude() {
        return this.exclude;
    }

    public String output() {
        return this.output;
    }

    /**
     * Парсер командной строки.
     */
    private void parser() {
        // в options добавляем список опций
        // "-d" короткая форма опции (однобуквенная опция), "directory" длинная форма опции,
        // true флаг обозночающий наличие параметров и "directory" текстовое пояснение данной опции.
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        Option excludeOp = new Option("e", "exclude", true, "exclude extensions");
        excludeOp.setArgs(Option.UNLIMITED_VALUES);
        excludeOp.setValueSeparator(' ');
        options.addOption(excludeOp);
        options.addOption("d", "directory", true, "directory that must be archived");
        options.addOption("o", "output", true, "output archive file");

        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("d")) {
                System.out.print("Option d is present. The value is: ");
                System.out.println(commandLine.getOptionValue("d"));
                String arguments = commandLine.getOptionValue("d");
                this.directory = arguments;
            }

            if (commandLine.hasOption("e")) {
                System.out.print("Option e is present. The value is: ");
                System.out.println(List.of(commandLine.getOptionValues("e")));
                String[] arguments = commandLine.getOptionValues("e");
                for (String s : arguments) {
                    this.exclude.add(s);
                }
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
}
