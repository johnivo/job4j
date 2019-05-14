package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.05.2019
 */
public class AnalizyTest {

    /**
     * Check analizy operations (reading from file and checking data, write to file) .
     * @throws IOException possible.
     */
    @Test
    public void whenReadConfig() throws IOException {
        File source = data(
                "server.log",
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "200 10:59:01",
                "500 11:01:02",
                "200 11:02:02"
        );

        File target = data(
                "unavailable.csv",
                ""
        );
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder content = new StringBuilder();
        try (BufferedReader file = new BufferedReader(new FileReader(target))) {
            file.lines().forEach(
                    line -> content.append(line).append(System.lineSeparator())
            );
        }
        assertThat(
                content.toString(),
                is(
                        String.join(System.lineSeparator(),
                                "10:57:01;10:59:01;",
                                "11:01:02;11:02:02;", ""
                        )
                )
        );

        source.deleteOnExit();
        target.deleteOnExit();
    }

    /**
     * Fill input data.
     * @param file properties file.
     * @param properties data, TYPE date.
     * @return file.
     * @throws IOException possible.
     */
    private File data(String file, String... properties) throws IOException {
        File path = new File(
                System.getProperty("java.io.tmpdir")
                        + System.getProperty("file.separator")
                        + file
        );
        if (!path.createNewFile()) {
            throw new IllegalStateException(String.format("File could not created %s", path.getAbsoluteFile()));
        }
        try (final PrintWriter store = new PrintWriter(path)) {
            Stream.of(properties).forEach(store::println);
        }
        return path;
    }
}