package ru.job4j.gc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

/**
 * VM options -Xmx8m
 * размер кучи устанавливаем 8MB
 *
 * Далее поочередно устанавливаем параметры для выполнения класса с соответствующим GC:
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseSerialGC
 * -XX:+UseParallelGC
 * -XX:+UseG1GC
 * и наблюдаем сборки мусора и время потраченное на работу GC.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.08.2019
 */
public class MemoryUsage {

    private static final Logger LOG = LogManager.getLogger(MemoryUsage.class.getName());

    public static class User {

        public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(String.format("finalize: destroy object %s", this));
        }

        @Override
        public String toString() {
            return "User{"
                    + "name='"
                    + name
                    + '\''
                    + '}';
        }
    }

    public static class EmptyUser {

    }

    public static void main(String[] args) {

        //Для каждого объекта JVM хранит:
        //1) Заголовок объекта, для большинства JVM(Hotspot, openJVM) состоит из двух машинных слов. 16B
            //Структура заголовка объекта
            //•MarkWord содержит HashCode, GarbageCollectionInformation, Lock.
            //  •HashCode - каждый объект имеет хешкод.
            //  По умолчанию результат вызова метода Object.hashCode() вернет адрес объекта в памяти.
            //  •GarbageCollectionInformation - каждый java объект содержит информацию нужную для системы управления памятью.
            //  •Lock - каждый объект содержит информацию о состоянии блокировки.
            //  Это может быть указатель на объект блокировки или прямое представление блокировки.
            //•TypeInformationBlockPointer - содержит информацию о типе объекта
            // (таблица виртуальных методов, указатель на объект, указатели на некоторые дополнительные структуры)
            //•ArrayLength - если объект является массивом, то заголовок расширяется 4 байтами для хранения длины массива.
        //2) Память для примитивных типов
        //3) Память для ссылочных типов
        //4) Смещение/выравнивание
            //По сути, это несколько неиспользуемых байт, которые размещаются после данных самого объекта.
            // Это сделано для того, чтобы адрес в памяти всегда был кратным машинному слову. Это необходимо для:
            //•ускорения чтения из памяти
            //•уменьшения количества бит для указателя на объект
            //•для уменьшения фрагментации памяти

        //В java размер любого объекта кратен 8 байтам.
        //По спецификации у каждого объекта есть заголовок..
        //Размеры экземпляров классов могут отличатся в зависимости от версии JVM

        //размер объекта типа new User("name") равен 64B
        //Ссылочная переменная и сам объект, на который эта переменная ссылается.
        //Размер = sizeOf(reference) + sizeOf(String"name")
        //16 + 48 = 64B

        System.out.println(String.format("empty object size %s B", sizeOf(new EmptyUser())));
        System.out.println(String.format("String \"name\" size %s B", sizeOf(new String("name"))));
        System.out.println(String.format("user size %s B", sizeOf(new User("name"))));

//        boolean j = true;
//        System.out.println(String.format("boolean size %s B", sizeOf(j)));
//        int a = 5;
//        System.out.println(String.format("int size %s B", sizeOf(a)));
//        System.out.println(String.format("Integer size %s B", sizeOf(new Integer(5))));
//        System.out.println(String.format("String size %s B", sizeOf(new String())));
//        System.out.println(String.format("empty object size %s B", sizeOf(new Object())));

        System.out.println("start");
        info();

        for (int i = 1; i < 1_000_000; i++) {
            User user = new User("u " + i);
            Runtime runtime = Runtime.getRuntime();
            System.out.println(String.format(
                    "Used memory %s B. Average user%s size %s B.",
                    (runtime.totalMemory() - runtime.freeMemory()),
                    i,
                    (runtime.totalMemory() - runtime.freeMemory()) / i)
            );

            System.out.println(String.format("estimated object size %s", sizeOf(user)));
            user = null;
        }

        //System.out.println();
        //info();
        //System.gc();
    }

    /**
     * Выдает статистику использования кучи.
     */
    protected static void info() {

        int kb = 1024;

        //ссылка на среду выполнения из системы.
        //позволяет получать необходимую информацию об использовании JVM памяти
        Runtime runtime = Runtime.getRuntime();

        System.out.println("Heap utilization statistics [KB]");

        System.out.println("Used memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / kb
        );

        System.out.println(
                "Free memory:"
                        + runtime.freeMemory() / kb
        );

        //всего доступно памяти
        System.out.println(
                "Total memory:"
                        + runtime.totalMemory() / kb
        );

        //максимальный объем памяти используемой JVM
        System.out.println(
                "Max memory:"
                        + runtime.maxMemory() / kb
        );
    }

}
