package ru.job4j.professions;

public class Doctor extends Profession {
    final Diagnose diagnose = new Diagnose();

    public Diagnose heal(Patient patient) {
        return diagnose;
    }
}
