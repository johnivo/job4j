package ru.job4j.io.chat;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.05.2019
 */
public class ConsoleChatTest {

    /**
     * Checking receiving a response from a given file.
     * @throws IOException possible.
     */
    @Test
    public void whenTestingReceivingAnswersFromGivenFile() {
        String address = getClass().getClassLoader().getResource("lexicon.txt").getFile();
        Answer chat = new Answer(address);
        String result = chat.answer();
        List<String> answers = chat.answersList();
        assertThat(answers.contains(result), is(true));
    }

    /**
     * Checking the receipt of the response from the newly created file.
     * @throws IOException possible.
     */
    @Test
    public void whenTestingReceivingAnswersFromCreatedFile() throws IOException {
        File source = data(
                "lexicon.txt",
                "Hello!",
                "Bye!",
                "Really.",
                "It's fine."
        );
        Answer chat = new Answer(source.getAbsolutePath());
        String result = chat.answer();
        System.out.println(result);
        System.out.println();
        List<String> answers = chat.answersList();
        for (String r : answers) {
            System.out.println(r);
        }
        assertThat(answers.contains(result), is(true));
        source.deleteOnExit();
    }

    /**
     * Checking chat (testing stop-command and writing to log).
     * @throws IOException possible.
     */
    @Test
    public void whenUserWriteStopThenChatOffs() throws IOException {
        String address = getClass().getClassLoader().getResource("lexicon.txt").getFile();
        File target = data(
                "chat.log",
                ""
        );
        final String LN = System.lineSeparator();
        String words = String.format("привет%sджава%sзакончить", LN, LN);
        try (ByteArrayInputStream bin = new ByteArrayInputStream(words.getBytes());
             BufferedReader file = new BufferedReader(new FileReader(target))
        ) {
            ConsoleChat chat = new ConsoleChat(address, bin);
            chat.launch(target.getAbsolutePath());
            List<String> rst = new ArrayList<>();
            file.lines().forEach(rst::add);
            assertThat(rst.size(), is(6));
        }
        target.deleteOnExit();
    }

    /**
     * Checking chat (testing pause, continue and stop-commands and writing to log).
     * @throws IOException possible.
     */
    @Test
    public void whenUserWritePauseContinueAndStopThenChatStopsTurnsOnAndOff() throws IOException {
        File source = data(
                "lexicon.txt",
                "Hello",
                "Hello"
        );
        File target = data(
                "chat.log",
                ""
        );
        final String LS = System.lineSeparator();
        String words = String.format("начало%sстоп%sбот молчит%sпродолжить%sзакончить", LS, LS, LS, LS);
        try (ByteArrayInputStream bin = new ByteArrayInputStream(words.getBytes());
                BufferedReader file = new BufferedReader(new FileReader(target))
        ) {
            ConsoleChat chat = new ConsoleChat(source.getAbsolutePath(), bin);
            chat.launch2(target.getAbsolutePath());
            StringBuilder content = new StringBuilder();
            file.lines().forEach(
                    line -> content.append(line).append(LS)
            );
            String expected = new StringBuilder("user: начало").append(LS)
                    .append("bot: Hello").append(LS)
                    .append("user: стоп").append(LS)
                    .append("user: бот молчит").append(LS)
                    .append("user: продолжить").append(LS)
                    .append("bot: Hello").append(LS)
                    .append("user: закончить").append(LS)
                    .toString();
            assertThat(content.toString(), is(expected));
        }
        source.deleteOnExit();
        target.deleteOnExit();
    }

    /**
     * Checking chat (testing pause, continue and stop-commands and writing to log).
     * @throws IOException possible.
     */
    @Test
    public void whenUserWritePauseContinueAndStopThenChatStopsTurnsOnAndOff2() throws IOException {
        String address = getClass().getClassLoader().getResource("lexicon.txt").getFile();
        File target = data(
                "chat.log",
                ""
        );
        final String LN = System.lineSeparator();
        String words = String.format("один%sдва%sстоп%sтри%sпродолжить%sзакончить", LN, LN, LN, LN, LN);
        try (ByteArrayInputStream bin = new ByteArrayInputStream(words.getBytes());
             BufferedReader file = new BufferedReader(new FileReader(target))
        ) {
            ConsoleChat chat = new ConsoleChat(address, bin);
            chat.launch(target.getAbsolutePath());
            List<String> rst = new ArrayList<>();
            file.lines().forEach(rst::add);
            assertThat(rst.size(), is(10));
        }
        target.deleteOnExit();
    }

    /**
     * Fill input data.
     * @param file properties file.
     * @param properties data, TYPE date.
     * @return file.
     * @throws IOException possible.
     */
    private File data(String file, String... properties) throws IOException {
        File path = new File(
                System.getProperty("java.io.tmpdir")
                        + System.getProperty("file.separator")
                        + file
        );
        if (!path.createNewFile()) {
            throw new IllegalStateException(String.format("File could not created %s", path.getAbsoluteFile()));
        }
        try (final PrintWriter store = new PrintWriter(path)) {
            Stream.of(properties).forEach(store::println);
        }
        return path;
    }

}