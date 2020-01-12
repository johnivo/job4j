package ru.job4j.crud.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация кеша для хранения локаций в памяти.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.01.2020
 */
public class LocationStore {

    private String country;
    private String city;

    private static final LocationStore INSTANCE = new LocationStore();

    private final ConcurrentHashMap<String, List<String>> locations = new ConcurrentHashMap<>();

    private LocationStore() {
        //fillStorage();
    }

    public static LocationStore getInstance() {
        return INSTANCE;
    }

    public List<String> getCountries() {
        List<String> result = new ArrayList<>();
        result.addAll(locations.keySet());
        return result;
    }

    public List<String> getCities(String country) {
        return locations.get(country);
    }

    public void addCountry(String country) {
        locations.put(country, new ArrayList<>());
    }

    public void addCity(String country, String city) {
        List<String> cities;
        locations.putIfAbsent(country, new ArrayList<>());
        cities = locations.get(country);
        if (!cities.contains(city)) {
            cities.add(city);
        }
    }

    private void fillStorage() {
        addCity("Russia", "Moscow");
        addCity("Russia", "Vologda");
        addCity("Belarus", "Minsk");
        addCity("Belarus", "Brest");
    }

}
