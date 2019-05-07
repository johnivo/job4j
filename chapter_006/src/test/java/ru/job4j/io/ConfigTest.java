package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 07.05.2019
 */
public class ConfigTest {

    /**
     * Check load operations.
     * @throws IOException possible.
     */
    @Test
    public void whenLoadConfigThenSizeMapIsSeven() throws IOException {
        Config config = new Config("app.properties");
        assertThat(config.load().size(), is(7));
    }

    /**
     * Check read operations.
     * @throws IOException possible.
     */
    @Test
    public void whenReadConfig() throws IOException {
//        File path = new File("app.properties");
//        Config config = new Config(path.getAbsolutePath()).load();
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config);
        assertThat(config.value("## PostgreSQL"), is(""));
        assertThat(config.value(""), is(""));
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

}