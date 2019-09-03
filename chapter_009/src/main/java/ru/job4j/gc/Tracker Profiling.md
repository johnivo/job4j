1. Используя различные утилиты для анализа памяти виртуальной машины провести анализ работы программы 
из второго модуля. Программа по обработке заявок Tracker.

2. Данные анализа описать в текстовом файле и добавить в репозиторий. В файле нужно указать,
каким инструментом проведен анализ и что по нему видно.

3. Попробовать добиться состояния выхода за пределы памяти и посмотреть состояние виртуальной машины.

Для анализа использовалась jconsole:

![img](https://github.com/johnivo/job4j/blob/master/chapter_009/src/main/resources/tracker1.jpg)

Использовались настройки по умолчанию(G1GC), при заполнении соотвествующих областей в куче
визаульно наблюдались малые сборки мусора в Eden Space

![img](https://github.com/johnivo/job4j/blob/master/chapter_009/src/main/resources/tracker2.jpg)

и полные

![img](https://github.com/johnivo/job4j/blob/master/chapter_009/src/main/resources/Tracker3.jpg)

При критическом росте количества заявок (около 10млн) произошло переполнение доступной памяти созданными объектами.
Программа упала с java.lang.OutOfMemoryError: Java heap space.

![img](https://github.com/johnivo/job4j/blob/master/chapter_009/src/main/resources/Tracker4.jpg)

Все потоки были остановлены, данные выгружены из памяти.