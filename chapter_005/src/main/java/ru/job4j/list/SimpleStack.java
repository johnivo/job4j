package ru.job4j.list;

/**
 * Динамический контейнер типа Stack на базе связанного списка.
 * LIFO - last input first output.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 12.04.2019
 */
public class SimpleStack<E> {

    /**
     * Используем функциональные возможности класса UnidirectionalLinkedList.
     * Представляющем реализацию односвязнного списка.
     */
    private UnidirectionalLinkedList<E> linkedList = new UnidirectionalLinkedList();

    /**
     * Возвращает верхний элемент стека, удаляя его из коллекции.
     *
     * @return верхний элемент (null, при пустом стеке).
     */
    public E poll() {
//        int lastIndex = linkedList.getSize() - 1;
//        E result = linkedList.get(lastIndex);
//        linkedList.delete();
//        return result;
        return linkedList.delete();
    }

    /**
     * Добавляет в начало стека новый элемент.
     *
     * @param value добавляемое значение.
     */
    public void push(E value) {
        linkedList.add(value);
    }

    /**
     * Вспомогательный метод, получает размер стека.
     */
    public int size() {
        return linkedList.getSize();
    }

}
