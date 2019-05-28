package ru.job4j.io.bot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 27.05.2019
 */
public class ClientTest {

    private static final String LN = System.lineSeparator();

    /**
     * Fills input data and imitates work of client side of bot.
     * Compares output data with expected result.
     * @param input data.
     * @param expected result.
     */
    private void testClient(String input, String expected) {
        Socket socket = mock(Socket.class);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes())
        ) {
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            System.setIn(new ByteArrayInputStream(expected.getBytes()));
            Client client = new Client(socket);
            client.start();
            assertThat(out.toString(), is(expected));
            System.setIn(System.in);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Check when client greetings and says exit.
     */
    @Test
    public void whenSendHalloThenReceivesAlsoGreets() {
        this.testClient(
                String.format("Hello, dear friend, I'm a oracle.%s%s", LN, LN),
                Joiner.on(LN).join(
                        "Hello oracle",
                        "Exit",
                        ""
                )
        );
    }

    /**
     * Check when client greetings, says other phrases and exit.
     */
    @Test
    public void whenSendOtherMessageThenOracleDontUnderstand() {
        this.testClient(
                Joiner.on(LN).join(
                        "Hello, dear friend, I'm a oracle.",
                        "",
                        "I don't understand you.",
                        "",
                        ""
                ),
                Joiner.on(LN).join(
                        "Hello oracle",
                        "How're you?",
                        "Exit",
                        ""
                )
        );
    }
}