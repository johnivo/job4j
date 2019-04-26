package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * Элементарная структура дерева.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 21.04.2019
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Корневой элемент дерева.
     */
    private Node<E> root;

    /**
     * Счётчик структурных изменений (для реализации fail-fast поведения итератора).
     */
    private int modCount;

    /**
     * Конструктор формирует дерево с указанным корневым элементом.
     *
     * @param root корневой элемент.
     */
    Tree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * Находит в дереве узел, соответствующий заданному условию.
     *
     * @param predicate заданное условие.
     * @return контейнер Optional (содержит искомый узел, при его наличии).
     */
    public Optional<Node<E>> findNode(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Осуществляет поиск в дереве узла с заданным ключом.
     *
     * @param value заданное значение/ключ.
     * @return контейнер Optional (содержит искомый элемент, при его наличии).
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return this.findNode(node -> node.eqValue(value));

    }

    /**
     * Находит родительский элемент parent в дереве и добавляет в него дочерний элемент child.
     *
     * @param parent уже существующий родитель.
     * @param child добавляемый потомок.
     * @return true если добавлен.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> checkParent = findBy(parent);
        Optional<Node<E>> checkChild = findBy(child);
        if (!checkChild.isPresent()) {
            checkParent.ifPresent(node -> node.add(new Node<>(child)));
            rsl = true;
            this.modCount++;
        }
        return rsl;
    }

    /**
     * Проверяет количество дочерних элементов в каждом узле дерева. Если во всех узлах <= 2 - то дерево бинарное.
     *
     * @return true если бинарное.
     */
    public boolean isBinary() {
        return !this.findNode(node -> node.leaves().size() > 2).isPresent();
    }

//    public boolean isBinary() {
//        boolean rsl = true;
//        Iterator<E> it = iterator();
//        while (it.hasNext()) {
//            int children = findBy(it.next()).get().leaves().size();
//            if (children > 2) {
//                rsl = false;
//                break;
//            }
//        }
//        return rsl;
//    }

    /**
     * Возвращает итератор для последовательного прохода в ширину по элементам дерева.
     *
     * @return переопределенный итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Queue<Node<E>> data = new LinkedList<>(List.of(Tree.this.root));
            private int expectedModCount = Tree.this.modCount;

            @Override
            public boolean hasNext() {
                return !this.data.isEmpty();
            }

            @Override
            public E next() {
                if (this.expectedModCount != Tree.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> node = this.data.remove();
                this.data.addAll(node.leaves());
                return node.getValue();
            }
        };
    }
}
