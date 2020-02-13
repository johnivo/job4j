## Hibernate

[1. Что такое ORM?](#1-Что-такое-ORM)

[2. Опиши, как конфигурируется Hibernate. Рассказать про hibernate.cfg.xml и про mapping.](#2-Опиши-как-конфигурируется-Hibernate-Рассказать-про-hibernatecfgxml-и-про-mapping)

[3. Жизненный цикл Entity?](#3-Жизненный-цикл-Entity)

[4. Зачем нужен класс SessionFactory? Является ли он потокобезопасным?](#4-Зачем-нужен-класс-SessionFactory-Является-ли-он-потокобезопасным)

[5. Зачем нужен класс Session? Является ли он потокобезопасным?](#5-Зачем-нужен-класс-Session-Является-ли-он-потокобезопасным)

[6. В чем отличие методов Session.get Session.load?](#6-В-чем-отличие-методов-Sessionget-Sessionload)

[7. Расскажите про методы flush commit.](#7-Расскажите-про-методы-flush-commit)

[8. В чем отличие метода save от saveOrUpdate и merge?](#8-В-чем-отличие-метода-save-от-saveOrUpdate-и-merge)

[9. Расскажите процесс создания, редактирования, чтения и удаления данных через Hibernate.](#9-Расскажите-процесс-создания-редактирования-чтения-и-удаления-данных-через-Hibernate)

[10. Как осуществляется иерархия наследования в Hibernate? Рассказать про три стратегии наследования.](#10-Как-осуществляется-иерархия-наследования-в-Hibernate-Рассказать-про-три-стратегии-наследования)

[11. Можно ли создать собственный тип данных?](#11-Можно-ли-создать-собственный-тип-данных)

[12. Какие коллекции поддерживаются на уровне mapping?](#12-Какие-коллекции-поддерживаются-на-уровне-mapping)

[13. Зачем нужен класс Transactional?](#13-Зачем-нужен-класс-Transactional)

[14. Расскажите про уровни изоляции? Какие уровни поддерживаются в hibernate? Как их устанавливать?](#14-Расскажите-про-уровни-изоляции-Какие-уровни-поддерживаются-в-hibernate-Как-их-устанавливать)

[15. Что такое OplimisticLock? Расскажите стратегии создания через version, timestamp.](#15-Что-такое-OplimisticLock-Расскажите-стратегии-создания-через-version-timestamp)

[16. Расскажите про стратегии извлечения данных urgy, lazy?](#16-Расскажите-про-стратегии-извлечения-данных-urgy-lazy)

[17. Что такое объект Proxy? С чем связана ошибка LazyInitializationException? Как ее избежать?](#17-Что-такое-объект-Proxy-С-чем-связана-ошибка-LazyInitializationException-Как-ее-избежать)

[18. HQL. Расскажи основные элементы синтаксиса HQL? Простой запрос, запрос join? Создания объекта через конструтор.](#18-HQL-Расскажи-основные-элементы-синтаксиса-HQL-Простой-запрос-запрос-join-Создания-объекта-через-конструтор)

[19. Расскажите про уровни кешей в hibernate?](#19-Расскажите-про-уровни-кешей-в-hibernate)

[20. Что такое StatelessSessionFactory? Зачем он нужен, где он используется?](#20-Что-такое-StatelessSessionFactory-Зачем-он-нужен-где-он-используется)

[21. Зачем нужен решим read-only?](#21-Зачем-нужен-решим-read-only)

## 1. Что такое ORM?

ORM (Object-Relational Mapping - объектно-реляционное отображение или преобразование) — технология программирования, 
которая связывает базы данных с концепциями объектно-ориентированных языков программирования, 
создавая «виртуальную объектную базу данных»

[к оглавлению](#Hibernate)

## 2. Опиши как конфигурируется Hibernate Рассказать про hibernatecfgxml и про mapping?

Существует четыре способа конфигурации работы с Hibernate:
+ используя аннотации;
+ hibernate.cfg.xml;
+ hibernate.properties;
+ persistence.xml.

Самый частый способ конфигурации : через аннотации и файл persistence.xml, что касается файлов hibernate.properties 
и hibernate.cfg.xml, то hibernate.cfg.xml главнее (если в приложение есть оба файла, то принимаются настройки из файла 
hibernate.cfg.xml). Конфигурация аннотациями, хоть и удобна, но не всегда возможна, к примеру, если для разных баз 
данных или для разных ситуаций вы хотите иметь разные конфигурацию сущностей, то следует использовать xml файлы конфигураций.

Файл конфигурации Hibernate содержит в себе данные о базе данных и необходим для инициализации SessionFactory. 
В .xml файле необходимо указать вендора базы данных или JNDI ресурсы, а так же информацию об используемом диалекте, 
что поможет hibernate выбрать режим работы с конкретной базой данных.

Файл отображения (mapping file) используется для связи entity бинов и колонок в таблице базы данных. 
В случаях, когда не используются аннотации JPA, файл отображения .xml может быть полезен 
(например при использовании сторонних библиотек).

[к оглавлению](#Hibernate)

## 3. Жизненный цикл Entity?

+ *Transient:* состояние, при котором объект никогда не был связан с какой-либо сессией и не является персистентностью. 
Этот объект находится во временном состоянии. Объект в этом состоянии может стать персистентным при вызове метода save(), 
persist() или saveOrUpdate(). Объект персистентности может перейти в transient состоянии после вызова метода delete().
+ *Persistent:* когда объект связан с уникальной сессией он находится в состоянии persistent (персистентности). 
Любой экземпляр, возвращаемый методами get() или load() находится в состоянии persistent.
+ *Detached:* если объект был персистентным, но сейчас не связан с какой-либо сессией, 
то он находится в отвязанном (detached) состоянии. 
Такой объект можно сделать персистентным используя методы update(), saveOrUpdate(), lock() или replicate(). 
Состояния transient или detached так же могут перейти в состояние persistent как новый объект персистентности 
после вызова метода merge().

[к оглавлению](#Hibernate)

## 4. Зачем нужен класс SessionFactory Является ли он потокобезопасным?

Именно из объекта SessionFactory мы получаем объекты типа Session. На все приложение существует только одна 
SessionFactory и она инициализируеться вместе со стартом приложения. SessionFactory кэширует мета-дату и SQL запросы, 
которые часто используются приложением во время работы. Так же оно кэширует информацию, 
которая была получена в одной из транзакций и может быть использована и в других транзакциях.

Обьект SessionFactory можно получить следующим обращением:
```java
SessionFactory sessionFactory = configuration.buildSessionFactory();
```

Т.к. объект SessionFactory immutable (неизменяемый), то да, он потокобезопасный. 
Множество потоков может обращаться к одному объекту одновременно.

[к оглавлению](#Hibernate)

## 5. Зачем нужен класс Session Является ли он потокобезопасным?

Session — это основной интерфейс, который отвечает за связь с базой данных. Так же, он помогает создавать объекты 
запросов для получение персистентных объектов. (персистентный объект — объект который уже находится в базе данных; 
объект запроса — объект который получается когда мы получаем результат запроса в базу данных, 
именно с ним работает приложение). 

Обьект Session можно получить из SessionFactory:
```java
Session session = sessionFactory.openSession();
```

Роль интерфейса Session:
+ является оберткой для jdbc подключения к базе данных; 
+ является фабрикой для транзакций (согласно официальной документации transaction — аllows the application to define units of work, 
что , по сути, означает что транзакция определяет границы операций связанных с базой данных).
+ является хранителем обязательного кэша первого уровня.

Жизненный цикл объекта session связан с началом и окончанием транзакции. 
Этот объект предоставляет методы для CRUD (create, read, update, delete) операций для объекта персистентности. 
С помощью этого экземпляра можно выполнять HQL, SQL запросы и задавать критерии выборки.

Объект Hibernate Session не является потокобезопасным. 
Каждый поток должен иметь свой собственный объект Session и закрывать его по окончанию.

[к оглавлению](#Hibernate)

## 6. В чем отличие методов Sessionget Sessionload?

Hibernate session обладает различными методами для загрузки данных из базы данных. 
Наиболее часто используемые методы для этого — get() и load().

+ get() загружает данные сразу при вызове, в то время как load() использует прокси объект и загружает данные только тогда, 
когда это требуется на самом деле. В этом плане load() имеет преимущество в плане ленивой загрузки данных.
+ load() бросает исключение, когда данные не найдены. 
Поэтому его нужно использовать только при уверенности в существовании данных.
+ Нужно использовать метод get(), если необходимо удостовериться в наличии данных в БД.
+ В случае обращение к несуществующему объекту, метод get(); вернет null. 
В случае нахождения объект, метод get(); вернет сам объект и запрос в базу данных будет произведен немедленно.

[к оглавлению](#Hibernate)

## 7. Расскажите про методы flush commit?

+ flush() синхронизирует вашу базу данных с текущим состоянием объекта/объектов, хранящихся в памяти, 
но не совершает транзакцию. 
+ если вы получите какое-либо исключение после вызова flush(), то транзакция будет отменена. 
+ Вы можете синхронизировать свою базу данных с небольшими фрагментами данных, используя flush(), вместо того, 
чтобы делать большие данные одновременно с помощью commit() и столкнуться с риском получить Исключение из памяти.
+ commit() сделает данные, хранящиеся в базе данных постоянными. 
Вы не можете отменить свою транзакцию после успешного завершения commit()

[к оглавлению](#Hibernate)

## 8. В чем отличие метода save от saveOrUpdate и merge?

+ Hibernate save() используется для сохранения сущности в базу данных. 
Проблема с использованием метода save() заключается в том, что он может быть вызван без транзакции. 
А следовательно если у нас имеется отображение нескольких объектов, то только первичный объект будет сохранен и 
мы получим несогласованные данные. 
Также save() немедленно возвращает сгенерированный идентификатор.

+ Hibernate persist() аналогичен save() с транзакцией. persist() не возвращает сгенерированный идентификатор сразу.

+ Hibernate saveOrUpdate() использует запрос для вставки или обновления, основываясь на предоставленных данных. 
Если данные уже присутствуют в базе данных, то будет выполнен запрос обновления. 
Метод saveOrUpdate() можно применять без транзакции, но это может привести к аналогичным проблемам, 
как и в случае с методом save().

+ Hibernate merge() может быть использован для обновления существующих значений, 
однако этот метод создает копию из переданного объекта сущности и возвращает его. 
Возвращаемый объект является частью контекста персистентности и отслеживает любые изменения, 
а переданный объект не отслеживается.

[к оглавлению](#Hibernate)

## 9. Расскажите процесс создания редактирования чтения и удаления данных через Hibernate?



[к оглавлению](#Hibernate)

## 10. Как осуществляется иерархия наследования в Hibernate Рассказать про три стратегии наследования?

SQL не понимает наследование типов и не поддерживает его.

Всего таких стратегий 4:
+ Использовать одну таблицу для каждого класса и полиморфное поведение по умолчанию.
+ Одна таблица для каждого конкретного класса, с полным исключением полиморфизма и отношений 
наследования из схемы SQL (для полиморфного поведения во время выполнения будут использоваться UNION-запросы)
+ Единая таблица для всей иерархии классов. Возможна только за счет денормализации схемы SQL. 
Определять суперкласс и подклассы будет возможно посредством различия строк.
+ Одна таблица для каждого подкласса, где отношение “is a” представлено в виде «has a», 
т.е. – связь по внешнему ключу с использованием JOIN.

[https://habr.com/ru/post/337488/](https://habr.com/ru/post/337488/)

[к оглавлению](#Hibernate)

## 11. Можно ли создать собственный тип данных?

[Пользовательские типы в Hibernate](https://easyjava.ru/data/hibernate/polzovatelskie-tipy-v-hibernate/)

[к оглавлению](#Hibernate)

## 12. Какие коллекции поддерживаются на уровне mapping?

+ Bag
+ Set
+ List
+ Array
+ Map

Своей реализации тип коллекции Bag очень напоминает Set, разница состоит в том, что Bag может хранить повторяющиеся значения. 
Bag хранит непроиндексированный список элементов. Большинство таблиц в базе данных имеют индексы отображающие положение 
элемента данных один относительно другого, данные индексы имеют представление в таблице в виде отдельной колонки. 
При объектно-реляционном маппинге, значения колонки индексов мапится на индекс в Array, на индекс в List или на key в Map. 
Если вам надо получить коллекцию объектов не содержащих данные индексы, то вы можете воспользоваться коллекциями типа Bag или Set 
(коллекции содержат данные в неотсортированном виде, но могут быть отсортированы согласно запросу).

[к оглавлению](#Hibernate)

## 13. Зачем нужен класс Transactional?

*Transaction (org.hibernate.Transaction)* — однопоточный короткоживущий объект, используемый для атомарных операций. 
Это абстракция приложения от основных JDBC или JTA транзакций. org.hibernate.Session может занимать 
несколько org.hibernate.Transaction в определенных случаях.

Вместо вызовов session.openTransaction() и session.commit() используется аннотация @Transactional

[Hibernate. Основные принципы работы с сессиями и транзакциями](https://habr.com/ru/post/271115/)

[к оглавлению](#Hibernate)

## 14. Расскажите про уровни изоляции Какие уровни поддерживаются в hibernate Как их устанавливать?

They're 4 mains transaction's isolation levels:
+ read uncommited - imagine two transactions, 'A' and 'B'. 
First, 'A' writes a data into one table without commiting the transaction. 
After, 'B' reads the uncommited data and work on it. 
But some error occurs on commiting the 'A' transaction and all changes are rollbacked. 
In this case, 'B' continues to work on uncommited data by the 'A' transaction. This mode is very fast but can introduce 
a lot of data consistency problems.
+ read commited - we still use the same scenario as for read uncommited, but commited data is locked. 
It means that 'B' can't see uncommited data from the 'A' transaction. 'B' can see it only when 'A' will commit its transaction.
+ repeatable read - this isolation level promotes the same data read, even if the data was changed meanwhile. 
We continue to work with our 'A' and 'B' transactions. First, 'B' makes a SELECT query and lock selected rows. 
After, 'A' makes an INSERT query. 'B' executes a new SELECT query with the same conditions as the first one. 
'B' will now see the same results as previously (the second SELECT must be made under the same transaction as the first one).
+ serializable - this level occurs when our 'B' transaction reads the data and lock whole data's table.
 It means that another transaction can't modify the data on this table. 
 Unlike read uncommited, this way is the most secure. But in the other hand, it's also the slowest solution.

Hibernate starts the transactions by calling getTransaction() (JPA's implementation) or beginTransaction() (Hibernate's Session) methods. 
According to used persistence mechanism, a transaction can be an instance of javax.persistence.EntityTransaction (for JPA) or org.hibernate.Transaction (for Hibernate's Session). 
Both transaction are begun with begin() method, rollbacked thanks to rollback() one and commited through commit() invocation.

To configure transactions isolation level in Hibernate, we need to change the property called *hibernate.connection.isolation*. 
This property can take one from following entries: 
+ 1 (read uncommited) 
+ 2 (read commited) 
+ 4 (repeatable read) 
+ 8 (serializable)

Normally, the isolation level is set at java.sql.Connection level, through setTransactionIsolation(int level) method. 
Level passed in parameter should be one from Connection's constants:

+ Connection.TRANSACTION_READ_UNCOMMITTED, 
+ Connection.TRANSACTION_READ_COMMITTED, 
+ Connection.TRANSACTION_REPEATABLE_READ 
+ Connection.TRANSACTION_SERIALIZABLE.

[к оглавлению](#Hibernate)

## 15. Что такое OplimisticLock Расскажите стратегии создания через version timestamp?

it's crucial to manage concurrent access to a database properly. 
We should be able to handle multiple transactions in an effective and most importantly, error-proof way.
To achieve that we can use optimistic locking mechanism.

In order to use optimistic locking, *we need to have an entity including a property with @Version annotation.* 
While using it, each transaction that reads data holds the value of the version property.
Before the transaction wants to make an update, it checks the version property again.
If the value has changed in the meantime an OptimisticLockException is thrown. 
Otherwise, the transaction commits the update and increments a value version property.

As we've said before, *optimistic locking is based on detecting changes on entities by checking their version attribute*. 
If any concurrent update takes place, OptmisticLockException occurs. After that, we can retry updating the data.

We can imagine that this mechanism is suitable for applications which do much more reads than updates or deletes. 
What is more, it's useful in situations where entities must be detached for some time and locks cannot be held.

*On the contrary, pessimistic locking mechanism involves locking entities on the database level.*

Each transaction can acquire a lock on data. As long as it holds the lock, no transaction can read, 
delete or make any updates on the locked data. We can presume that using pessimistic locking may result in deadlocks. 
However, it ensures greater integrity of data than optimistic locking.

Version attributes are properties with @Version annotation. *They are necessary for enabling optimistic locking.*

[https://www.baeldung.com/jpa-optimistic-locking](https://www.baeldung.com/jpa-optimistic-locking) 

[к оглавлению](#Hibernate)

## 16. Расскажите про стратегии извлечения данных urgy lazy?

+ Eager Loading is a design pattern in which data initialization occurs on the spot. Загружаются все данные по цепочке.
+ Lazy Loading is a design pattern which is used to defer initialization of an object as long as it's possible. 
Данные подгружаются при обращении.

[к оглавлению](#Hibernate)

## 17. Что такое объект Proxy С чем связана ошибка LazyInitializationException Как ее избежать?

Hibernate использует прокси объект для поддержки отложенной загрузки. 
Обычно при загрузке данных из таблицы Hibernate не загружает все отображенные (замаппинные) объекты. 
Как только вы ссылаетесь на дочерний объект или ищите объект с помощью геттера, если связанная сущность не находиться 
в кэше сессии, то прокси код перейдет к базе данных для загрузки связанной сущности. 
Для этого используется javassist, чтобы эффективно и динамически создавать реализации подклассов ваших entity объектов.

Hibernate поддерживает ленивую инициализацию используя proxy объекты и выполняет запросы к базе данных только 
по необходимости.

fetch = FetchType.LAZY это значит, что хибернейт не будет инициализировать эти поля пока вы к ним не обратитесь. 
Но т.к. вы обращаетесь к этим полям за пределами транзакционных методов, он не может это сделать и выкидывает ошибку.
Чтобы этого избежать надо, что метод, который обращается к этим полям был с аннотацей Transactional

Или как предложили в комментариях: Hibernate.initialize(owner.getBooks());

Это хак, но он заставит хибернейт инициировать коллекцию. НО! 
Возможно это не всегда надо и тогда надо выбрать первый вариант и отталкиваться от здравого смысла, смотреть, 
где надо навешивать аннотацию, а где нет.

[к оглавлению](#Hibernate)

## 18. HQL Расскажи основные элементы синтаксиса HQL Простой запрос запрос join Создания объекта через конструтор

Hibernate Framework поставляется с мощным объектно-ориентированным языком запросов — Hibernate Query Language (HQL). 
Он очень похож на SQL, за исключением, что в нем используются объекты вместо имен таблиц, что делает язык ближе к объектно-ориентированному программированию.

HQL является регистронезависимым, кроме использования в запросах имен java переменных и классов, где он подчиняется правилам Java. 
Например, SelECt то же самое, что и select, но ru.javastudy.MyClass отличен от  ru.javastudy.MyCLASS. Запросы HQL кэшируются (это как плюс так и минус).

For example, if we have these two queries:
```sql
FROM Employee emp
JOIN emp.department dep

and

FROM Employee emp
JOIN FETCH emp.department dep
```

In this two queries, you are using JOIN to query all employees that have at least one department associated.
But, the difference is: in the first query you are returning only the Employes for the Hibernate. 
In the second query, you are returning the Employes and all Departments associated.
So, if you use the second query, you will not need to do a new query to hit the database again to see the Departments of each Employee.
You can use the second query when you are sure that you will need the Department of each Employee. 
If you not need the Department, use the first query.

[к оглавлению](#Hibernate)

## 19. Расскажите про уровни кешей в hibernate?

Hibernate использует кэширование, чтобы сделать наше приложение быстрее. 
Кэш Hibernate может быть очень полезным в получении высокой производительности приложения при правильном использовании. 
Идея кэширования заключается в сокращении количества запросов к базе данных.

Кэш первого уровня Hibernate связан с объектом Session. 
Кэш первого уровня у Hibernate  включен по умолчанию и не существует никакого способа, чтобы его отключить. 
Однако Hibernate предоставляет методы, с помощью которых мы можем удалить выбранные объекты из кэша или полностью очистить кэш.
Любой объект закэшированный в session не будет виден другим объектам session. После закрытия объекта сессии все кэшированные 
объекты будут потеряны.

[к оглавлению](#Hibernate)

## 20. Что такое StatelessSessionFactory Зачем он нужен где он используется?

StatelessSession – командно-ориентированный API, предоставляемый Hibernate. 
Используйте его для потоковой передачи данных в базу и из нее в форме отсоединенных (detached) объектов. 
StatelessSession не имеет ассоциированного persistence-контекста и не предоставляет большую часть высокоуровневой семантики. 

```java
StatelessSession session = sessionFactory.openStatelessSession();
Transaction tx = session.beginTransaction();
   
ScrollableResults customers = session.getNamedQuery("GetCustomers")
    .scroll(ScrollMode.FORWARD_ONLY);
while ( customers.next() ) {
    Customer customer = (Customer) customers.get(0);
    customer.updateStuff(...);
    session.update(customer);
}
   
tx.commit();
session.close();
```

[к оглавлению](#Hibernate)

## 21. Зачем нужен решим read only?

You might actually have reasons to mark transactions as read-only.

+ Transactions for reading might look indeed strange and often people don't mark methods for transactions in this case. 
But JDBC will create transaction anyway, it's just it will be working in autocommit=true 
if different option wasn't set explicitly.
+ But there is no guarantee that your method doesn't write into the database. 
If you mark method as @Transactional(readonly=true), Spring will set the JDBC transaction into a read-only mode, 
thus you'll dictate whether it's actually possible to write into DB in scope of this transaction. 
If your architecture is cumbersome and some team members may choose to put modification query where it's not expected, 
this flag would point you to the problematic place.
+ Also read-only transactions can be optimized by DBs, but this of course is DB specific. 
E.g. MySQL added support for this only in InnoDB starting from 5.6.4 version.
+ If you're not using JDBC directly, but rather an ORM, that might be problematic. 
For instance Hibernate community says that working outside of transaction might cause unpredictable behavior. 
This is because Hibernate will open transaction, but it won't close it on its own, thus connection will be returned 
to the Connection Pool with transaction being not committed. What happens then? 
JDBC keeps silence, thus this is implementation specific (MySQL rolls back transaction, Oracle afair commits it). 
This also can be configured on Connection Pool level (e.g. C3P0 gives you such an option, rollback by default).
+ Another thing when it comes to Hibernate, Spring sets the FlushMode to MANUAL in case of read-only transactions, 
which leads to other optimizations like no need for dirty checks.
+ You may want to override or set explicitly the transaction isolation level. 
This impacts read-transactions as well since you do or don't want to read uncommitted changes, 
be exposed to phantom reads, etc.

[к оглавлению](#Hibernate)

## Туториалы:

Hibernate Tutorial https://www.tutorialspoint.com/hibernate/index.htm
 
Hibernate ORM http://hibernate.org/orm/documentation/5.4/

https://ru.wikipedia.org/wiki/Hibernate_(%D0%B1%D0%B8%D0%B1%D0%BB%D0%B8%D0%BE%D1%82%D0%B5%D0%BA%D0%B0)