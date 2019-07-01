package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {

        XMLReader xr = XMLReaderFactory.createXMLReader();
        ParserXML handler = new ParserXML();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);
        FileReader r = new FileReader(new File("C:/Users/Barclay/AppData/Local/Temp/dest.xml"));
        xr.parse(new InputSource(r));

    }

}
