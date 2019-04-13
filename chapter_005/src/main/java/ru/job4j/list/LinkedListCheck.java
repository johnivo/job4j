package ru.job4j.list;

/**
 * Класс определяет цикличность в связанном списке.
 * Для поиска цикла использован алгоритм Флойда "Черепаха и заяц".
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.04.2019
 */
public class LinkedListCheck {

    /**
     * Метод определяет наличие цикличности в связном списке.
     *
     * @param first head-элемент списка.
     * @return true, если есть замыкание.
     */
    public <E> boolean hasCycle(Node<E> first) {
        Node<E> slow = first;
        Node<E> fast = first;
        boolean result = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}

/**
 * Элементарная реализация связанного списка.
 */
class Node<E> {
    E value;
    Node<E> next;

    public Node(E value) {
        this.value = value;
    }
}

