## IO

[1. Расскажите общую идею, что такое поток ввода-вывода?](#1-Расскажите-общую-идею-что-такое-поток-ввода-вывода)

[2. Что такое Java IO?](#2-Что-такое-Java-IO)

[3. Что такое Java NIO?](#3-Что-такое-Java-NIO)

[4. Что такое Scanner?](#4-Что-такое-Scanner)

[5. Как работает Scanner внутри?](#5-Как-работает-Scanner-внутри)

[6. Какие базовые методы существуют в Scanner?](#6-Какие-базовые-методы-существуют-в-Scanner)

[7. Что такое байтовый поток? Как он реализован внутри?](#7-Что-такое-байтовый-поток-Как-он-реализован-внутри)

[8. Что такое символьный поток? Как он реализован внутри?](#8-Что-такое-символьный-поток-Как-он-реализован-внутри)

[9. Что такое буферизированный поток?](#9-Что-такое-буферизированный-поток)

[10. Что такое форматированный вывод? Какие механизмы позволяют осуществить форматированный вызов?](#10-Что-такое-форматированный-вывод-Какие-механизмы-позволяют-осуществить-форматированный-вызов)

[11. Как осуществлятся ввод и вывод из командной строки?](#11-Как-осуществлятся-ввод-и-вывод-из-командной-строки)

[12. Что такое класс Console? Расскажите его АПИ.](#12-Что-такое-класс-Console-Расскажите-его-АПИ)

[13. Что такое поток данных? Data stream.](#13-Что-такое-поток-данных-Data-stream)

[14. Что такое поток объектов, Object stream.](#14-Что-такое-поток-объектов,-Object-stream)

[15. Что такое Path? Как он реализуется на разных ОС?](#15-Что-такое-Path-Как-он-реализуется-на-разных-ОС)

[16. Как получить список файлов?](#16-Как-получить-список-файлов)

[17. Как проверить что файловая сущность является файлом или папкой?](#17-Как-проверить-что-файловая-сущность-является-файлом-или-папкой)

[18. Как удалить файл?](#18-Как-удалить-файл)

[19. Как переместить файл?](#19-Как-переместить-файл)

[20. Как управлять атрибутами файла?](#20-Как-управлять-атрибутами-файла)

[21. Как создать файл?](#21-Как-создать-файл)

[22. Как создать директорию?](#22-Как-создать-директорию)

[23. Как записать в файл?](#23-Как-записать-в-файл)

[24. Как прочитать данные из файла?](#24-Как-прочитать-данные-из-файла)

[25. Работа с сокетами](#25-Работа-с-сокетами)

[26. Какие существуют виды потоков ввода/вывода?](#26-Какие-существуют-виды-потоков-ввода/вывода)

[27. Назовите основные предки потоков ввода/вывода.](#27-Назовите-основные-предки-потоков-ввода/вывода)

[28. Что общего и чем отличаются следующие потоки: InputStream, OutputStream, Reader, Writer?](#28-Что-общего-и-чем-отличаются-следующие-потоки-InputStream-OutputStream-Reader-Writer)

[29. Что вы знаете о RandomAccessFile?](#29-Что-вы-знаете-о-RandomAccessFile)

[30. Какой класс-надстройка позволяет читать данные из входного байтового потока в формате примитивных типов данных?](#30-Какой-класс-надстройка-позволяет-читать-данные-из-входного-байтового-потока-в-формате-примитивных-типов-данных)

[31. Какой класс-надстройка позволяет ускорить чтение/запись за счет использования буфера?](#31-Какой-класс-надстройка-позволяет-ускорить-чтение/запись-за-счет-использования-буфера)

[32. Какие классы позволяют преобразовать байтовые потоки в символьные и обратно?](#32-Какие-классы-позволяют-преобразовать-байтовые-потоки-в-символьные-и-обратно)

[33. Какой символ является разделителем при указании пути к ЭФС?](#33-Какой-символ-является-разделителем-при-указании-пути-к-ЭФС)

[34. Что вы знаете об интерфейсе FilenameFilter?](#34-Что-вы-знаете-об-интерфейсе-FilenameFilter)

[35. Что такое сериализация?](#35-Что-такое-сериализация)

## 1. Расскажите общую идею что такое поток ввода вывода?

Цель создания `InputStream` и `OutputStream` это абстрактный доступ к вводу и выводу. Источник при этом не важен. 
Это может быть файл, консоль, веб-страница. Stream - это бесконечный поток данных, подключенный к источнику данных

[к оглавлению](#IO)

## 2. Что такое Java IO?

IO API – (Input & Output) в первую очередь это Java API, которые облегчают работу с потоками. 
В `java.io` существуют так называемые потоки ввода и вывода (`InputStream` and `OutputStream`).

В основном `java.io` предназначен для чтения и записи данных в ресурс:

1) файл;
2) при работе с сетевым подключением;
3) `System.err`, `System.in`, `System.out`;
4) при работе с буфером.

![img](https://github.com/johnivo/job4j/blob/master/interview_questions/src/main/resources/io_diagram.gif)

Для разных типов данных существуют разные реализации классов

|_| Byte Based| _| Character Based| _ |
| ---| ---| ---| ---| --- |
| _| Input| Output| Input| Output |
| Basic| InputStream| OutputStream| Reader / InputStreamReader| Writer / OutputStreamWriter |
| Arrays| ByteArrayInputStream| ByteArrayOutputStream| CharArrayReader| CharArrayWriter |
| Files| FileInputStream / RandomAccessFile| FileOutputStream / RandomAccessFile| FileReader| FileWriter |
| Pipes| PipedInputStream| PipedOutputStream| PipedReader| PipedWriter |
| Buffering| BufferedInputStream| BufferedOutputStream| BufferedReader| BufferedWriter |
| Filtering| FilterInputStream| FilterOutputStream| FilterReader| FilterWriter |
| Parsing| PushbackInputStream / StreamTokenizer| _| PushbackReader / LineNumberReader| _ |
| Strings| _| _| StringReader| StringWriter |
| Data| DataInputStream| DataOutputStream| _| _ |
| Data - Formatted| _| PrintStream| _| PrintWriter |
| Objects| ObjectInputStream| ObjectOutputStream| _| _ |

**Классы Java IO API**

**Базовые**

+ `InputStream` /` OutputStream` - абстрактный класс, определяющий потоковый байтовый ввод/вывод
+ `Reader` / `Writer` - Символьные потоки имеют два основных абстрактных класса `Reader` и `Writer`, 
управляющие потоками символов `Unicode`.
+ `InputStreamReader` / `OutputStreamWriter` Входной/выдодной поток, транслирующий байты в символы

**Массивы**
+ `ByteArrayInputStream` / `ByteArrayOutputStream` - использует байтовый массив в потоке.
+ `CharArrayReader` / `CharArrayWriter` - читает/пишет из символьного массива.

**Files**
+ `FileInputStream` / `FileOutputStream` - Чтение/Отправка данных в файл на диске. Реализация класса `OutputStream`                                       
+ `RandomAccessFile` / `RandomAccessFile` - Чтение/запись файлов с произвольным доступом. метод `seek()` позволяет 
переместиться к определенной позиции и изменить хранящееся там значение. 
При использовании RandomAccessFile необходимо знать структуру файла. Класс `RandomAccessFile` содержит методы для чтения 
и записи примитивов и строк UTF-8.
`RandomAccessFile` может открываться в режиме чтения ("r") или чтения/записи ("rw"). Также есть режим "rws", когда файл 
открывается для операций чтения-записи и каждое изменение данных файла немедленно записывается на физическое устройство.
+ `FileReader` / `FileWriter` `FileWriter` записывает данные в файл. При вводе/выводе практически всегда применяется 
буферизация, поэтому используется `BufferedWriter`.                           
Когда данные входного потока исчерпываются, метод `readLine()` возвращает `null`. Для потока явно вызывается метод `close()`; 
если не вызвать его для всех выходных файловых потоков, в буферах могут остаться данные, и файл получится неполным

**Буферизация**
+ `BufferedInputStream` / `BufferedOutputStream` - буферизируемый поток. Буферы вывода нужно для повышения производительности
+ `BufferedReader` / `BufferedWriter`

[к оглавлению](#IO)

## 3. Что такое Java NIO?

| IO| NIO |
| ---| --- |
| Потокоориентированный| Буфер-ориентированный |
| Блокирующий (синхронный) ввод/вывод| Неблокирующий (асинхронный) ввод/вывод |

**Предпосылки создания:**

Недостатки IO:

+ The `File` class lacked some important functionality, such as a copy method.
+ It also defined many methods that returned boolean. As one can imagine, in case of an error, `false` was returned, 
rather than throwing an exception. The developer had, indeed, no way of knowing why it failed.
+ Did not provide good handling on support of symbolic links.
+ A limited set of file attributes was provided.

To overcome these problems, java.nio package was introduced in java 4. The key features were:
+ Channels and Selectors: A channel is an abstraction on lower-level file system features, e.g. memory-mapped files.
+ Buffers: Buffering for all primitive classes (except for Boolean).
+ Charset: Charset (`java.nio.charset`), encoders, and decoders to map bytes and Unicode symbols

With java 7 the `java.nio.file` package is introduced providing a better support for handling symbolic links, 
file attributes access and specially to support extended the file system through classes such 
as **Path, Paths and Files**.

Состоит из 3 основных компонентов:
+ `Channels`
+ `Buffers`
+ `Selectors`

Java NIO: Channels read data into Buffers, and Buffers write data into Channels
There are several Channel and Buffer types. Here is a list of the primary Channel implementations in Java NIO:
+ `FileChannel`
+ `DatagramChannel`
+ `SocketChannel`
+ `ServerSocketChannel`

A Selector allows a single thread to handle multiple Channel's. 
This is handy if your application has many connections (Channels) open, but only has low traffic on each connection. 
For instance, in a chat server.

Example.
```java
RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
    FileChannel inChannel = aFile.getChannel();
    ByteBuffer buf = ByteBuffer.allocate(48);
    int bytesRead = inChannel.read(buf);
    while (bytesRead != -1) {
      System.out.println("Read " + bytesRead);
      buf.flip();
      while(buf.hasRemaining()){
          System.out.print((char) buf.get());
      }
      buf.clear();
      bytesRead = inChannel.read(buf);
    }
    aFile.close();
```

[к оглавлению](#IO)

## 4. Что такое Scanner?

`Scanner` - класс в `java.util` для чтения данных примитивных типов `int`, `double`, `String` и т.д. 
Это самый простой способ для получения входящих данных, но не самый эффективный если есть ограничения по времени.

Обычно создается в виде
```
Scanner sc = new Scanner(System.in);
```
Для чтения данных типа `XYZ` используется метод `nextXYZ()`. Для проверки что есть данные такого типа `hasNextXYZ()`

Например:
+ `hasNextBoolean()`
+ `hasNextByte()`
+ `hasNextDouble()`
+ `hasNextFloat()`

[к оглавлению](#IO)

## 5. Как работает Scanner внутри?

использует регулярные выражения
```java
private static final String LINE_SEPARATOR_PATTERN =
                                           "\r\n|[\n\r\u2028\u2029\u0085]";
private static Pattern NON_ASCII_DIGIT = Pattern.compile(
        "[\\p{javaDigit}&&[^0-9]]");
```                                           
кеширует значения
```java
public String nextLine() {
       modCount++;
        if (hasNextPattern == linePattern())
            return getCachedResult();
        clearCaches();
        String result = findWithinHorizon(linePattern, 0);
        if (result == null)
            throw new NoSuchElementException("No line found");
        MatchResult mr = this.match();
        String lineSep = mr.group(1);
        if (lineSep != null)
            result = result.substring(0, result.length() - lineSep.length());
        if (result == null)
            throw new NoSuchElementException();
        else
            return result;
    }
```  
Java `Scanner` class extends `Object` class and implements `Iterator` and `Closeable` interfaces.

[к оглавлению](#IO)

## 6. Какие базовые методы существуют в Scanner?

+ `delimiter()` It is used to get the Pattern which the Scanner class is currently using to match delimiters.
+ `hasNextLine` 
+ `nextLine`
+ `hasNextInt` 
+ `nextInt` ...

[к оглавлению](#IO)

## 7. Что такое байтовый поток Как он реализован внутри?

Byte streams работает с данными побайтово (8 bits). Например, `FileInputStream` используется для чтения 
и `FileOutputStream` для записи. Byte streams интерфейс, который внутри основан на байтовом массиве. 
В основе находится некий буфер который заполняется, вычитывается и заново заполняется. Методы внутри native.
```java
private native int read0() throws IOException;
``` 

[к оглавлению](#IO)

## 8. Что такое символьный поток Как он реализован внутри?

В Java, символы хранятся в кодировке `Unicode` (16 bit). Символный поток позволяет читать данные символ за символом. 
Например `FileReader` и `FileWriter` символьные потоки.
Для них можно задать кодировку:
```java 
Reader reader = new InputStreamReader(in, "UTF-8");
``` 

[к оглавлению](#IO)

## 9. Что такое буферизированный поток?

Для оптимизации операций ввода-вывода используются буферизуемые потоки. Эти потоки добавляют к стандартным специальный 
буфер в памяти, с помощью которого повышается производительность при чтении и записи потоков.
`BufferedInputStream` и `BufferedOutputStream`. 
Это может сделать программу намного эффективней, т.к. каждый такой запрос часто инициировал доступ к диску, сетевое 
действие, или некоторую другую работу, которая относительно дорога.

[к оглавлению](#IO)

## 10. Что такое форматированный вывод Какие механизмы позволяют осуществить форматированный вызов?

+ %a	Шестнадцатеричное число с плавающей точкой
+ %b	Булево значение
+ %c	Символ
+ %d	Десятичное целое
+ %e	Число в научной записи
+ %f	Десятичное число с плавающей точкой
+ %h	Хеш-код от аргумента
+ %o	Восьмеричное целое
+ %n	Символ переноса строки
+ %t	Время
+ %x	Шестнадцатеричное целое

```java
String output = String.format("%s = %d", "joe", 35);
String.format(); Formatter
```

Можно создать `Formatter` и привязать его к `StrungBuilder`:
```java
StringBuilder sbuf = new StringBuilder();
Formatter fmt = new Formatter(sbuf);
fmt.format("PI = %f%n", Math.PI);
System.out.print(sbuf.toString());
```
Также есть свое форматирование для вывода дат.
Можно задавать выравнивание, количество отступов:
```java
String.format("|%-20d|", 93); // prints: |93                  |
String.format("|%020d|", 93); // prints: |00000000000000000093|
```

[к оглавлению](#IO)

## 11. Как осуществлятся ввод и вывод из командной строки?

По умолчанию ввод с клавиатуры, вывод на монитор.

Класс `System` содержит также три переменные предопределенных потоков ввода-вывода: `in`, `out` и `err`   
+ Переменная `System.out` ссылается на стандартный поток вывода. По умолчанию это консоль.
+ Переменная `System.in` ссылается на стандартный поток ввода, которым по умолчанию является клавиатура.
+ `System.err` - для ошибок.

[к оглавлению](#IO)

## 12. Что такое класс Console Расскажите его АПИ?

Альтернатива стандратным потокам ввода / вывода класс `Console`.

Для создание экземпляра используется `System.console()`. Метод может вернуть `null`, если консоль недоступна. 
Консоль позволяет вводить пароль используя метод `readPassword` (не видны символы при вводе, не сохраняется в памяти). 
   
+ `flush()` выводит на консоль все данные из буфера.
+ `format()` выводит на консоль строку с использованием форматирования.
+ `printf()` выводит на консоль строку с использованием форматирования (фактически то же самое, что и предыдущий метод)
+ `String readLine()` считывает с консоли введенную пользователем строку.
+ `char[] readPassword()` считывает с консоли введенную пользователем строку, 
при этом символы строки не отображаются на консоли.

[к оглавлению](#IO)

## 13. Что такое поток данных Data stream?

Классы `DataOutputStream` и `DataInputStream` позволяют записывать и считывать данные примитивных типов 
(`boolean`, `char`, `byte`, `short`, `int`, `long`, `float` and `double`).
+ `DataOutputStream.writeDouble`	
+ `DataInputStream.readDouble`

[к оглавлению](#IO)

## 14. Что такое поток объектов, Object stream?

`ObjectOutputStream` используется для конвертации объектов в поток. В java это называется сериализация. 
Объект преобразоыванный таким образом может быть сохранен в базу данных, передан по сети и т.п. 
Для записи в файл можно использовать `FileOutputStream`.
Объект который передается в потоке должен реализовывать интерфейс `java.io.Serializable`.
```java
FileOutputStream fos = new FileOutputStream("EmployeeObject.ser");
ObjectOutputStream oos = new ObjectOutputStream(fos);
// write object to file
oos.writeObject(emp);
```

При сериализации используют переменную `SerialVersionUID`. 
Во время сериализации, среда выполнения Java создает номер версии для класса, так что она может десереализировать 
его позже. В Java этот номер версии известен как `SerialVersionUID`. Если во время десериализации, `SerialVersionUID` 
не соответствует, то процесс завершится с исключением

`SerialVersionUID` используется для указании версии сериализованных данных.
+ Когда мы не объявляем `SerialVersionUID` в нашем классе, среда выполнения Java делает это за нас, но этот процесс 
чувствителен ко многим метаданным класса включая количество полей, тип полей, модификаторы доступа полей, интерфейсов, 
которые реализованы в классе и пр. Вы можете найти точную информацию в документации о сериализации от Oracle.
+ Рекомендуется объявлять `SerialVersionUID` как `private static final long` переменную во избежание механизма по умолчанию. 

[к оглавлению](#IO)

## 15. Что такое Path Как он реализуется на разных ОС?

Java 7 представляет новую абстракцию для пути, а именно интерфейс `Path`. Он используется в новых функциях и API, 
по всему NIO.2. Объект пути содержит имена каталогов и файлов, которые составляют полный путь до файла/каталога, 
представленного объектом `Path`. 

`Path` содержит методы для извлечения элементов пути, манипуляций с ними и их добавления.

Путь к файлу, в разных системх может записываться по разному, `\` или `/`, поэтому лучше
использовать `File.separator` для построения пути
```java
// Cоздание объекта Path через вызов статического метода get() класса Paths 
Path testFilePath = Paths.get("/home/heorhi/testfile.txt"); 
         
//Пример строки создания объекта Path пути для запуска в Windows 
Path testFilePath = Paths.get("D:\\test\\testfile.txt");
``` 

[к оглавлению](#IO)

## 16. Как получить список файлов?

+ Без учета подпапок
```java
File file = new File("dir");
File[] filesArr = file.listFiles();
String[] filesNames = file.list();
```
+ С учетом подпапок
```java
public void listFilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            System.out.println(fileEntry.getName());
        }
    }
}
final File folder = new File("/home/you/Desktop");
listFilesForFolder(folder);
```
 
**Java 8**
+ Java NIO без учета подпапок
```java
Stream<Path> stramFiles = Files.list(Paths.get("dir"));
```
+ С учетом подпапок. Files.walk API is available from Java 8.
```java
try (Stream<Path> paths = Files.walk(Paths.get("/home/you/Desktop"))) {
    paths
        .filter(Files::isRegularFile)
        .forEach(System.out::println);
}
```
+ Через `walkFileTree` 
(The difference between `walk` and `walkFileTree` is that they supply different interfaces for walking the tree: 
`walkFileTree` takes `FileVisitor`, walk gives `Stream<Path>`)
```java
Files.walkFileTree(directory, Collections.emptySet(), 1, new SimpleFileVisitor<Path>() {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        doSomething(file);
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        // log exc
        return FileVisitResult.CONTINUE;
    }
});
```
 
[к оглавлению](#IO)
 
## 17. Как проверить что файловая сущность является файлом или папкой?

```java
File file = new File("/Users/pankaj/source.txt");
File dir = new File("/Users/pankaj");
File notExists = new File("/Users/pankaj/notafile");
        
System.out.println("/Users/pankaj/source.txt is file?" + file.isFile());
System.out.println("/Users/pankaj/source.txt is directory?" + file.isDirectory());
        
System.out.println("/Users/pankaj is file?" + dir.isFile());
System.out.println("/Users/pankaj is directory?" + dir.isDirectory());
        
System.out.println("/Users/pankaj/notafile is file?" + notExists.isFile());
System.out.println("/Users/pankaj/notafile is directory?" + notExists.isDirectory());
```
С использованием `path`
```java
Path file = new File(path).toPath();
boolean exists =      Files.exists(file);        // Check if the file exists
boolean isDirectory = Files.isDirectory(file);   // Check if it's a directory
boolean isFile =      Files.isRegularFile(file); // Check if it's a regular file
```

[к оглавлению](#IO)

## 18. Как удалить файл?

**Using `java.io.File.delete()` function:**
```java
File file = new File("/Users/pankaj/file.txt");

if(file.delete()) {
    System.out.println("/Users/pankaj/file.txt File deleted");
} else {
    System.out.println("File /Users/pankaj/file.txt doesn't exist");
}    
```
**Using `java.nio.file.files.deleteIfExists(Path p)`**
```java
Files.deleteIfExists(Paths.get("C:\\Users\\Mayank\\Desktop\\445.txt")); 
```

[к оглавлению](#IO)

## 19. Как переместить файл?

`Java.io.File` does not contains any ready make move file method, 
but you can workaround with the following two alternatives :

+ `File.renameTo()` (может не сработать на разных файловых системах. Надо проверять результат)
+ Copy to new file and delete the original file.

Для Java 7:

+ `Files.move(Paths.get("/foo.txt")`, `Paths.get("bar.txt")`, `StandardCopyOption.REPLACE_EXISTING);`

[к оглавлению](#IO)

## 20. Как управлять атрибутами файла?

**Базовые атрибуты** (доступны во всех ОС):

+ File type
+ File size
+ Created time
+ Owner of the file
+ Last time modified
+ Last time accessed
+ Hidden
+ System file
+ Regular file
+ isDirectory

**`FileAttributeView`** - базовый интерфейс с подинтерфейсами

+ `BasicFileAttributeView`
+ `DosFileAttributeView`
+ `PosixFileAttributeView`
+ `UserDefinedFileAttributeView`
+ `AclFileAttributeView`
+ `FileOwnerAttributeView`

```java
Path path = FileSystems.getDefault().getPath("c:/test", "somefile.txt");
BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
basicView.readAttributes().lastAccessTime().toMillis();;  // will return the last time the file was read.
basicView.readAttributes().lastModifiedTime().toMillis();  // will return the last time the file was changed.
basicView.readAttributes().creationTime().toMillis();  // will return the creation time.
```

```java
DosFileAttributeView dosView = Files.getFileAttributeView(path,DosFileAttributeView.class);
dosView.setHidden(true);
dosView.setReadOnly(true);
dosView.setSystem(true);
dosView.setArchive(true);
```

[к оглавлению](#IO)

## 21. Как создать файл?

Четыре способа:
1. `File file = new File(absoluteFilePath);`

   `file.createNewFile();`
     
2. `FileOutputStream fos = new FileOutputStream("name.txt");`

   `fos.write(fileData.getBytes());`
  
   `fos.flush();`
  
   `fos.close();`
  
3. `String fileData = "Pankaj Kumar";`

   `Files.write(Paths.get("name.txt"), fileData.getBytes());`
  
4. `Path path = Paths.get("name.txt");`

   `Files.createFile(path);`

[к оглавлению](#IO)

## 22. Как создать директорию?

Два способа:
+ `new File("/path/directory").mkdirs();`

+ `Files.createDirectories(Paths.get("/path/to/directory"));`

[к оглавлению](#IO)

## 23. Как записать в файл?

+ **BufferedWritter**
```java
    BufferedWriter writer = new BufferedWriter(new FileWriter("c:/temp/samplefile1.txt"));
    writer.write(fileContent);
    writer.close();
```
        
+ **FileWriter/PrintWriter**
```java
    FileWriter fileWriter = new FileWriter("c:/temp/samplefile2.txt");
    fileWriter.write(fileContent);
    fileWriter.close();
```

+ **FileOutputStream**
```java
    FileOutputStream fos = new FileOutputStream("c:/temp/samplefile4.txt");
    byte[] strToBytes = fileContent.getBytes();
    fos.write(strToBytes);
    
    fos.close();
```

+ **DataOutputStream**
```java
    FileOutputStream fos = new FileOutputStream("c:/temp/samplefile5.txt");
    DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(fos));
    dataOutStream.writeUTF(fileContent);
     
    dataOutStream.close();
```

+ **FileChannel**
```java
    RandomAccessFile stream = new RandomAccessFile("c:/temp/samplefile6.txt", "rw");
    FileChannel channel = stream.getChannel();
    byte[] strBytes = fileContent.getBytes();
    ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
    buffer.put(strBytes);
    buffer.flip();
    channel.write(buffer);
    stream.close();
    channel.close();
```
+ **Java 7 Path**
```java
    Path path = Paths.get("c:/temp/samplefile7.txt");     
    Files.write(path, fileContent.getBytes());
```

**Summary**
+ If we try to write to a file that doesn’t exist, the file will be created first and no exception will be thrown (except using Path method).
+ Always close the output stream after writing the file content to release all resources. It will also help in not corrupting the file.
+ Use PrintWriter is used to write formatted text.
+ Use FileOutputStream to write binary data.
+ Use DataOutputStream to write primitive data types.
+ Use FileChannel to write larger files.

[к оглавлению](#IO)

## 24. Как прочитать данные из файла?

+ **BufferedReader**
```java
    BufferedReader br = new BufferedReader(new FileReader(file)); 
          
    String st; 
    while ((st = br.readLine()) != null) 
        System.out.println(st); 
    }
```
    
+ **FileReader**
```java
    FileReader fr = 
        new FileReader("C:\\Users\\pankaj\\Desktop\\test.txt"); 
      
    int i; 
    while ((i=fr.read()) != -1) 
    System.out.print((char) i);      
```
+ **Scanner**
```java
    Scanner sc = new Scanner(file); 
      
    // we just need to use \\Z as delimiter 
    sc.useDelimiter("\\Z"); 
      
    System.out.println(sc.next());
```
+ **Reading the whole file in a List**
```java
    data = new String(Files.readAllBytes(Paths.get(fileName)));
```

[к оглавлению](#IO)

## 25. Работа с сокетами?

Создание сервера:

```java
server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
System.out.println("Сервер запущен!"); 
clientSocket = server.accept(); // accept() будет ждать пока
                
try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    // и отправлять
    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
    System.out.println(word);
    // не долго думая отвечает клиенту
    out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
    out.flush(); // выталкиваем все из буфера
    } finally { // в любом случае сокет будет закрыт
        System.out.println("dfjkhgkdf");
        clientSocket.close();
        // потоки тоже хорошо бы закрыть
        in.close();
        out.close();
}
```

Создание клиента:

```java
// адрес - локальный хост, порт - 4004, такой же как у сервера
clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем у сервера доступ на соединение
reader = new BufferedReader(new InputStreamReader(System.in));
in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // читать соообщения с сервера
out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // писать туда же

System.out.println("Вы что-то хотели сказать? Введите это здесь:");
// если соединение произошло и потоки успешно созданы - мы можем
// работать дальше и предложить клиенту что то ввести
// если нет - вылетит исключение
String word = reader.readLine(); // ждём пока клиент что-нибудь не напишет в консоль
out.write(word + "\n"); // отправляем сообщение на сервер
out.flush();
String serverWord = in.readLine(); // ждём, что скажет сервер
System.out.println(serverWord); // получив - выводим на экран
```

[к оглавлению](#IO)

## 26. Какие существуют виды потоков ввода/вывода?

Разделяют два вида потоков ввода/вывода: байтовые и символьные.

[к оглавлению](#IO)

## 27. Назовите основные предки потоков ввода/вывода?

+ Байтовые: `java.io.InputStream`, `java.io.OutputStream`;

+ Символьные: `java.io.Reader`, `java.io.Writer`;

[к оглавлению](#IO)

## 28. Что общего и чем отличаются следующие потоки: InputStream, OutputStream, Reader, Writer?

Базовый класс `InputStream` представляет классы, которые получают данные из различных источников:
+ массив байтов;
+ строка (`String`);
+ файл;
+ канал (`pipe`) - данные помещаются с одного конца и извлекаются с другого;
+ последовательность различных потоков, которые можно объединить в одном потоке;
+ другие источники (например, подключение к интернету).

Класс `OutputStream` — это абстрактный класс, определяющий потоковый байтовый вывод. В этой категории находятся классы, 
определяющие, куда направляются ваши данные: 
+ в массив байтов (но не напрямую в `String` - предполагается, что вы сможете 
создать их из массива байтов); 
+ в файл; 
+ или в канал.

Символьные потоки имеют два основных абстрактных класса `Reader` и `Writer`, управляющие потоками символов `Unicode`. 
Класс `Reader` — абстрактный класс, определяющий символьный потоковый ввод. Класс `Writer` — абстрактный класс, 
определяющий символьный потоковый вывод. В случае ошибок все методы класса передают исключение `IOException`.

[к оглавлению](#IO)

## 29. Что вы знаете о RandomAccessFile?

Класс `RandomAccessFile` наследуется напрямую от `Object` и не наследуется от вышеприведенных базовых классов ввода\вывода. 
Предназначен для работы с файлами, поддерживая произвольный доступ к их содержимому.

Работа с классом `RandomAccessFile` напоминает использование совмещенных в одном классе потоков `DataInputStream` и 
`DataOutputStream` (они реализуют те же интерфейсы `DataInput` и `DataOutput`). Кроме того, метод `seek()` позволяет 
переместиться к определенной позиции и изменить хранящееся там значение.

При использовании `RandomAccessFile` необходимо знать структуру файла. Класс `RandomAccessFile` содержит методы для 
чтения и записи примитивов и строк UTF-8.

[к оглавлению](#IO)

## 30. Какой класс надстройка позволяет читать данные из входного байтового потока в формате примитивных типов данных?

Для чтения байтовых данных (не строк) применяется класс `DataInputStream`. В этом случае необходимо использовать 
классы из группы `InputStream`.

Для преобразования строки в массив байтов, пригодный для помещения в поток `ByteArrayInputStream`, в классе `String` 
предусмотрен метод `getBytes()`. Полученный `ByteArrayInputStream` представляет собой поток `InputStream`, подходящий 
для передачи `DataInputStream`.

При побайтовом чтении символов из форматированного потока `DataInputStream` методом `readByte()` любое полученное 
значение будет считаться действительным, поэтому возвращаемое значение неприменимо для идентификации конца потока. 
Вместо этого можно использовать метод `available()`, который сообщает, сколько еще осталось символов.

Класс `DataInputStream` позволяет читать элементарные данные из потока через интерфейс `DataInput`, который определяет 
методы, преобразующие элементарные значения в форму последовательности байтов. Такие потоки облегчают сохранение в 
файле двоичных данных.

Конструктор: `DataInputStream(InputStream stream);`

Методы: `readDouble()`, `readBoolean()`, `readInt()`.

[к оглавлению](#IO)

## 31. Какой класс надстройка позволяет ускорить чтение/запись за счет использования буфера?

Для этого используются классы, позволяющие буферизировать поток:
+ `java.io.BufferedInputStream(InputStream in)` || `BufferedInputStream(InputStream in, int size)`,
+ `java.io.BufferedOutputStream(OutputStream out)` || `BufferedOutputStream(OutputStream out, int size)`,
+ `java.io.BufferedReader(Reader r)` || `BufferedReader(Reader in, int sz)`,
+ `java.io.BufferedWriter(Writer out)` || `BufferedWriter(Writer out, int sz)`

[к оглавлению](#IO)

## 32. Какие классы позволяют преобразовать байтовые потоки в символьные и обратно?

`OutputStreamWriter` — мост между классом `OutputStream` и классом `Writer`. 
Символы, записанные в поток, преобразовываются в байты.

```java
OutputStream outputStream = new FileOutputStream("c:\\data\\output.txt");
Writer  outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
outputStreamWriter.write("Hello World");
outputStreamWriter.close();
```

`InputStreamReader` — аналог для чтения. 
При помощи методов класса `Reader` читаются байты из потока `InputStream` и далее преобразуются в символы.

```java
InputStream inputStream  = new FileInputStream("c:\\data\\input.txt");
Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
int data = inputStreamReader.read();
while(data != -1) {
    char theChar = (char) data;
    data = inputStreamReader.read();
}
inputStreamReader.close();
```
 
[к оглавлению](#IO)

## 33. Какой символ является разделителем при указании пути к ЭФС?

Для различных систем символ разделителя различается. Вытащить его можно используя `file.separator`, 
а также в статическом поле `File.separator`.  Для Windows это ‘\’.

[к оглавлению](#IO)

## 34. Что вы знаете об интерфейсе FilenameFilter?

Интерфейс `FilenameFilter` применяется для проверки попадает ли объект `File` под некоторое условие. 
Этот интерфейс содержит единственный метод `boolean accept(File pathName)`. 
Этот метод необходимо переопределить и реализовать.

[к оглавлению](#IO)

## 35. Что такое сериализация?

**Сериализация** - это процесс сохранения состояния объекта в последовательность байт. 

**Десериализация** - это процесс восстановления объекта, из этих байт. 

Java Serialization API предоставляет стандартный механизм для создания сериализуемых объектов.

[к оглавлению](#IO)