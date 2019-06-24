package ru.job4j.trackersql;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 19.06.2019
 */
public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
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
    public void checkConnection() throws SQLException {
        TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenCreateItemAndGetByIdItemIsSame() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name", "desc", System.currentTimeMillis());
            item.setComments(new String[] {"com1", "com2"});
            Item expected = tracker.add(item);
            Item actual = tracker.findById(expected.getId());
            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual, is(expected));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenCreateItemWithTwoCommentsAndGetByIdThenGetItemTwiceWithDifferentComments() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("new", "desc", System.currentTimeMillis());
            item.setComments(new String[] {"com1", "com2"});
            Item expected = tracker.add(item);
            List<Item> actual = tracker.findByName("new");
            assertThat(actual.size(), is(2));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenCreateThreeItemsWithSameNameAndGetByNameThenGetListOfThreeItems() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            List<Item> input = List.of(
                    new Item("new", "desc", System.currentTimeMillis()),
                    new Item("new", "desc2", System.currentTimeMillis()),
                    new Item("new", "desc3", System.currentTimeMillis())
            );
            for (Item item : input) {
                tracker.add(item);
            }
            List<Item> actual = tracker.findByName("new");
            assertThat(actual.size(), is(3));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Ignore
    @Test
    public void whenFindAllThenFindsAllItems() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            List<Item> input = List.of(
                    new Item("new", "desc", System.currentTimeMillis()),
                    new Item("new2", "desc2", System.currentTimeMillis())
            );
            for (Item item : input) {
                tracker.add(item);
            }
            List<Item> actual = tracker.findAll();
            //в базе есть 4 элеммента, в т.ч. один с двумя комментами, плюс здесь 2 создаются
            assertThat(actual.size(), is(7));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenDeleteItemByIdThenItemIsDeleted() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("delete item", "", 0L);
            sql.add(item);
            assertTrue(sql.delete(item.getId()));
            assertNull(sql.findById(item.getId()));
            assertFalse(sql.delete(item.getId()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenReplaceItemAndGetByIdThenItemIsReplaced() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("replace item", "res", 0L);
            item.setComments(new String[]{});
            sql.add(item);
            item.setName("replaced");
            assertTrue(sql.replace(item.getId(), item));
            assertThat(item, is(sql.findById(item.getId())));
            assertThat(sql.findById(item.getId()).getName(), is("replaced"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}