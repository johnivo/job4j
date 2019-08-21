package ru.job4j.generator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.08.2019
 */
public class SimpleGenerator implements Template {

    /**
     * Поле с маской для поиска ключей в строке
     * любое количество символов до и после .*
     * для экранируемых символов ${}
     * внутри находятся любое количество любых символов (.+?) - ленивый квантификатор
     */
    private String template = ".*\\$\\{(.+?)\\}.*";

    /**
     * Компилирует строку с маской в шаблон типа Pattern.
     */
    private final Pattern keys = Pattern.compile(template);

    /**
     * В заданной строке заменяет ключи на соответствующие значения из карты.
     *
     * Ппроходим по карте,
     * Создаем объект Matcher, передаем ему входную строку, в которой ищем совпадения с маской.
     * Если сопадение найдено, обновляем входную строку - заменяем все вхождения ключа на соответствующее значение.
     * Добавляем в множество входную строку (для подсчета числа уникальных замен).
     * Проверяем на исключения:
     * если в размер карты больше размера множества temp, то "в карте лишние ключи"
     * если в целевой строке остались необработанные маски, то "в карте не хвататет ключей"
     *
     * @param data строка с исходным текстом.
     * @param db массив пар ключ=значение.
     * @return data отредактированная строка.
     */
    @Override
    public String generate(String data, Map<String, String> db) {
        Set<String> temp = new TreeSet<>();
        for (Map.Entry<String, String> e : db.entrySet()) {
            Matcher matcher = keys.matcher(data);
            if (matcher.find()) {
                String replace = String.format("\\$\\{%s\\}", e.getKey());
                data = data.replaceAll(replace, e.getValue());
                temp.add(matcher.group()); //по умолчанию как .group(0)
            }
        }

        if (temp.size() < db.size()) {
            throw new SimpleGeneratorException("в карте лишние ключи");
        }

        if (data.matches(template)) {
            throw new SimpleGeneratorException("в карте не хвататет ключей");
        }
        return data;
    }

}
