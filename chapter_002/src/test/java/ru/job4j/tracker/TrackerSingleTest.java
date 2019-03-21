package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 22.03.2019
 */
public class TrackerSingleTest {

    /**
     * singlet test - при нескольких созданиях нового Tracker, будет возвращаться один и тот же объект.
     */
    @Test
    public void whenUseTrackerSingle() {
        TrackerSingle tracker1 = TrackerSingle.INSTANCE;
        TrackerSingle tracker2 = TrackerSingle.INSTANCE;
        assertThat(tracker1 == tracker2, is(true));
    }

    @Test
    public void whenUseTrackerSingleStaticField() {
        Tracker tracker3 = TrackerSingleStaticField.getInstance();
        Tracker tracker4 = TrackerSingleStaticField.getInstance();
        assertThat(tracker3 == tracker4, is(true));
    }

    @Test
    public void whenUseTrackerSingleStaticFinalField() {
        Tracker tracker5 = TrackerSingleStaticFinalField.getInstance();
        Tracker tracker6 = TrackerSingleStaticFinalField.getInstance();
        assertThat(tracker5 == tracker6, is(true));
    }

    @Test
    public void whenUseTrackerSingleStaticFinalClass() {
        Tracker tracker7 = TrackerSingleStaticFinalClass.getInstance();
        Tracker tracker8 = TrackerSingleStaticFinalClass.getInstance();
        assertThat(tracker7 == tracker8, is(true));
    }
}
