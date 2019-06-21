package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Objects;

/**
 * Реализация массива для хранения заявок.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 28.12.2018
 */
public class Item {
    private String name;
    private String id;
    private String desc;
    private long created;
    private String[] comments;

    public Item() {
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Item(String id, String name, String desc, long created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreated() {
        return this.created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String[] getComments() {
        return this.comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return created == item.created
                && Objects.equals(name, item.name)
                && Objects.equals(id, item.id)
                && Objects.equals(desc, item.desc)
                && Arrays.equals(comments, item.comments);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, id, desc, created);
        result = 31 * result + Arrays.hashCode(comments);
        return result;
    }

    @Override
    public String toString() {
        return "Item{"
                + "name='" + name + '\''
                + ", id='" + id + '\''
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", comments=" + Arrays.toString(comments)
                + '}';
    }
}