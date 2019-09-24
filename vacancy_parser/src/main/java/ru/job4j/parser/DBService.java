package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * Обеспечивает взаимодействие с БД.
 * Содержит метод start, определяющий функционал приложения.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 11.07.2019
 */
public class DBService implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(DBService.class.getName());

    private Connection connection;

    private Properties config;

    private String url;

    /**
     * конструктор.
     * @param properties - файл с настройками соединения.
     */
    public DBService(Properties properties) {
        this.config = properties;
        if (init()) {
            LOG.info("Database connection initialized");
        }
    }

    /**
     * конструктор для тестов.
     * @param connection - autocommit=false.
     */
    public DBService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Инициализирует соединение с базой данных.
     * @return true or false
     */
    public boolean init() {
        try {
            Class.forName(config.getProperty("driver-class-name"));
            Connection connection = DriverManager.getConnection(
                    this.config.getProperty("url"),
                    this.config.getProperty("username"),
                    this.config.getProperty("password")
            );
            url = this.config.getProperty("json.url");

            this.connection = connection;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Возвращает последнее время запуска, если первый запуск, то дату начала года.
     * @param connection соединение с бд
     * @return lastRunTime время запуска
     */
    public Date getLastRunTime(Connection connection) {

        LocalDateTime beginning = LocalDateTime.now().with(firstDayOfYear());
        Date lastRunTime = java.sql.Timestamp.valueOf(beginning);
        int count = 0;

        String selectLastTime = "SELECT created FROM Vacancy ORDER BY created DESC LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(selectLastTime)) {
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
     * @param connection соединение с бд
     */
    public void writeToDB(List<Vacancy> input, Date start, Connection connection) {

        String insertEntry = "INSERT INTO Vacancy (name, description, link, created) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertEntry)) {
            for (Vacancy vac : input) {
                if (vac.getCreated().before(start)) {
                    continue;
                }
                ps.setString(1, vac.getName());
                ps.setString(2, vac.getDesc());
                ps.setString(3, vac.getLink());
                ps.setTimestamp(4, new Timestamp(vac.getCreated().getTime()));
                ps.addBatch();
            }
            ps.executeBatch();
            LOG.info("Database entry done");
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    /**
     * Удаляет дабликаты записей в базе данных.
     * Сравниваются имя и описание вакансии, остается последняя по дате публикации вакансия.
     * @param connection соединение с бд
     * @return true or false
     */
    public boolean removeDuplicates(Connection connection) {
        boolean deleted = false;
        String removeDuplicates = "DELETE FROM Vacancy as v USING Vacancy dup WHERE v.id < dup.id "
        //String removeDuplicates = "DELETE FROM Vacancy as v USING Vacancy dup WHERE v.created < dup.created "
                + "AND v.name = dup.name AND v.description = dup.description";
        try (PreparedStatement ps = connection.prepareStatement(removeDuplicates)) {
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            this.connection.close();
        }
    }

    /**
     * @return connection текущее соединение с бд
     */
    public Connection getConnection() {
        return this.connection;
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
     * @param connection соединение с бд
     */
    public void start(Connection connection) {

        Date start = getLastRunTime(connection);

        SqlRuParser parser = new SqlRuParser();
        int pages = parser.getPages(url);
        List list = parser.parse(url, pages, start);

        writeToDB(list, start, connection);
        removeDuplicates(connection);
    }

    /**
     * Находит вакансии в базе данных по имени и возвращает их список.
     * @param name имя вакансии
     * @param connection соединение с бд
     * @return vacancies список вакансий
     */
    public List<Vacancy> findByName(String name, Connection connection) {
        List<Vacancy> vacancies = new ArrayList<>();
        String selectByName = "SELECT * FROM Vacancy where name = ?";
        try (PreparedStatement ps = connection.prepareStatement(selectByName)) {
            ps.setString(1, name);
            LOG.info(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vacancies.add(
                        new Vacancy(
                                rs.getString(2), //первый id serial primary key
                                rs.getString(3),
                                rs.getString(4),
                                rs.getTimestamp(5)
                        )
                );
                System.out.println(
                        String.format(
                                "%s %s %s %s",
                                rs.getString("name"), //можно по названиям
                                rs.getString("description"),
                                rs.getString("link"),
                                rs.getTimestamp("created")
                        )
                );
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancies;
    }
}
