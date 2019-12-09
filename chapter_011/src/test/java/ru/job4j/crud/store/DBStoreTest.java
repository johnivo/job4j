package ru.job4j.crud.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import ru.job4j.crud.datamodel.Role;
import ru.job4j.crud.datamodel.User;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 26.11.2019
 */
public class DBStoreTest {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    public BasicDataSource init() {
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setMinIdle(Integer.parseInt(config.getProperty("minIdle")));
            SOURCE.setMaxIdle(Integer.parseInt(config.getProperty("maxIdle")));
            SOURCE.setMaxOpenPreparedStatements(Integer.parseInt(config.getProperty("maxOpenPreparedStatements")));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return SOURCE;
    }

    @Test
    public void checkConnectionPool() {
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            DBStore dbs = new DBStore();

            String url = dbs.init().getUrl();
            String username = dbs.init().getUsername();
            String password = dbs.init().getPassword();
            String driverClassName = dbs.init().getDriverClassName();
            Integer minIdle = dbs.init().getMinIdle();
            Integer maxIdle = dbs.init().getMaxIdle();
            Integer maxOpenPreparedStatements = dbs.init().getMaxOpenPreparedStatements();

            assertThat(url, is("jdbc:postgresql://127.0.0.1:5432/users1"));
            assertThat(username, is("postgres"));
            assertThat(password, is("password"));
            assertThat(driverClassName, is("org.postgresql.Driver"));
            assertThat(minIdle, is(5));
            assertThat(maxIdle, is(10));
            assertThat(maxOpenPreparedStatements, is(100));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

//    @Test
//    public void whenAdd2UsersThenGet2NewEntriesInDB() {
//        DBStore dbs = DBStore.getInstance();
//        User user1 = new User(null, "user1", "user1", "user1@mail.ru", LocalDateTime.now());
//        User user2  = new User(null, "user2", "user2", "user2@mail.ru", LocalDateTime.now());
//        int firstSize = dbs.findAll().size();
//
//        dbs.add(user1);
//        dbs.add(user2);
//        int secondSize = dbs.findAll().size();
//
//        assertThat(firstSize + 2, is(secondSize));
//    }

    @Test
    public void whenAdd2UsersThenGet2NewEntriesInDB() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        User user1 = new User(null, "user1", "user1", "user1@mail.ru",
                LocalDateTime.now(), null, "user1");
        user1.setRole(new Role("admin"));
        User user2  = new User(null, "user2", "user2", "user2@mail.ru",
                LocalDateTime.now(), null, "user2");
        user2.setRole(new Role("user"));
        int firstSize = dbs.findAll().size();

        dbs.add(user1);
        dbs.add(user2);
        int secondSize = dbs.findAll().size();

        assertThat(firstSize + 2, is(secondSize));
    }

    @Test
    public void whenUpdateUsernameThenGetNewUsername() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        User user1 = new User(null, "user1", "user1", "user1@mail.ru",
                LocalDateTime.now(), null, "user1");
        user1.setRole(new Role("admin"));
        dbs.add(user1);
        List<User> result = dbs.findAll();
        int id = result.get(result.size() - 1).getId();
        String login = result.get(result.size() - 1).getLogin();
        String password = result.get(result.size() - 1).getPassword();
        user1.setName("replaced");

        dbs.update(user1, id);

        assertThat(dbs.findById(id).getName(), is("replaced"));
        assertThat(dbs.findByLogin(login).getName(), is("replaced"));
        assertThat(dbs.isCredential(login, password).getName(), is("replaced"));
    }

    @Test
    public void whenDeleteUserThenDontGetNewEntryInDB() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        User user1 = new User(null, "user1", "user1", "user1@mail.ru",
                LocalDateTime.now(), null, "user1");
        user1.setRole(new Role("admin"));
        int firstSize = dbs.findAll().size();
        dbs.add(user1);
        List<User> result = dbs.findAll();
        int id = result.get(result.size() - 1).getId();

        dbs.delete(id);
        int secondSize = dbs.findAll().size();

        assertThat(firstSize, is(secondSize));
    }

//    public Connection init() {
//        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
//            Properties config = new Properties();
//            config.load(in);
//            Class.forName(config.getProperty("driver-class-name"));
//            return DriverManager.getConnection(
//                    config.getProperty("url"),
//                    config.getProperty("username"),
//                    config.getProperty("password")
//            );
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//    }
}