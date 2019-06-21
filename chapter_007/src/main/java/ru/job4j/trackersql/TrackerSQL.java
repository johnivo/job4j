package ru.job4j.trackersql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 19.06.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    /**
     * Log.
     */
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    /**
     * Connection.
     */
    private Connection connection;

    /**
     * For select comment by id.
     */
    private final String commentById = "select comment from comments where item_id = ?";

    /**
     * Constructor.
     */
    public TrackerSQL() {
        this.init();
    }

    /**
     * Initializing the connection to base.
     * @return true or false
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            DatabaseMetaData dbm = this.connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "item", null);
            if (tables == null || !tables.next()) {
                createStructure();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Creating a table structure by script from a file.
     */
    private void createStructure() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        TrackerSQL.class.getClassLoader().getResourceAsStream("structure.sql")))
        ) {
            String line;
            try (Statement st = this.connection.createStatement()) {
                while ((line = br.readLine()) != null) {
                    st.execute(line);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public Item add(Item item) {
        String insertItem = "insert into item (name, description, category_id, state_id, user_id, created) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = this.connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setInt(3, 1);
            ps.setInt(4, 1);
            ps.setInt(5, 1);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            LOG.info(ps.toString());
            if (ps.executeUpdate() > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int itemId = generatedKeys.getInt(1);
                        System.out.println(String.valueOf(itemId));
                        item.setId(String.valueOf(itemId));
                        String insertComment = "insert into comments (item_id, comment) values (?, ?)";
                        try (PreparedStatement psComment = this.connection.prepareStatement(insertComment)) {
                            String[] comments = item.getComments();
                            if (comments != null) {
                                for (String comment : comments) {
                                    psComment.setInt(1, itemId);
                                    psComment.setString(2, comment);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean replaced = false;
        String replaceItem = "update item set name = ?, description = ?, created = ? where item_id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(replaceItem)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setInt(4, Integer.parseInt(item.getId()));
            LOG.info(ps.toString());
            if (ps.executeUpdate() > 0) {
                replaced = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return replaced;
    }

    @Override
    public boolean delete(String id) {
        boolean deleted = false;
        String deleteById = "delete from item where item_id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(deleteById)) {
            ps.setInt(1, Integer.parseInt(id));
            LOG.info(ps.toString());
            if (ps.executeUpdate() > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return deleted;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        String selectAll = "select i.item_id, i.name, i.description, i.created, c.comment from item as i left outer join comments as c on i.item_id = c.item_id";
        try (PreparedStatement ps = this.connection.prepareStatement(selectAll)) {
            LOG.info(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(selectedItem(rs));
                System.out.println(
                        String.format(
                                "%s %s",
                                rs.getString("name"),
                                rs.getString("created")
                        )
                );
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        String selectByName = "select i.item_id, i.name, i.description, i.created, c.comment from item as i "
                + "left outer join comments as c on i.item_id = c.item_id "
                + "where i.name = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(selectByName)) {
            ps.setString(1, key);
            LOG.info(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(selectedItem(rs));
                System.out.println(
                        String.format(
                                "%s %s %s",
                                rs.getString("name"),
                                rs.getString("created"),
                                rs.getString("comment")
                        )
                );
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        String selectById = "select i.item_id, i.name, i.description, i.created, c.comment from item as i "
                + "left outer join comments as c on i.item_id = c.item_id "
                + "where i.item_id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(selectById)) {
            ps.setInt(1, Integer.parseInt(id));
            LOG.info(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = selectedItem(rs);
                System.out.println(
                        String.format(
                                "%s %s %s",
                                rs.getString("name"),
                                rs.getString("created"),
                                rs.getString("comment")
                        )
                );
                //System.out.println(item.toString());
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Selected item from the database..
     * @param rs instance of ResultSet.
     * @return item.
     */
    private Item selectedItem(ResultSet rs) {
        Item item = null;
        try (PreparedStatement psComment = connection.prepareStatement(this.commentById)) {
            item = new Item();
            item.setId(String.valueOf(rs.getInt("item_id")));
            item.setName(rs.getString("name"));
            item.setDesc(rs.getString("description"));
            item.setCreated(rs.getTimestamp("created").getTime());
            psComment.setInt(1, Integer.parseInt(item.getId()));
            List<String> comments = new ArrayList<>();
            ResultSet rsComment = psComment.executeQuery();
            while (rsComment.next()) {
                comments.add(rsComment.getString("comment"));
            }
            item.setComments(comments.toArray(new String[comments.size()]));
        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] args) {
        TrackerSQL sql = new TrackerSQL();
        Item item = new Item("new item 2", "test 2", System.currentTimeMillis());
        sql.add(item);
        item.setName("replaced 2");
        sql.replace(item.getId(), item);
        sql.delete("10");
        sql.findById("3");
        sql.findByName("Goods by mail");
        sql.findAll();
    }

}
