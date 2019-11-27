package ru.job4j.crud.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.crud.datamodel.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 25.11.2019
 */
public class DBStore implements Store<User> {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private Connection connection;

    public DBStore() {
        this.init();
    }

    /**
     * конструктор для тестов
     * @param connection
     */
    public DBStore(Connection connection) {
        this.connection = connection;
    }

    /**
     * Инициализирует соединение с базой данных.
     * @return true or false
     */
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

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        String insert = "INSERT INTO users (name, login, email, createDate) VALUES (?, ?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(insert)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(user.getCreateDate()));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, Integer id) {
        String update = "UPDATE users SET name = ?, login = ?, email = ? WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(update)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            //ps.setTimestamp(4, Timestamp.valueOf(user.getCreateDate()));
            //ps.setInt(5, id);
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String delete = "DELETE FROM users WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(delete)
        ) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String selectAll = "SELECT u.id, u.name, u.login, u.email, u.createDate FROM users AS u";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5).toLocalDateTime()
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("login"),
//                        rs.getString("email"),
//                        rs.getTimestamp(5).toLocalDateTime()
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        String selectById = "SELECT u.id, u.name, u.login, u.email, u.createDate FROM users AS u WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectById)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setCreateDate(rs.getTimestamp(5).toLocalDateTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
