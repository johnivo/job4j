package ru.job4j.crud.store;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.01.2020
 */
public class LocationStoreTest {

    LocationStore locations = LocationStore.getInstance();

    @Test
    public void whenAddCountryThanTrue() {
        locations.addCountry("Norway");
        List<String> result = locations.getCountries();
        assertThat(result.contains("Norway"), is(true));
    }

    @Test
    public void whenAddOneCityThenTrue() {
        locations.addCity("Finland", "Helsinki");
        List<String> cities = locations.getCities("Finland");
        assertThat(locations.getCountries().contains("Finland"), is(true));
        assertThat(cities.get(0), is("Helsinki"));
    }

    @Test
    public void whenChecksSizeOfUKStorageThanTwo() {
        locations.addCity("UK", "London");
        locations.addCity("UK", "Liverpool");
        List<String> cities = locations.getCities("UK");
        assertThat(cities.size(), is(2));
    }

}