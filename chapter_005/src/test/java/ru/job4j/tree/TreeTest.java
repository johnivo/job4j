package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 21.04.2019
 */
public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        assertThat(tree.add(5, 6), is(true));
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void whenAllElementsFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(2, 4);
        assertThat(tree.findBy(7).isPresent(), is(false));
        assertFalse(tree.findBy(5).isPresent());
    }

    @Test
    public void whenAddDuplicateElementThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        tree.add(3, 6);
        Optional<Node<Integer>> check = tree.findBy(1);
        for (Node<Integer> node : check.get().leaves()) {
            System.out.println(node.getValue());
        }
        assertThat(tree.add(3, 6), is(false));
        assertThat(tree.add(3, 3), is(false));
        assertThat(tree.add(3, 1), is(false));
    }

    @Test
    public void testForIndependenceNextAndHasNext() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenNewElAddedNextInvocationShouldThrowCMW() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator<Integer> iterator = tree.iterator();
        tree.add(3, 5);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNewElAddedHasNextInvocationShouldThrowCMW() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        Iterator<Integer> iterator = tree.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test
    public void whenChildrenAmountTwoOrLessThenIsBinaryReturnsTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenThreeChildrenThenIsBinaryReturnsFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary(), is(false));
    }
}