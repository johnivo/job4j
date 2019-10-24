package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 24.10.2019
 */
public class EmailNotification  {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * Отправляет почту через ExecutorService
     *
     * формирует тему и содержание письма
     * добавляет задачу по отправке уведомления в пул и сразу ее выполняет
     *
     * @param user объект пользователь
     */
    public void emailTo(User user) {

        String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());

//        pool.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Execute " + Thread.currentThread().getName());
//                send(subject, body, user.getEmail());
//            }
//        });
        pool.submit(() -> {
            System.out.println("Execute " + Thread.currentThread().getName());
            send(subject, body, user.getEmail());
        });
    }

    /**
     * выполняет отправку письма с указанными параметрами
     * @param subject тема
     * @param body содержание
     * @param email адрес получателя
     */
    public void send(String subject, String body, String email) {
        //do something
        System.out.println("subject: " + subject);
        System.out.println("body: " + body);
        System.out.println("email: " + email);
        System.out.println();
    }

    /**
     * shutdown() упорядоченно завершает работу пула, при этом ранее отправленные задачи выполняются, а новые задачи не принимаются
     * с помощью isTerminated() проверяем, все-ли задачи исполнителя сервиса завершены по команде остановки shutdown()
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EmailNotification notification = new EmailNotification();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                notification.emailTo(new User("user " + i, "user" + i + "@mail.com"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notification.close();
    }

}
