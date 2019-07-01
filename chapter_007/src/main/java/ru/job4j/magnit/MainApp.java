package ru.job4j.magnit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Точка входа в приложение.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 01.07.2019
 */
public class MainApp {

    public static void main(String[] args) throws IOException {

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

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
