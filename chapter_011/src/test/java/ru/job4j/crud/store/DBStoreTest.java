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

import static java.util.Arrays.asList;
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
        user1.setCountry("Belarus");
        user1.setCity("Minsk");
        User user2  = new User(null, "user2", "user2", "user2@mail.ru",
                LocalDateTime.now(), null, "user2");
        user2.setRole(new Role("user"));
        user2.setCountry("Belarus");
        user2.setCity("Brest");
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
        user1.setRole(new Role("user"));
        user1.setCountry("Belarus");
        user1.setCity("Minsk");
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
    public void whenUpdateLocationThenGetNewLocation() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        User user3 = new User(null, "user3", "user3", "user1@mail.ru",
                LocalDateTime.now(), null, "user3");
        user3.setRole(new Role("user"));
        user3.setCountry("Belarus");
        user3.setCity("Minsk");
        dbs.add(user3);
        List<User> result = dbs.findAll();
        int id = result.get(result.size() - 1).getId();

        user3.setName("RusVol");
        user3.setCountry("Russia");
        user3.setCity("Vologda");

        dbs.update(user3, id);
        User upUser = dbs.findById(id);

        assertThat(upUser.getName(), is("RusVol"));
        assertThat(upUser.getCountry(), is("Russia"));
        assertThat(upUser.getCity(), is("Vologda"));
    }

    @Test
    public void whenDeleteUserThenDontGetNewEntryInDB() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        User user1 = new User(null, "user1", "user1", "user1@mail.ru",
                LocalDateTime.now(), null, "user1");
        user1.setRole(new Role("user"));
        user1.setCountry("Belarus");
        user1.setCity("Minsk");
        int firstSize = dbs.findAll().size();
        dbs.add(user1);
        List<User> result = dbs.findAll();
        int id = result.get(result.size() - 1).getId();

        dbs.delete(id);
        int secondSize = dbs.findAll().size();

        assertThat(firstSize, is(secondSize));
    }

    @Test
    public void getCountriesByDefaultTest() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        List<String> rst = dbs.getCountries();
        assertTrue(rst.containsAll(asList("Russia", "Belarus")));
    }

    @Test
    public void getCitiesByDefaultTest() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        List<String> rst = dbs.getCities("Russia");
        assertTrue(rst.containsAll(asList("Moscow", "Vologda")));
    }

    @Test
    public void getCityIdByDefaultTest() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        Integer id1 = dbs.getCityId("Moscow");
        Integer id2 = dbs.getCityId("Vologda");
        Integer id3 = dbs.getCityId("Minsk");
        Integer id4 = dbs.getCityId("Brest");
        assertThat(id1, is(1));
        assertThat(id2, is(2));
        assertThat(id3, is(3));
        assertThat(id4, is(4));
    }

    @Test
    public void getCityNameByCityIdByDefaultTest() throws SQLException {
        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
        String moscow = dbs.getCityNameByCityId(1);
        String vologda = dbs.getCityNameByCityId(2);
        String minsk = dbs.getCityNameByCityId(3);
        String brest = dbs.getCityNameByCityId(4);
        assertThat(moscow, is("Moscow"));
        assertThat(vologda, is("Vologda"));
        assertThat(minsk, is("Minsk"));
        assertThat(brest, is("Brest"));
    }

//    @Test
//    public void getCountryNameByCityIdByDefaultTest() throws SQLException {
//        DBStore dbs = new DBStore(ConnectionRollback.create(this.init().getConnection()));
//        String moscow = dbs.getCountryNameByCityId(1);
//        String vologda = dbs.getCountryNameByCityId(2);
//        String minsk = dbs.getCountryNameByCityId(3);
//        String brest = dbs.getCountryNameByCityId(4);
//        assertThat(moscow, is("Russia"));
//        assertThat(vologda, is("Russia"));
//        assertThat(minsk, is("Belarus"));
//        assertThat(brest, is("Belarus"));
//    }

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