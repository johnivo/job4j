package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.06.2019
 */
public class ParserXML extends DefaultHandler {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    private Long sum = 0L;

    private List<Long> list = new ArrayList();

    /**
     * Парсит файл XML и выводит арифметическую сумму значений всех атрибутов field в консоль.
     * @param source входной файл
     */
    public void parsing(File source) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ParserXML parserXML = new ParserXML();
            saxParser.parse(new FileInputStream(source), parserXML);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void endDocument() {
        for (Long field : list) {
            sum = sum + field;
        }
        System.out.println("Sum of fields attributes is " + sum);
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {
        if ("field".equals(atts.getLocalName(0))) {
            list.add(Long.valueOf(atts.getValue(0)));
        }
    }

    /**
     * Запускает программу на выполнение.
     * Создает структуру в базе и возвращает лист с данными.
     * Генерирует файл формата XML.
     * Преобразовывает файл XML в файл другого XML формата через XSTL
     * Парсит файл XML и выводит арифметическую сумму значений всех атрибутов field в консоль.
     */
    public void start() {
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        List<Entry> list = storeSQL.startSSQL();

        String temp = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
        File source = new File(temp + "source.xml");
        StoreXML storeXML = new StoreXML();
        storeXML.save(list, source);

        File dest = new File(temp + "dest.xml");
        File scheme = new File(temp + "scheme.xslt");
        try {
            Files.copy(ConvertXSLT.class.getClassLoader().getResourceAsStream("scheme.xslt"), scheme.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConvertXSLT convertXSLT = new ConvertXSLT();
        convertXSLT.convert(source, dest, scheme);

        parsing(dest);

    }

    public static void main(String[] args) throws SAXException, IOException {

        //for example

        XMLReader xr = XMLReaderFactory.createXMLReader();
        ParserXML handler = new ParserXML();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);
        String file = "dest2.xml";
        File source = new File(
                System.getProperty("java.io.tmpdir")
                        + System.getProperty("file.separator")
                        + file
        );
        source.createNewFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(source))
        ) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.newLine();
            writer.write("<entries>");
            writer.newLine();
            writer.write("<entry field=\"1\"/>");
            writer.newLine();
            writer.write("<entry field=\"2\"/>");
            writer.newLine();
            writer.write("</entries>");
            writer.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        FileReader r = new FileReader(source);
        xr.parse(new InputSource(r));
    }

}
