package ru.job4j.crud.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.crud.datamodel.Role;
import ru.job4j.crud.datamodel.User;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
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
        String insert = "INSERT INTO users (name, login, email, createDate, photoId, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(insert)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(user.getCreateDate()));
            ps.setString(5, user.getPhotoId());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getRole().getRole());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, Integer id) {
        String update = "UPDATE users SET name = ?, login = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(update)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().getRole());
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadImage(User user) {
        String upload = "UPDATE users SET photoId = ? WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(upload)
        ) {
            ps.setString(1, user.getPhotoId());
            ps.setInt(2, user.getId());

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
        String selectAll = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role FROM users AS u";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = getUser(rs);
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
        String selectById = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role FROM users AS u WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectById)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = getUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = new User();
        String selectByLogin = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role FROM users AS u WHERE login = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectByLogin)
        ) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = getUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User isCredential(String login, String password) {
        User user = new User();
        String credential = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role "
                + "FROM users AS u WHERE login = ? AND password = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(credential)
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = getUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private User getUser(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String name = rs.getString(2); //или rs.getString("name")
        String login = rs.getString(3);
        String email = rs.getString(4);
        LocalDateTime createDate = rs.getTimestamp(5).toLocalDateTime();
        String photoId = rs.getString(6);
        String password = rs.getString(7);
        String role = rs.getString(8);
        User user = new User(id, name, login, email, createDate, photoId, password);
        user.setRole(new Role(role));
        return user;
    }
}
