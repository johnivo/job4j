package ru.job4j.generator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.08.2019
 */
public class TemplateTest {

    @Test
    public void whenUniqueKeysThenTheyAreReplaced() {
        Template template = new SimpleGenerator();
        String data = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("subject", "you");
        String expected = "I am Petr, Who are you?";

        String result = template.generate(data, map);

        Assert.assertThat(result, is(expected));
    }

    @Test
    public void whenOnlySameKeysThenTheyAreReplaced() {
        Template template = new SimpleGenerator();
        String data = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> map = Map.of(
                "sos", "Aaa"
        );
        String expected = "Help, Aaa, Aaa, Aaa";

        String result = template.generate(data, map);

        Assert.assertThat(result, is(expected));
    }

    @Test
    public void whenRepeatedKeysThenTheyAreReplaced() {
        Template template = new SimpleGenerator();
        String data = "Hi, ${name}, ${name}, ${joy}, ${joy}";
        Map<String, String> map = Map.of(
                "name", "Any",
                "joy", "hey"
        );
        String expected = "Hi, Any, Any, hey, hey";

        String result = template.generate(data, map);

        Assert.assertThat(result, is(expected));
    }

    @Test(expected = SimpleGeneratorException.class)
    public void whenNoKeysThenThrowsException() {
        Template template = new SimpleGenerator();
        String data = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Petr"
        );

        String result = template.generate(data, map);
    }

    @Test(expected = SimpleGeneratorException.class)
    public void whenExtraKeysThenThrowsException() {
        Template template = new SimpleGenerator();
        String data = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Petr",
                "subject", "you",
                "subject2", "you"
        );

        String result = template.generate(data, map);
    }
}