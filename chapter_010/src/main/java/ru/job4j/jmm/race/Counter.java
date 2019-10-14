package ru.job4j.jmm.race;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.10.2019
 */
public class Counter {

    public int count;

    //Два потока используют общий ресурс - один объект типа счетчик и одновременно меняют его состояние
    //Чтобы уйти от состояния гонки, нужно синхронизировать код в критической секции
    //в нашем случае синхронизируем метод экземпляра
    //public synchronized void add(int value) {

    public void add(int value) {
        int temp = count;
        this.count = this.count + value;

        System.out.println(String.format("thread %s: %s + %s = %s", Thread.currentThread().getName(), temp, value, count));

    }

    public static void main(String[] args) {

        Counter counter = new Counter();

        ActionOne one = new ActionOne(counter);
        ActionTwo two = new ActionTwo(counter);

        Thread a = new Thread(new ActionOne(counter), "A");
        Thread b = new Thread(new ActionTwo(counter), "B");
        //new ActionTwo(counter).start();

        a.start();
        b.start();
    }

}
