package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 28.06.2019
 */
@XmlRootElement
public class Entries {

    private List<Entry> values;

    public Entries() {
    }

    public Entries(List<Entry> values) {
        this.values = values;
    }

    @XmlElement(name = "entry")
    public List<Entry> getValues() {
        return values;
    }

    public void setValues(List<Entry> values) {
        this.values = values;
    }

}
