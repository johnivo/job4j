package ru.job4j.jmm.race;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.10.2019
 */
public class ActionTwo extends Thread {

    public Counter counter;

    public ActionTwo(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.add(100);
        }
    }
}
