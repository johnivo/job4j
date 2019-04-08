package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.04.2019
 */
public class AbstractStoreTest {

    private UserStore storeUser;
    private RoleStore storeRole;

    @Before
    public void setUp() {
        this.storeUser = new UserStore(10);
        storeUser.add(new User("One"));
        storeUser.add(new User("Two"));
        this.storeRole = new RoleStore(10);
        storeRole.add(new Role("1"));
        storeRole.add(new Role("2"));
        storeRole.add(new Role("3"));
    }

    @Test
    public void whenAddElementThenSizeIncreases() {
        storeUser.add(new User("Three"));
        assertThat(storeUser.size(), is(3));
        storeRole.add(new Role("4"));
        assertThat(storeRole.size(), is(4));
    }

    @Test
    public void whenFindElementWithUncorrectIdThenNull() {
        User expected = null;
        assertThat(storeUser.findById("Three"), is(expected));
    }

    @Test
    public void whenFindElementWithCorrectIdThenThatElement() {
        assertThat(storeRole.findById("3").getId(), is("3"));
    }


    @Test
    public void whenReplaceElementThenGetNewElement() {
        storeUser.replace("Two", new User("Three"));
        assertThat(storeUser.findById("Three").getId(), is("Three"));
    }

    @Test
    public void whenDeleteElementThenSizeDecreases() {
        assertThat(storeUser.delete("One"), is(true));
        assertThat(storeRole.delete("2"), is(true));
    }
}