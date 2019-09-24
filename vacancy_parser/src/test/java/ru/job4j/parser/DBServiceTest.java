package ru.job4j.parser;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 23.09.2019
 */
public class DBServiceTest {

    public Connection init() {
        try (InputStream in = DBService.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() {
        try (InputStream in = DBService.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            DBService dbs = new DBService(config);

            boolean result = dbs.init();

            assertThat(result, is(true));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddListOf2UniqueVacanciesThenGet2NewEntriesInDB() {
        Date date = new Date();
        Vacancy first = new Vacancy("name", "desc1", "https://www.sql.ru/forum/1314951/sistemnyy-programmist-c-java-ot-200tr", date);
        Vacancy second = new Vacancy("name", "desc2", "link", date);
        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(first);
        vacancies.add(second);

        try (DBService dbs = new DBService(ConnectionRollback.create(this.init()))) {

            dbs.writeToDB(vacancies, date, dbs.getConnection());
            List<Vacancy> result = dbs.findByName("name", dbs.getConnection());
            System.out.println(result.get(0));

            assertTrue(result.contains(first));
            assertTrue(result.contains(second));
            assertThat(result.size(), is(2));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddListOf2SameVacanciesThenRemoveFirstDuplicateInDB() throws SQLException {

        DBService dbs = new DBService(ConnectionRollback.create(this.init()));
        Date date = new Date();
        List<Vacancy> vacancies = List.of(
                new Vacancy("name", "desc", "link", date),
                new Vacancy("name", "desc", "link", date)
        );

        dbs.writeToDB(vacancies, date, dbs.getConnection());
        dbs.removeDuplicates(dbs.getConnection());
        List<Vacancy> result = dbs.findByName("name", dbs.getConnection());

        assertThat(result.size(), is(1));

    }

    @Test
    public void whenAddListOf3VacanciesThenRemoveDuplicateAndGet2NewEntriesInDB() {

        try (DBService dbs = new DBService(ConnectionRollback.create(this.init()))) {
            Date date = new Date();
            List<Vacancy> vacancies = List.of(
                    new Vacancy("name", "desc1", "https://www.sql.ru/forum/1314951/sistemnyy-programmist-c-java-ot-200tr", date),
                    new Vacancy("name", "desc", "link", date),
                    new Vacancy("name", "desc", "link", date)
            );

            dbs.writeToDB(vacancies, date, dbs.getConnection());
            dbs.removeDuplicates(dbs.getConnection());
            List<Vacancy> result = dbs.findByName("name", dbs.getConnection());

            assertThat(result.size(), is(2));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    //на будущее
//    <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
//        <dependency>
//            <groupId>commons-io</groupId>
//            <artifactId>commons-io</artifactId>
//            <version>2.6</version>
//        </dependency>
}