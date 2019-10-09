## Multithreading

[1. Чем отличается процесс от потока?](#1-Чем-отличается-процесс-от-потока)

[2. Каким образом можно создать поток?](#2-Каким-образом-можно-создать-поток)

[3. Как работают методы sleep, yield, wait, notify и notifyAll?](#3-Как-работают-методы-sleep-yield-wait-notify-и-notifyAll)

[4. Объясните следующие термины: монитор, мьютекс, критическая секция?](#4-Объясните-следующие-термины-монитор-мьютекс-критическая-секция)

[5. Как работает join()?](#5-Как-работает-join)

[6. Что такое DeadLock? Приведите примеры.](#6-Что-такое-DeadLock-Приведите-примеры)

[7. Назовите различия между Collections.synchronizedMap(new HashMap()) и ConcurrentHashMap?](#7-Назовите-различия-между-CollectionssynchronizedMapnew-HashMap-и-ConcurrentHashMap)

[8. Различия в интерфейсах Runnable и Callable?](#8-Различия-в-интерфейсах-Runnable-и-Callable)

[9. Различя между isInterrupted(), interrupted()?](#9-Различя-между-isInterrupted-interrupted)

[10. Что происходит при вызове Thread.interrupt()?](#10-Что-происходит-при-вызове-Threadinterrupt)

[11. Перечислите ВСЕ причины по которым может выскочить InterruptedException?](#11-Перечислите-ВСЕ-причины-по-которым-может-выскочить-InterruptedException)

[12. Назовите отличия synchronize{} и ReentrantLock.](#12-Назовите-отличия-synchronize-и-ReentrantLock)

[13. Приведите наиболее существенное отличие между CountDownLatch и Barrier?](#13-Приведите-наиболее-существенное-отличие-между-CountDownLatch-и-Barrier)

[14. Отличие Thread.start() и Thread.run()?](#14-Отличие-Threadstart-и-Threadrun)

[15. Объясните ключевое слово volatile.](#15-Объясните-ключевое-слово-volatile)

[16. Расскажите про приоритеты потока?](#16-Расскажите-про-приоритеты-потока)

[17. Что такое потоки-демоны?](#17-Что-такое-потоки-демоны)

[18. Назовите все возможные состояния потока?](#18-Назовите-все-возможные-состояния-потока)

[19. Что такое race condition?](#19-Что-такое-race-condition)

[20. Что такое Thread Local переменная?](#20-Что-такое-Thread-Local-переменная)

[21. Что такое FutureTask?](#21-Что-такое-FutureTask)

[22. Что такое Thread Pool?](#22-Что-такое-Thread-Pool)

[23. Что такое Semaphore?](#23-Что-такое-Semaphore)

[24. Чем отличается submit от execute у ExecutorServices?](#24-Чем-отличается-submit-от-execute-у-ExecutorServices)

[25. Чем отличается shutdown от shutdownNow у ThreadPoolExecutor?](#25-Чем-отличается-shutdown-от-shutdownNow-у-ThreadPoolExecutor)

[26. Как создать ThreadPool у ExecutorService только на 1, на 5, на неограниченное количество потоков?](#26-Как-создать-ThreadPool-у-ExecutorService-только-на-1-на-5-на-неограниченное-количество-потоков)

[27. Что такое ReadWriteLock?](#27-Что-такое-ReadWriteLock)

[28. В чём отличие Thread от FutureTask? В чём отличие Thread.interrupt() и FutureTask.cancel()?](#28-В-чём-отличие-Thread-от-FutureTask-В-чём-отличие-Threadinterrupt-и-FutureTaskcancel)

[29. Расскажите про шаблон проектирования Producer Consumer?](#29-Расскажите-про-шаблон-проектирования-Producer-Consumer)

[30. ](#30-)

## 1. Чем отличается процесс от потока?



[к оглавлению](#Multithreading)

## 2. Каким образом можно создать поток?



[к оглавлению](#Multithreading)

## 3. Как работают методы sleep, yield, wait, notify и notifyAll?



[к оглавлению](#Multithreading)

## 4. Объясните следующие термины: монитор, мьютекс, критическая секция?



[к оглавлению](#Multithreading)

## 5. Как работает join()?



[к оглавлению](#Multithreading)

## 6. Что такое DeadLock Приведите примеры?



[к оглавлению](#Multithreading)

## 7. Назовите различия между CollectionssynchronizedMap(new HashMap()) и ConcurrentHashMap?



[к оглавлению](#Multithreading)

## 8. Различия в интерфейсах Runnable и Callable?



[к оглавлению](#Multithreading)

## 9. Различя между isInterrupted(), interrupted()?



[к оглавлению](#Multithreading)

## 10. Что происходит при вызове Threadinterrupt()?



[к оглавлению](#Multithreading)

## 11. Перечислите ВСЕ причины по которым может выскочить InterruptedException?



[к оглавлению](#Multithreading)

## 12. Назовите отличия synchronize{} и ReentrantLock?



[к оглавлению](#Multithreading)

## 13. Приведите наиболее существенное отличие между CountDownLatch и Barrier?



[к оглавлению](#Multithreading)

## 14. Отличие Threadstart() и Threadrun()?



[к оглавлению](#Multithreading)

## 15. Объясните ключевое слово volatile?



[к оглавлению](#Multithreading)

## 16. Расскажите про приоритеты потока?



[к оглавлению](#Multithreading)

## 17. Что такое потоки демоны?



[к оглавлению](#Multithreading)

## 18. Назовите все возможные состояния потока?



[к оглавлению](#Multithreading)

## 19. Что такое race condition?



[к оглавлению](#Multithreading)

## 20. Что такое Thread Local переменная?



[к оглавлению](#Multithreading)

## 21. Что такое FutureTask?



[к оглавлению](#Multithreading)

## 22. Что такое Thread Pool?



[к оглавлению](#Multithreading)

## 23. Что такое Semaphore?



[к оглавлению](#Multithreading)

## 24. Чем отличается submit от execute у ExecutorServices?



[к оглавлению](#Multithreading)

## 25. Чем отличается shutdown от shutdownNow у ThreadPoolExecutor?



[к оглавлению](#Multithreading)

## 26. Как создать ThreadPool у ExecutorService только на 1, на 5, на неограниченное количество потоков?



[к оглавлению](#Multithreading)

## 27. Что такое ReadWriteLock?



[к оглавлению](#Multithreading)

## 28. В чём отличие Thread от FutureTask В чём отличие Threadinterrupt() и FutureTaskcancel()?



[к оглавлению](#Multithreading)

## 29. Расскажите про шаблон проектирования Producer Consumer?



[к оглавлению](#Multithreading)

## 30. ?



[к оглавлению](#Multithreading)

