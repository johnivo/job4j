package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 27.06.2019
 */
public class StoreSQL implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    private final Config config;
    private Connection connect;

    private final int batchSize = 10000;

    /**
     * Конструктор.
     * @param config параметры соединения
     */
    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * Генерирует в базе данных n записей.
     * @param size количество записей
     */
    public void generate(int size) {
        this.databaseConnection();
        int count = 0;
        String insertEntry = "INSERT INTO entry (field) VALUES (?)";
        try (PreparedStatement ps = this.connect.prepareStatement(insertEntry)) {
            this.connect.setAutoCommit(false);
            for (int i = 1; i <= size; i++) {
                ps.setInt(1, i);
                ps.addBatch();

                if (++count % batchSize == 0) {
                    System.out.println(count);
                    ps.executeBatch();
                    this.connect.commit();
                }

            }
            ps.executeBatch();
            this.connect.commit();
            this.connect.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                if (this.connect != null) {
                    this.connect.rollback();
                }
            } catch (SQLException e2) {
                LOG.error(e2.getMessage(), e2);
            }
        }
    }

    /**
     * Возвращает список всех записей поля field.
     * @return entries
     */
    public List<Entry> load() {
        List<Entry> entries = new ArrayList<>();
        try (Statement st = this.connect.createStatement();
             ResultSet rs = st.executeQuery("SELECT field FROM entry")) {
            while (rs.next()) {
                entries.add(new Entry(rs.getInt(1)));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return entries;
    }

    /**
     * Инициализация соединения и создание структуры в базе.
     */
    private void databaseConnection() {
        String url = config.get("url");
        String file = url.replace("jdbc:sqlite:", "");
        System.out.println(file);
        File db = new File(file);
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            if (db.exists()) {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS entry (field INTEGER)");
                st.executeUpdate("DELETE FROM entry");
                System.out.println("A table is ready to write.");
            } else {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS entry (field INTEGER)");
                System.out.println("A new database has been created.");
            }
            this.connect = conn;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

}
