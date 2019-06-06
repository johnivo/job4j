package ru.job4j.io.finder;

import com.google.common.base.Joiner;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import ru.job4j.io.SearchTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 04.06.2019
 */
public class FinderTest {

    private static final String LN = System.lineSeparator();
    private static final String FN = System.getProperty("file.separator");

    /**
     * Parsing test.
     */
    @Test
    public void whenArgsAddThenAllExistsAsOption() {
        String[] args = {
                "-d", "c:/",
                "-n", "*.txt",
                "-m",
                "-o", "log.txt",
                "other"
        };
        Args keys = new Args(args);
        assertThat(keys.directory(), is("c:/"));
        assertThat(keys.name(), is("*.txt"));
        assertThat(keys.mask(), is(true));
        assertThat(keys.fullMatch(), is(false));
        assertThat(keys.regEx(), is(false));
        assertThat(keys.output(), is("log.txt"));
    }

    /**
     * Help test.
     */
    @Test
    public void whenParametersDontEnteredThenHelpAppears() {
        String[] args = {
        };
        Args keys = new Args(args);
    }

    /**
     * Simulates the search engine and reads the log file.
     * Compares output data with expected result.
     * @param args parameter list.
     * @param expected result.
     */
    public void testFinder(String[] args, String expected, String logName) {
        MainFinder.main(args);
        String line;
        StringBuilder result = new StringBuilder();
        File log = new File(System.getProperty("java.io.tmpdir") + FN + logName);
        try (BufferedReader reader = new BufferedReader(new FileReader(log.getAbsolutePath()))
        ) {
            while ((line = reader.readLine()) != null) {
                result.append(line);
                result.append(LN);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(result.toString(), is(expected));
        log.deleteOnExit();
    }

    /**
     * Check search mask.
     */
    @Test
    @Ignore
    public void whenSearchingByMaskThenResultsAreLogged() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        String parent = path + "MaskTest";
        File source = new File(parent);
        source.mkdirs();
        new File(parent + FN + "dir1").mkdirs();
        new File(parent + FN + "dir2").mkdirs();
        new File(parent + FN + "dir2" + FN + "dir3").mkdirs();
        new File(parent + FN + "file1.txt").createNewFile();
        new File(parent + FN + "dir1" + FN + "file2.txt").createNewFile();
        new File(parent + FN + "dir2" + FN + "bingo.xml").createNewFile();
        new File(parent + FN + "dir2" + FN + "dir3" + FN + "bingo.txt").createNewFile();
        String logName = "log.txt";
        String[] args = {
                "-d", parent,
                "-n", "*.txt",
                "-m",
                "-o", path + logName
        };
        this.testFinder(
                args,
                Joiner.on(LN).join(
                        parent + FN + "dir1" + FN + "file2.txt",
                        parent + FN + "dir2" + FN + "dir3" + FN + "bingo.txt",
                        parent + FN + "file1.txt",
                        ""
                ),
                logName

        );
        SearchTest.recursiveDelete(source);
    }

    /**
     * Check the search for the full file name.
     */
    @Test
    @Ignore
    public void whenSearchingByFullNameThenResultsAreLogged() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        String parent = path + "FullNameTest";
        File source = new File(parent);
        source.mkdirs();
        new File(parent + FN + "dir4").mkdirs();
        new File(parent + FN + "dir4" + FN + "bingo2.xml").createNewFile();
        new File(parent + FN + "dir4" + FN + "bingo2.txt").createNewFile();
        String logName = "log2.txt";
        String[] args = {
                "-d", parent,
                "-n", "bingo2.xml",
                "-f",
                "-o", path + logName
        };
        this.testFinder(
                args,
                Joiner.on(LN).join(
                        parent + FN + "dir4" + FN + "bingo2.xml",
                        ""
                ),
                logName
        );
        SearchTest.recursiveDelete(source);
    }

    /**
     * Check for regular expression searches.
     */
    @Test
    @Ignore
    public void whenSearchingByRegExpThenResultsAreLogged() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        String parent = path + "RegExTest";
        File source = new File(parent);
        source.mkdirs();
        new File(parent + FN + "dir5").mkdirs();
        new File(parent + FN + "dir6").mkdirs();
        new File(parent + FN + "dir6" + FN + "dir7").mkdirs();
        new File(parent + FN + "file3.txt").createNewFile();
        new File(parent + FN + "dir5" + FN + "file4.txt").createNewFile();
        new File(parent + FN + "dir6" + FN + "bingo3.xml").createNewFile();
        new File(parent + FN + "dir6" + FN + "dir7" + FN + "bingo3.txt").createNewFile();
        String logName = "log3.txt";
        String[] args = {
                "-d", parent,
                "-n", "(bingo).*",
                "-r",
                "-o", path + logName
        };
        this.testFinder(
                args,
                Joiner.on(LN).join(
                        parent + FN + "dir6" + FN + "bingo3.xml",
                        parent + FN + "dir6" + FN + "dir7" + FN + "bingo3.txt",
                        ""
                ),
                logName
        );
        SearchTest.recursiveDelete(source);
    }
}