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
        int i = 0;
        for (Task t : tasks) {
            if (t.getPriority() < task.getPriority()) {
                i++;
            }
        }
        this.tasks.add(i, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
