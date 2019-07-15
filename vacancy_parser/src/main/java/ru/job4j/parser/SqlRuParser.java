package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс обеспечивает работу приложения в части парсинга страниц.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 02.07.2019
 */
public class SqlRuParser {

    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());

    /**
     * Возвращает список вакансий java.
     * Парсит постранично заданный топик с вакансиями java
     * Вакансии, содержащие в названии javascript и java script пропускаются
     * @return vacancies дата
     */
    public List<Vacancy> parse(String url, Date start) {
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc;
        int n = getPages(url);
        System.out.println("страниц для парсинга " + n);
        Date date = null;

        for (int i = 1; i <= n; i++) {
            try {
                doc = Jsoup.connect(url + i).get();

                Elements forumTable = doc.getElementsByClass("forumTable");
                Elements rows = forumTable.first().getElementsByTag("tbody").first().getElementsByTag("tr");
                for (int j = 1; j < rows.size(); j++) {
                    Element row = rows.get(j);
                    String name = row.child(1).getElementsByTag("a").first().text();

                    String created = row.child(5).text();

                    String link = row.getElementsByTag("a").attr("href");

                    String desc = null;
                    try {
                        desc = Jsoup.connect(link)
                                .get()
                                .getElementsByClass("msgTable").first()
                                .getElementsByTag("tbody").first().getElementsByTag("tr")
                                .get(1).child(1).text();
                        //.select("td[class='msgBody']")
                        //.get(1).text();
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                    }

                    if (checkNameTopic(name)) {

                        if (created.contains("сегодня")) {
                            created = created.replace("сегодня", setToday());
                        }
                        if (created.contains("вчера")) {
                            created = created.replace("вчера", setYesterday());
                        }

                        date = convertOfDate(created);
                        Vacancy v = new Vacancy(name, desc, link, date);
                        vacancies.add(v);
                    }
                }
                //vacancies.stream().forEach(System.out::println);
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }

            if (date.before(start)) {
                break;
            }

        }
        return vacancies;
    }

    /**
     * Возвращает число страниц в топике.
     * @return n число страниц
     */
    private Integer getPages(String url) {
        Integer n = 0;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements sortOptions = doc.getElementsByClass("sort_options");
            Element lastPage = sortOptions.last().getElementsByTag("a").last();
            //Element lastPage = doc.select("td[style='text-align:left'] a[href]").last();
            n = Integer.valueOf(lastPage.text());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return n;
    }

    /**
     * Проверяет содержит ли название вакансии слово "java"
     * и не содержит "javascript", "java script",
     * отстутствует ли метка "закрыт".
     * @return true or false
     */
    private boolean checkNameTopic(String name) {
        boolean check = false;
        String temp = name.toLowerCase();
        if (temp.contains("java".toLowerCase())
                && !temp.contains("закрыт".toLowerCase())
                && !temp.contains("javascript".toLowerCase())
                && !temp.contains("java script".toLowerCase())
        ) {
            check = true;
        }
        return check;
    }

    /**
     * Возвращает всегодняшнюю дату в виде "d MM yy".
     * @return timeStamp дата в формате строки
     */
    private String setToday() {
        Date time = Calendar.getInstance().getTime();
        String timeStamp = new SimpleDateFormat("d MM yy").format(time);
        return timeStamp;
    }

    /**
     * Возвращает вчерашнюю дату в виде "d MM yy".
     * @return timeStamp дата в формате строки
     */
    private String setYesterday() {
        LocalDateTime in = LocalDateTime.now().minusDays(1);
        Date time = java.sql.Timestamp.valueOf(in);
        String timeStamp = new SimpleDateFormat("d MM yy").format(time);
        return timeStamp;
    }

    /**
     * Конвертирует дату публикации из строки в дату фомарта "dd MM yy, HH:mm".
     * @param stringDate строка
     * @return date дата
     */
    private Date convertOfDate(String stringDate) {
        Date date = null;
        String[] givenMonths = {"янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"};
        String[] realMonths = {"01", "02", "03", "04", "05", "06",
                "07", "08", "09", "10", "11", "12"};
        for (int k = 0; k < givenMonths.length; k++) {
            stringDate = stringDate.replace(givenMonths[k], realMonths[k]);
        }
        try {
            DateFormat format = new SimpleDateFormat("dd MM yy, HH:mm", Locale.ENGLISH); //new Locale("RU")
            date = format.parse(stringDate);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return date;
    }

}
