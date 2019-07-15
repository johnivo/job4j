package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Класс содержит настройки планировщика (задание, время запуска приложения).
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.07.2019
 */
public class ParserJob implements Job {

    private static final Logger LOG = LogManager.getLogger(ParserJob.class.getName());

    public ParserJob() {
    }

    /**
     * Определяет задание для планировщика.
     * @param context
     */
    @Override
    public void execute(JobExecutionContext context) {

        LOG.info("Parser is run");

        JobDataMap data = context.getJobDetail().getJobDataMap();

        Properties config = (Properties) data.get("config");

        DBService dbs = new DBService();
        dbs.start(config);

        LOG.info("Parsing is completed");

    }

    /**
     * Запускает планировщик на выполнение.
     * Время запуска в триггер считывается из файла настроек.
     */
    public void run() {

        String properties = "app.properties";

        JobDetail parserJob = JobBuilder.newJob(ParserJob.class)
                .withIdentity("parserJob")
                .build();

        try (InputStream in = ParserJob.class.getClassLoader()
                .getResourceAsStream(properties)) {
            Properties config = new Properties();
            config.load(in);
            String cronTime = config.getProperty("cron.time");

            parserJob.getJobDataMap().put("config", config);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger", "SqlRuParser")
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronTime))
                    .forJob("parserJob")
                    .build();

            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(parserJob, trigger);

            scheduler.start();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    public static void main(String[] args) {

        try (InputStream in = ParserJob.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            String cronTime = config.getProperty("cron.time");

            // Запускаем JobDetail с именем задания,
            // группой задания и классом выполняемого задания
            JobDetail parserJob = JobBuilder.newJob(ParserJob.class)
                    .withIdentity("parserJob")
                    .build();
            parserJob.getJobDataMap().put("prop", config);

            // Запускаем CronTrigger с его именем и именем группы
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger", "SqlRuParser")
                    //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(03).repeatForever())
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronTime))
                    .forJob("parserJob")
                    .build();

            // Запускаем Schedule Factory
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            // Извлекаем планировщик из schedule factory
            Scheduler scheduler = schedulerFactory.getScheduler();
            // Планируем задание с помощью JobDetail и Trigger
            scheduler.scheduleJob(parserJob, trigger);
            // Запускаем планировщик
            scheduler.start();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


        //0 30 12 ? * *
        //раз в 2 мин
        //0 0/2 * 1/1 * ? *
        //каждые 10сек
        //0/10 0/1 * 1/1 * ? *


    }

}
