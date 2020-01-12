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
public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private Connection connection;

    public DBStore() {
        this.init();
    }

    /**
     * конструктор для тестов
     *
     * @param connection
     */
    public DBStore(Connection connection) {
        this.connection = connection;
    }

    /**
     * Инициализирует соединение с базой данных.
     *
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
        String insert = "INSERT INTO users (name, login, email, createDate, photoId, password, role, city_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            int cityId = getCityId(user.getCity());
            ps.setInt(8, cityId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, Integer id) {
        String update = "UPDATE users SET name = ?, login = ?, email = ?, password = ?, role = ?, city_id = ? "
                + "WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(update)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().getRole());

            int cityId = getCityId(user.getCity());
            ps.setInt(6, cityId);

            ps.setInt(7, id);

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
        String selectAll = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role, u.city_id, "
                + "city.name AS cityName, country.name AS countryName FROM users AS u "
                + "LEFT OUTER JOIN city ON u.city_id = city.id "
                + "LEFT OUTER JOIN country ON city.country_id = country.id "
                + "ORDER BY u.id";
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
        String selectById = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role, u.city_id, "
                + "city.name AS cityName, country.name AS countryName FROM users AS u "
                + "LEFT OUTER JOIN city ON u.city_id = city.id "
                + "LEFT OUTER JOIN country ON city.country_id = country.id "
                + "WHERE u.id = ?";
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
        String selectByLogin = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role, u.city_id, "
                + "city.name AS cityName, country.name AS countryName FROM users AS u "
                + "LEFT OUTER JOIN city ON u.city_id = city.id "
                + "LEFT OUTER JOIN country ON city.country_id = country.id "
                + "WHERE u.login = ?";
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
        String credential = "SELECT u.id, u.name, u.login, u.email, u.createDate, u.photoId, u.password, u.role, u.city_id "
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
        int id = rs.getInt("id");
        String name = rs.getString("name"); //можно по индексам колонок rs.getString(2)
        String login = rs.getString("login");
        String email = rs.getString("email");
        LocalDateTime createDate = rs.getTimestamp("createDate").toLocalDateTime();
        String photoId = rs.getString("photoId");
        String password = rs.getString("password");

        User user = new User(id, name, login, email, createDate, photoId, password);

        String role = rs.getString("role");
        user.setRole(new Role(role));

        int cityId = rs.getInt("city_id");

        String cityName = getCityNameByCityId(cityId);
        user.setCity(cityName);

        String countryName = getCountryNameByCityId(cityId);
        user.setCountry(countryName);

        return user;
    }

    @Override
    public List<String> getCountries() {
        List<String> countries = new ArrayList<>();
        String allCountries = "SELECT name FROM country ORDER BY name";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(allCountries)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String country = rs.getString("name");
                countries.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public List<String> getCities(String country) {
        List<String> cities = new ArrayList<>();
        String listCities = "SELECT city.name FROM city "
                + "INNER JOIN country ON city.country_id = country.id "
                + "WHERE country.name = ? "
                + "ORDER BY city.name";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(listCities)
        ) {
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String city = rs.getString("name");
                cities.add(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public int getCityId(String city) {
        int id = -1;
        String cityId = "SELECT id FROM city WHERE name = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(cityId)
        ) {
            st.setString(1, city);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getCityNameByCityId(int id) {
        String city = new String();
        String cityName = "SELECT name FROM city WHERE id = ?";
        if (id != -1) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(cityName)
            ) {
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    city = rs.getString("name");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return city;
    }

    public String getCountryNameByCityId(int id) {
        String country = new String();
        String countryName = "SELECT country.name FROM city INNER JOIN country "
                + "ON city.country_id = country.id "
                + "WHERE city.id = ?;";
        if (id != -1) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(countryName)
            ) {
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    country = rs.getString("name");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return country;
    }

}
