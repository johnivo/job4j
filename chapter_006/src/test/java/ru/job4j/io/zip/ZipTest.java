package ru.job4j.io.zip;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.ParseException;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.05.2019
 */
public class ZipTest {

    /**
     * Parsing test.
     */
    @Test
    public void whenArgsAddThenAllExistsAsOption() throws ParseException {
        String[] args = {
                "-d",
                "c:/Users/Barclay/AppData/Local/Temp/project",
                "-e",
                ".java", ".xml", ".dll",
                "-o",
                "test.zip",
                "other"
        };
        Args keys = new Args(args);
        assertThat(keys.directory(), is("c:/Users/Barclay/AppData/Local/Temp/project"));
        assertThat(keys.output(), is("test.zip"));
        assertEquals(keys.exclude(), Arrays.asList(".java", ".xml", ".dll"));
        assertThat(keys.exclude(), is(Arrays.asList(".java", ".xml", ".dll")));
    }

    /**
     * Archiving test.
     */
    @Test
    public void whenDirectoryPackInZipThenTestZipFileIsCreated2() throws IOException, ParseException {
        File project = new File(
                System.getProperty("java.io.tmpdir")
                        + System.getProperty("file.separator")
                        + "project"
        );
        project.mkdirs();
        new File(project + "/file0.csv").createNewFile();
        new File(project + "/qwerty").mkdirs();
        new File(project + "/dir1").mkdirs();
        new File(project + "/dir1/file1.csv").createNewFile();
        new File(project + "/dir2").mkdirs();
        new File(project + "/dir2/file2.xml").createNewFile();
        new File(project + "/dir1/dir3").mkdirs();
        new File(project + "/dir1/dir3/file3.txt").createNewFile();
        new File(project + "/dir1/dir3/file4.xml").createNewFile();
        String[] args = {
                "-d",
                project.getAbsolutePath(),
                "-e",
                ".xml", ".txt",
                "-o",
                "test.zip"
        };
        Args keys = new Args(args);
        Zip zip = new Zip();
        zip.pack(keys);
        File[] files = new File(System.getProperty("user.dir")).listFiles(
                file -> file.getName().equals(keys.output())
        );
        assertThat(files.length, is(1));
        assertThat(files[0].exists(), is(true));
        files[0].deleteOnExit();
        recursiveDelete(project);
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