### Парсер вакансий

Общее описание.

Приложение парсер должно заходить на сайт sql.ru в раздел работа и собирать Java вакансии.

Ваша задача:

1.. Реализовать модуль сборки анализа данных с sql.ru.

2.. Система должна использовать Jsoup для парсинга страниц.

3.. Система должна запускаться раз в день.

Для этого нужно использовать библиотеку quartz. 
```
<!-- https://mvnrepository.com/artifact/org.quartz-scheduler/quartz -->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.3.0</version>
</dependency>
```
Пример использования - http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-06.html

Для запуска раз в день нужно использовать cron exression - 0 0 12 * * ?

http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html

4.. Система должна собирать данные только про вакансии java. Нужно учесть, что JavaScript не подходит, как и Java Script.

5.. Данные должны храниться в базе данных. 

В базе должна быть таблица vacancy (id, name, text, link)
```
id - первичный ключ
name - имя вакансии
text - текст вакансии
link - текст, ссылка на вакансию
```

6.. Учесть дубликаты. Вакансии с одинаковым именем считаются дубликатами.

7.. Учитывать время последнего запуска. Если это первый запуск, то нужно собрать все объявления с начало года.

8.. В системе не должно быть вывода, либо ввода информации. Все настройки должны быть в файле. app.properties.

https://docs.oracle.com/javase/tutorial/essential/environment/properties.html

В файл app.properties указываем. настройки к базе данных и периодичность запуска приложения.
```
jdbc.driver=..
jdbc.url=...
jdbc.username=...
jdbc.password=...
cron.time=...
```

9.. Для вывода нужной информации использовать логгер log4j.

10.. Пример запуска приложения. 

java -jar SqlRuParser app.properties