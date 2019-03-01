package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {

    private String bug;

    public StreamUsage(String bug) {
        this.bug = bug;
    }

    public static class Task {
        private final String name;
        private final long spent;

        public Task(String name, long spent) {
            this.name = name;
            this.spent = spent;
        }

        @Override
        public String toString() {
            return "Task{"
                    +
                    "name='" + name + '\''
                    +
                    ", spent=" + spent
                    +
                    '}';
        }
    }

    public List<Task> usageFilter(ArrayList<Task> tasks, String bug) {
        List<Task> bugs = tasks.stream().filter(
                task -> task.name.contains(bug)
        ).collect(Collectors.toList());
        bugs.forEach(System.out::println);
        return bugs;
    }

    public List<String> namesTasks(ArrayList<Task> tasks) {
        List<String> names = tasks.stream().map(
                task -> task.name
        ).collect(Collectors.toList());
        return names;
    }

    public long totalSum(ArrayList<Task> tasks) {
        long total = tasks.stream().map(
                task -> task.spent
        ).reduce(0L, Long::sum);
        return total;
    }
}
