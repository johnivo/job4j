package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StreamUsageTest {

    @Test
    public void whenUsageFilter() {
        StreamUsage.Task item1 = new StreamUsage.Task("Bug #1", 100);
        StreamUsage.Task item2 = new StreamUsage.Task("Task #2", 100);
        StreamUsage.Task item3 = new StreamUsage.Task("Bug #3", 100);
        ArrayList<StreamUsage.Task> tasks = new ArrayList<>();
        tasks.add(item1);
        tasks.add(item2);
        tasks.add(item3);
        String bug = "Bug";
        StreamUsage tasksFilter = new StreamUsage(bug);
        int result = tasksFilter.usageFilter(tasks, bug).size();
        assertThat(result, is(2));
    }

    @Test
    public void whenUseNameTask() {
        StreamUsage.Task item1 = new StreamUsage.Task("Bug #1", 100);
        StreamUsage.Task item2 = new StreamUsage.Task("Task #2", 100);
        StreamUsage.Task item3 = new StreamUsage.Task("Bug #3", 100);
        ArrayList<StreamUsage.Task> tasks = new ArrayList<>();
        tasks.add(item1);
        tasks.add(item2);
        tasks.add(item3);
        String bug = "Bug";
        StreamUsage tasksFilter = new StreamUsage(bug);
        List<String> result = tasksFilter.namesTasks(tasks);
        List<String> expect = new ArrayList<>();
        expect.addAll(
                Arrays.asList(
                        "Bug #1",
                        "Task #2",
                        "Bug #3"
                )
        );
        assertThat(result, is(expect));
    }

    @Test
    public void whenUseTotalSum() {
        StreamUsage.Task item1 = new StreamUsage.Task("Bug #1", 100);
        StreamUsage.Task item2 = new StreamUsage.Task("Task #2", 100);
        StreamUsage.Task item3 = new StreamUsage.Task("Bug #3", 100);
        ArrayList<StreamUsage.Task> tasks = new ArrayList<>();
        tasks.add(item1);
        tasks.add(item2);
        tasks.add(item3);
        String bug = "Bug";
        StreamUsage tasksFilter = new StreamUsage(bug);
        long result = tasksFilter.totalSum(tasks);
        assertThat(result, is(300L));
    }
}
