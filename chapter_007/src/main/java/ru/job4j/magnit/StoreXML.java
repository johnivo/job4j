package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.06.2019
 */
public class StoreXML {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    /**
     * Генерирует файл XML формата из данных базы.
     * @param list список всех записей в базе
     * @param target целевой файл
     */
    public File save(List<Entry> list, File target) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Entries entries = new Entries(list);
            jaxbMarshaller.marshal(entries, target);

        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }

        return target;
    }

}
