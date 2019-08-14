package ru.job4j.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Преобразует строку в заголовок со ссылкой для файлов .md
 * Формирует каркас меню для топика.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.08.2019
 */
public class Converter {

    private static final String NAME = "SOLID";

    private Integer num = 1;

    class QA {
        String question;
        String answer;
    }

    public QA getMenuItem(String str) {
        str = str.trim();
        str = num + ". " + str;
        num++;

        String question = str;
        question = "[" + str + "]";

        str = str.replaceAll(" ", "-");

        QA qa = new QA();

        qa.question = question + "(#" + str + ")";
        qa.answer = "## " + str.replaceAll("-", " ");

        return qa;
    }

    public static void main(String[] args) {

        String str;
        Converter converter = new Converter();
        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            str = input.nextLine();
            lines.add(str);
            if (str.isEmpty()) {
                break;
            }
        }
        System.out.println();

        List<QA> qaList = new ArrayList<>();
        lines.forEach(s->qaList.add(converter.getMenuItem(s)));

        qaList.forEach(qa -> {
            System.out.println(qa.question);
            System.out.println();
        });

        qaList.forEach(qa -> {
            System.out.println(qa.answer);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("[к оглавлению](#" + NAME + ")");
            System.out.println();
        });
    }

}
