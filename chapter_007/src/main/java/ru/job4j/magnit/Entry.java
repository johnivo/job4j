package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 27.06.2019
 */
@XmlRootElement
public class Entry {

    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

}
