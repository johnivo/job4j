package ru.job4j.interview;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 06.05.2019
 */
public class DBScriptsTest {

    private static final String LN = System.lineSeparator();

    @Test
    public void whenProcessingScriptsWithSpecifiedDependenciesThenExecutionOrderIs45231() {
        final VulnerabilityScript script1 = new VulnerabilityScript(1, Arrays.asList(2, 3));
        final VulnerabilityScript script2 = new VulnerabilityScript(2, Arrays.asList(4));
        final VulnerabilityScript script3 = new VulnerabilityScript(3, Arrays.asList(4, 5));
        final VulnerabilityScript script4 = new VulnerabilityScript(4, Arrays.asList());
        final VulnerabilityScript script5 = new VulnerabilityScript(5, Arrays.asList());
        final Map<Integer, VulnerabilityScript> database = Stream.of(script1, script2, script3, script4, script5)
                .collect(Collectors.toMap(VulnerabilityScript::getScriptId, Function.identity()));
        DBScripts dbs = new DBScripts();
        String expected = Joiner.on(LN).join(
                "Execute 4",
                "Execute 5",
                "Execute 2",
                "Execute 3",
                "Execute 1",
                ""
        );
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(out);
        ) {
            System.setOut(ps);
            dbs.processing(database);
            assertThat(out.toString(), is(expected));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    @Test
    public void whenProcessingScriptsWithSpecifiedDependenciesThenExecutionOrderIs612453() {
        final VulnerabilityScript script1 = new VulnerabilityScript(1, List.of(6));
        final VulnerabilityScript script2 = new VulnerabilityScript(2, List.of(1));
        final VulnerabilityScript script3 = new VulnerabilityScript(3, List.of(1, 5));
        final VulnerabilityScript script4 = new VulnerabilityScript(4, List.of(1, 2));
        final VulnerabilityScript script5 = new VulnerabilityScript(5, List.of(2, 4));
        final VulnerabilityScript script6 = new VulnerabilityScript(6, List.of());
        final Map<Integer, VulnerabilityScript> database = Stream.of(script1, script2, script3, script4, script5, script6)
                .collect(Collectors.toMap(VulnerabilityScript::getScriptId, s -> s));
        for (Map.Entry entry : database.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        DBScripts dbs = new DBScripts();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(out);
        ) {
            System.setOut(ps);
            dbs.processing(database);
            String expected = new StringBuilder("Execute 6").append(LN)
                    .append("Execute 1").append(LN)
                    .append("Execute 2").append(LN)
                    .append("Execute 4").append(LN)
                    .append("Execute 5").append(LN)
                    .append("Execute 3").append(LN)
                    .toString();
            assertThat(out.toString(), is(expected));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Test
    public void whenProcessingScriptsWithSpecifiedDependenciesThenExecutionOrderIs453210() {
        VulnerabilityScript script0 = new VulnerabilityScript(0, List.of(1, 2));
        VulnerabilityScript script1 = new VulnerabilityScript(1, List.of(2));
        VulnerabilityScript script2 = new VulnerabilityScript(2, List.of(3));
        VulnerabilityScript script3 = new VulnerabilityScript(3, List.of(4, 5));
        VulnerabilityScript script4 = new VulnerabilityScript(4, List.of());
        VulnerabilityScript script5 = new VulnerabilityScript(5, List.of());
        Map<Integer, VulnerabilityScript> scriptsDB = new HashMap<>();
        scriptsDB.put(0, script0);
        scriptsDB.put(1, script1);
        scriptsDB.put(2, script2);
        scriptsDB.put(3, script3);
        scriptsDB.put(4, script4);
        scriptsDB.put(5, script5);
        for (Map.Entry entry : scriptsDB.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        DBScripts dbs = new DBScripts();
        dbs.processing(scriptsDB);
    }
}