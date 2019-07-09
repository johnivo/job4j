package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 02.07.2019
 */
public class SqlRuParser implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());

    private Connection connection;

    private String url;

    /**
     * размер пакета
     */
    private final int batchSize = 50;

    public SqlRuParser() {
    }

    /**
     * Инициализирует соединение с базой данных.
     * @param config файл с настройками
     * @return true or false
     */
    private boolean init(Properties config) {
        try {
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            url = config.getProperty("json.url");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Возвращает последнее время запуска, если первый запуск, то дату начала года.
     * @return lastRunTime время запуска
     */
    private Date getLastRunTime() {

        LocalDateTime beginning = LocalDateTime.now().with(firstDayOfYear());
        Date lastRunTime = java.sql.Timestamp.valueOf(beginning);
        int count = 0;

        String selectLastTime = "SELECT created FROM Vacancy ORDER BY created DESC LIMIT 1";
        try (PreparedStatement ps = this.connection.prepareStatement(selectLastTime)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lastRunTime = new Date(rs.getTimestamp("created").getTime());
                count++;
            }
            if (count == 0) {
                LOG.info("This is first run. Parsing is done with " + lastRunTime);
            } else {
                LOG.info("Last run date " + lastRunTime);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return lastRunTime;
    }

    /**
     * Добавляет вакансии из списка в базу данных.
     * @param input список вакансий
     * @param start время начала парсинга
     * @param config файл с настройками
     */
    private void writeToDB(List<Vacancy> input, Date start, Properties config) {
        this.init(config);
        int count = 0;
        String insertEntry = "INSERT INTO Vacancy (name, description, link, created) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = this.connection.prepareStatement(insertEntry)) {
            this.connection.setAutoCommit(false);
            //for (int i = 1; i <= vacancies.size(); i++) {
            for (Vacancy vac : input) {
                if (vac.getCreated().before(start)) {
                    continue;
                }
                ps.setString(1, vac.getName());
                ps.setString(2, vac.getDesc());
                ps.setString(3, vac.getLink());
                ps.setTimestamp(4, new Timestamp(vac.getCreated().getTime()));
                ps.addBatch();

                if (++count % batchSize == 0) {
                    ps.executeBatch();
                    this.connection.commit();
                }

            }
            ps.executeBatch();
            this.connection.commit();
            this.connection.setAutoCommit(true);
            LOG.info("Database entry done");
        } catch (SQLException e) {
            try {
                if (this.connection != null) {
                    this.connection.rollback();
                }
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Удаляет дабликаты записей в базе данных.
     * Сравниваются имя и описание вакансии, остается последняя по дате публикации вакансия.
     * @return true or false
     */
    private boolean removeDuplicates() {
        boolean deleted = false;
        String removeDuplicates = "DELETE FROM Vacancy as v USING Vacancy dup WHERE v.id < dup.id "
        //String removeDuplicates = "DELETE FROM Vacancy as v USING Vacancy dup WHERE v.created < dup.created "
                + "AND v.name = dup.name AND v.description = dup.description";
        try (PreparedStatement ps = this.connection.prepareStatement(removeDuplicates)) {
            int rows = ps.executeUpdate();
            if (rows > 0) {
                LOG.info(rows + " duplicates removed");
                deleted = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return deleted;
    }

    /**
     * Возвращает число страниц в топике.
     * @return n число страниц
     */
    private Integer getPages() {
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
     * Возвращает список вакансий java.
     * Парсит постранично заданный топик с вакансиями java
     * Вакансии, содержащие в названии javascript и java script пропускаются
     * @return vacancies дата
     */
    private List<Vacancy> parse(Date start) {
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc;
        int n = getPages();
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


    @Override
    public void close() throws Exception {
        if (connection != null) {
            this.connection.close();
        }
    }

    /**
     * Запускает парсер на выполнение.
     * Параметры заданы в файле с настройками.
     *
     * Инициализирует соединение с базой данных.
     * Устанавливает дату последнего запуска приложения,
     * если первый запуск - устанавливается дата начала года.
     * Парсит вакансии в список.
     * Записывает в базу вакансии из списка.
     * Удаляет дубликаты из базы.
     *
     * @param config файл с настройками
     */
    public void start(Properties config) {
        if (init(config)) {
            LOG.info("Database connection initialized");
        }
        Date start = getLastRunTime();
        List list = parse(start);
        writeToDB(list, start, config);
        removeDuplicates();
    }

}
