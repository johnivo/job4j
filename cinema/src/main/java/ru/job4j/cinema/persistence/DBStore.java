package ru.job4j.cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.cinema.model.Seat;
import ru.job4j.cinema.model.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.02.2020
 */
public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private Connection connection;

    public DBStore(Connection connection) {
        this.connection = connection;
    }

    public DBStore() {
        this.init();
    }

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
    public List<Seat> getSeats() {
        List<Seat> result = new ArrayList<>();
        String selectAll = "SELECT * FROM halls";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Seat seat = createSeat(rs);
                result.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Seat getSeat(Seat seat) {
        Seat result = new Seat();
        String selectSeat = "SELECT * FROM halls AS h WHERE row=? AND place=?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectSeat)
        ) {
            ps.setInt(1, seat.getRow());
            ps.setInt(2, seat.getPlace());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = createSeat(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Seat createSeat(ResultSet rs) throws SQLException {
        int row = rs.getInt("row");
        int place = rs.getInt("place");
        int price = rs.getInt("price");
        boolean booked = rs.getBoolean("booked");
        Seat seat = new Seat(row, place, price, booked);
        return seat;
    }

    @Override
    public boolean makePayment(Seat seat, User user) {
        boolean result = false;
        String selectSeat = "SELECT * FROM halls WHERE row=? AND place=?";
        String updateSeat = "UPDATE halls SET booked=? WHERE id=?";
        String insertAcc = "INSERT INTO accounts (username, phone, halls_id) VALUES (?, ?, ?)";
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps1 = connection.prepareStatement(selectSeat)) {
                ps1.setInt(1, seat.getRow());
                ps1.setInt(2, seat.getPlace());
                ResultSet rs = ps1.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    try (PreparedStatement ps2 = connection.prepareStatement(updateSeat)) {
                        ps2.setBoolean(1, true);
                        ps2.setInt(2, id);
                        if (ps2.executeUpdate() == 1) {
                            result = true;
                        }
                    }
                    if (result) {
                        try (PreparedStatement ps3 = connection.prepareStatement(insertAcc)) {
                            ps3.setString(1, user.getUsername());
                            ps3.setString(2, user.getPhone());
                            ps3.setInt(3, id);
                            ps3.executeUpdate();
                        }
                        connection.commit();
                    } else {
                        connection.rollback();
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
