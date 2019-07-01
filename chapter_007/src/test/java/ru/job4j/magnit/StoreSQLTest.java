package ru.job4j.magnit;

import com.google.common.base.Joiner;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 28.06.2019
 */
public class StoreSQLTest {

    private static final String LN = System.lineSeparator();

    @Ignore
    @Test
    public void whenGenerate10EntriesThenReturned10Entries() {
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.generate(10);
            List<Entry> list = sql.load();
            assertThat(list.size(), is(10));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Ignore
    @Test
    public void whenGenerateXMLFromDatabaseDataThenGetXMFile() {
        Config config = new Config();
        config.init();
        StoreXML test = new StoreXML();

        try (StoreSQL sql = new StoreSQL(config)) {

            sql.generate(2);
            List<Entry> list = sql.load();

            String temp = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
            File target = new File(temp + "target.xml");

            test.save(list, target);

            StringBuilder content = new StringBuilder();
            try (BufferedReader file = new BufferedReader(new FileReader(
                    target
            ))) {
                file.lines().forEach(
                        line -> content.append(line).append(System.lineSeparator())
                );
            }
            assertThat(
                    content.toString(),
                    is(
                            Joiner.on(LN).join(
                                    "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                                    "<entries>",
                                    "    <entry>",
                                    "        <field>1</field>",
                                    "    </entry>",
                                    "    <entry>",
                                    "        <field>2</field>",
                                    "    </entry>",
                                    "</entries>",
                                    ""
                            )
                    )
            );
            recursiveDelete(target);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Ignore
    @Test
    public void whenTestConvertXSLT() throws IOException {

        Config config = new Config();
        config.init();

        StoreXML storeXML = new StoreXML();
        ConvertXSLT convertXSLT = new ConvertXSLT();

        String temp = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
        File source = new File(temp + "source.xml");
        File dest = new File(temp + "dest.xml");
        File scheme = new File(temp + "scheme.xslt");
        Files.copy(ConvertXSLT.class.getClassLoader().getResourceAsStream("scheme.xslt"), scheme.toPath(), StandardCopyOption.REPLACE_EXISTING);

        try (StoreSQL sql = new StoreSQL(config)) {

            sql.generate(2);
            List<Entry> list = sql.load();
            storeXML.save(list, source);
            convertXSLT.convert(source, dest, scheme);

            StringBuilder content = new StringBuilder();
            try (BufferedReader file = new BufferedReader(new FileReader(
                    dest
            ))) {
                file.lines().forEach(
                        line -> content.append(line).append(System.lineSeparator())
                );
            }
            assertThat(
                    content.toString(),
                    is(
                            Joiner.on(LN).join(
                                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
                                    "<entries>",
                                    "<entry field=\"1\"/>",
                                    "<entry field=\"2\"/>",
                                    "</entries>",
                                    ""
                            )
                    )
            );
            recursiveDelete(dest);
            recursiveDelete(source);
            recursiveDelete(scheme);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Ignore
    @Test
    public void whenTestParserXML() throws IOException {

        Config config = new Config();
        config.init();

        StoreXML storeXML = new StoreXML();
        ConvertXSLT convertXSLT = new ConvertXSLT();

        String temp = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
        File source = new File(temp + "source.xml");
        File dest = new File(temp + "dest.xml");
        File scheme = new File(temp + "scheme.xslt");
        Files.copy(ConvertXSLT.class.getClassLoader().getResourceAsStream("scheme.xslt"), scheme.toPath(), StandardCopyOption.REPLACE_EXISTING);

        ParserXML parserXML = new ParserXML();

        try (StoreSQL sql = new StoreSQL(config);) {

            sql.generate(1000000);
            List<Entry> list = sql.load();
            storeXML.save(list, source);
            convertXSLT.convert(source, dest, scheme);

            parserXML.parsing(dest);
            //assertThat(list.size(), is(3));
            recursiveDelete(dest);
            recursiveDelete(source);
            recursiveDelete(scheme);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    /**
     * Delete data.
     *
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