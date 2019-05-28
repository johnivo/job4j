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
public class ServerTest {

    private static final String LN = System.lineSeparator();

    /**
     * Fills input data and imitates work of server side of bot.
     * Compares output data with expected result.
     * @param input data.
     * @param expected result.
     */
    private void testServer(String input, String expected) {
        Socket socket = mock(Socket.class);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes())
        ) {
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            Server server = new Server(socket);
            server.start();
            assertThat(out.toString(), is(expected));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Check exit operation.
     */
    @Test
    public void whenAskExitMessageThenServerTurnOff() {
        this.testServer("Exit", "");
    }

    /**
     * Check server response to greeting and exit.
     */
    @Test
    public void whenAskHelloThenOracleAlsoGreets() {
        this.testServer(
                String.format("Hello oracle%sExit", LN),
                String.format("Hello, dear friend, I'm a oracle.%s%s", LN, LN)
        );
    }

    /**
     * Check server response to other phrases and exit.
     */
    @Test
    public void whenUnsupportedAskThenOracleDontUnderstand() {
        this.testServer(
                Joiner.on(LN).join(
                        "Unsupported ask",
                        "Exit"
                ),
                Joiner.on(LN).join("I don't understand you.", "", "")
        );
    }
}