package ru.job4j.nonblockingalgorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 20.10.2019
 */
public class NonBlockCacheTest {

    @Test
    public void whenAdd2BaseThenCacheSizeIs2() {
        NonBlockCache cache = new NonBlockCache();

        cache.add(new Base(1, 0));
        cache.add(new Base(2, 0));

        assertThat(cache.size(), is(2));
    }

    @Test
    public void whenAdd2BasesAndRemove1BaseThenCacheSizeIs1() {
        NonBlockCache cache = new NonBlockCache();
        Base model1 = new Base(1, 0);
        Base model2 = new Base(2, 0);
        cache.add(model1);
        cache.add(model2);

        cache.delete(model2);

        assertThat(cache.size(), is(1));
    }

    @Test
    public void whenAddBaseAndUpdateTwiceThenVersionUpdatedTo2() {
        NonBlockCache cache = new NonBlockCache();
        Base model = new Base(1, 0);
        cache.add(model);

        assertTrue(cache.update(model));
        assertTrue(cache.update(model));
        assertThat(cache.getBase(1).getVersion(), is(2));
    }

    @Test(expected = OptimisticException.class)
    public void whenTwiceUpdateWithoutChangingStateThenThrowsOptEx() {
        NonBlockCache cache = new NonBlockCache();
        Base model = new Base(1, 0);
        cache.add(model);

        cache.update(model);
        cache.update(new Base(1, 0));
    }

    @Test
    public void whenThrowException2() throws InterruptedException {
        //Тест выполняется успешно. Это связано с тем, что главная нить не видит, что происходит во второстепенной нити.
        Thread thread = new Thread(
                () -> {
                    throw new RuntimeException("Throw Exception in Thread");
                }
        );
        thread.start();
        thread.join();
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        //Чтобы это поправить нам нужно передать исключение к главную нить.
        //Теперь мы можем проверить, что такой код падает.
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

    @Test
    public void whenConcurrencyUpdatingInTwoThreadsThenThrowsOptEx() throws InterruptedException {
        NonBlockCache cache = new NonBlockCache();
        Base model = new Base(1, 0);
        cache.add(model);

        //Нить 1 изменила объект 1, тогда version должно стать 1.
        //Нить 2 в это же время изменила объект 1, тут тоже самое version станет 1.
        //Объекты 1 - создаются в разной области памяти. По сути эти два разных объекта с одинаковыми полями.
        //Когда нить 1 будет обновлять данные, обновление пройдет успешно. потому что данные в кеше будут на единицу отличаться.
        //С другой стороны нить 2 выкинет исключение. потому, что версия в кеше не будет соответствовать текущей версии.

        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    try {
                        cache.update(model);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, 0));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Assert.assertThat(ex.get().getMessage(), is("versions do not match"));
    }

}