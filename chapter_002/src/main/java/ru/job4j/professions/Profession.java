package ru.job4j.professions;

public class Profession {
    public String name;
    public String profession;

    public Profession() {
    }

    public Profession(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return this.name;
    }

    public String getProfession() {
        return this.profession;
    }
}
