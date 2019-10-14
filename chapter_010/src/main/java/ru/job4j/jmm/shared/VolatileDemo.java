package ru.job4j.jmm.shared;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.10.2019
 */
public class VolatileDemo extends Thread {

    //использование volatile гарантирует видимость переменной для всех потоков
    //как только мы используем volatile вечный цикл завершится, будет использовано значение флага из основной памяти
    //volatile boolean keepRunning = true;
    boolean keepRunning = true;

    @Override
    public void run() {
        long count = 0;
        while (keepRunning) {
            count++;
        }
        System.out.println("Thread terminated. " + count);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();
        demo.start();
        Thread.sleep(1000);
        System.out.println("after sleeping in main");
        demo.keepRunning = false;
        demo.join();
        System.out.println("keepRunning set to " + demo.keepRunning);
    }

}
