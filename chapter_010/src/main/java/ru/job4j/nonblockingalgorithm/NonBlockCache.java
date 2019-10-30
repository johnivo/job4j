package ru.job4j.nonblockingalgorithm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 20.10.2019
 */
public class NonBlockCache {

    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    public boolean update(Base model) {
        boolean rst = false;

        if (cache.computeIfPresent(model.getId(), (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                        throw new OptimisticException("versions do not match");
                    }
                    model.setVersion(model.getVersion() + 1);
                    return model;
                }
        ) != null) {
            rst = true;
        }

        return rst;
    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }

    public Integer size() {
        return cache.size();
    }

    public Base getBase(int key) {
        return cache.get(key);
    }

}
