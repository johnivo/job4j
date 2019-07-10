package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.06.2019
 */
public class ConvertXSLT {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    /**
     * Преобразовывает файл XML в файл другого XML формата через XSTL.
     * @param source входной файл
     * @param dest целевой файл
     * @param scheme описание целевого файла
     */
    public File convert(File source, File dest, File scheme) {

        try {

            TransformerFactory factory = TransformerFactory.newInstance();
            //Source xslt = new StreamSource(scheme);
            Source xslt = new StreamSource(new FileInputStream(scheme));

            Transformer transformer = factory.newTransformer(xslt);
            Source xmlInput = new StreamSource(new FileInputStream(source));

            transformer.transform(xmlInput, new StreamResult(new FileOutputStream(dest)));

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return dest;
    }

}
