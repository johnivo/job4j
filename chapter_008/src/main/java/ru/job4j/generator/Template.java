package ru.job4j.generator;

import java.util.Map;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.08.2019
 */
public interface Template {

    String generate(String template, Map<String, String> data);

}
