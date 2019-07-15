package ru.job4j.parser;

import java.util.Date;
import java.util.Objects;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.07.2019
 */
public class Vacancy {

    private String name;
    private String desc;
    private String link;
    private Date created;

    public Vacancy() {
    }

    public Vacancy(String name, String desc, String link) {
        this.name = name;
        this.desc = desc;
        this.link = link;
    }

    public Vacancy(String name, String desc, String link, Date created) {
        this.name = name;
        this.desc = desc;
        this.link = link;
        this.created = created;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name)
                && Objects.equals(desc, vacancy.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, link, created);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", link='" + link + '\''
                + ", created=" + created
                + '}';
    }
}
