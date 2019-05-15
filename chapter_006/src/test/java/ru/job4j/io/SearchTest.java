package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.05.2019
 */
public class SearchTest {

    /**
     * Check search operation.
     *
     * @throws IOException possible.
     */
    @Test
    public void whenSearchSuccess() throws IOException {
        List exts = Arrays.asList(
                ".log",
                ".txt"
        );
        File parent = new File(
                System.getProperty("java.io.tmpdir")
                        + System.getProperty("file.separator")
                        + "dir1"
        );
        parent.mkdir();
        new File(parent + "/server.log").createNewFile();
        new File(parent + "/unavailable.csv").createNewFile();
        File dir2 = new File(parent + "/dir2/");
        dir2.mkdir();
        new File(dir2 + "/test.txt").createNewFile();
        new File(dir2 + "/test.doc").createNewFile();
        File dir3 = new File(dir2 + "/dir3/");
        dir3.mkdir();
        new File(dir3 + "/server2.log").createNewFile();
        new File(dir3 + "/test.php").createNewFile();
        Search search = new Search();
        List<File> rst = search.files(parent.getAbsolutePath(), exts);
        assertThat(rst.get(0).getName(), is("server.log"));
        assertThat(rst.get(1).getName(), is("test.txt"));
        assertThat(rst.get(2).getName(), is("server2.log"));
        assertThat(rst.size(), is(3));
        recursiveDelete(parent);
    }

    @Test
    public void whenFilesAreNotFound() throws IOException {
        List exts = Arrays.asList(
                ".csv"
        );
        File parent = new File(
                System.getProperty("java.io.tmpdir")
                        + System.getProperty("file.separator")
                        + "dir1"
        );
        parent.mkdir();
        new File(parent + "/server.log").createNewFile();
        File dir2 = new File(parent + "/dir2/");
        dir2.mkdir();
        new File(dir2 + "/test.txt").createNewFile();
        new File(dir2 + "/test.doc").createNewFile();


        Search search = new Search();
        List<File> rst = search.files(parent.getAbsolutePath(), exts);
        assertThat(rst.isEmpty(), is(true));
        assertThat(rst.size(), is(0));
        recursiveDelete(parent);
    }


    /**
     * Delete data.
     * @param file directory name.
     */
    public static void recursiveDelete(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    recursiveDelete(f);
                }
            }
            file.delete();
        }
    }
}