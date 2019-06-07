package ru.job4j.interview;

import java.util.*;

/**
 * Let's say I have a database of scripts. Each script has an arbitrary number of dependencies.
 * The dependencies are expressed as a list of scriptIds that need to be executed before a given script.
 * There are no circular dependencies. I want to come up with an execution plan so
 * that I can run all of the scripts in a sane order. Below is the script representation.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 06.05.2019
 */
public class DBScripts {

    /**
     * Handles a list of scripts.
     *
     * @param database The database scripts. The key is script id. Values are list of dependencies.
     */
    public void processing(Map<Integer, VulnerabilityScript> database) {
        Set<Integer> processed = new HashSet<>();
        Queue<Integer> data = new LinkedList<>(database.keySet());
        while (!data.isEmpty()) {
            Integer id = data.poll();
            if (!processed.contains(id)) {
                VulnerabilityScript script = database.get(id);
                if (script.getDependencies().stream().allMatch(processed::contains)) {
                    execute(script);
                    processed.add(script.getScriptId());
                } else {
                    data.offer(script.getScriptId());
                    script.getDependencies().forEach(data::offer);
                }
            }
        }
    }

    /**
     * Prints the processed script id to the console.
     *
     * @param script
     */
    private void execute(VulnerabilityScript script) {
        System.out.println("Execute " + script.getScriptId());
    }

}
