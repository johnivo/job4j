package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 23.03.2020
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    private Connection connection;

    private final String commentById = "select c.comment from comments as c where item_id = ?";

    public TrackerSQL() {
        if (init()) {
            LOG.info("Database connection initialized");
        }
    }

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
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
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
            ps.setTimestamp(6, new Timestamp(item.getCreated()));
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
                                    psComment.addBatch();
                                    psComment.executeBatch();
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
            ps.setTimestamp(3, new Timestamp(item.getCreated()));
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
        String selectAll = "select i.item_id, i.name, i.description, i.created, c.comment from item as i "
                + "left outer join comments as c on i.item_id = c.item_id";
        //String selectAll = "select i.item_id, i.name, i.description, i.created from item as i";
        try (PreparedStatement ps = this.connection.prepareStatement(selectAll)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(selectedItem(rs));
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
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(selectedItem(rs));
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
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = selectedItem(rs);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

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

}
