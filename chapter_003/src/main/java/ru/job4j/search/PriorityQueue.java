package ru.job4j.search;

import java.util.LinkedList;

/**
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 25.01.2018
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {

        //TODO добавить вставку в связанный список.
        //int i = 0;
        // или var i = 0;
        //for (Task t : tasks) {
        // или for (var t : tasks) {
        //    if (t.getPriority() < task.getPriority()) {
        //        i++;
        //    }
        //}
        var i = (int) tasks.stream()
                .filter(
                        e -> e.getPriority() < task.getPriority()
                ).count();
        this.tasks.add(i, task);
    }

    public Task take() {
        tasks.stream()
                .map(Task::getPriority)
                .forEach(System.out::println);
        return this.tasks.poll();
    }
}
