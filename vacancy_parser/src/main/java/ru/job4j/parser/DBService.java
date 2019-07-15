package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
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

    private String url;

    /**
     * размер пакета
     */
    private final int batchSize = 50;

    public DBService() {
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

        List list = new SqlRuParser().parse(url, start);

        writeToDB(list, start, config);
        removeDuplicates();
    }

}
