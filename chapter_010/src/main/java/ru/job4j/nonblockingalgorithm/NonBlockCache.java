package ru.job4j.nonblockingalgorithm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 20.10.2019
 */
public class NonBlockCache {

    private final ConcurrentHashMap<Integer, Base> cache;

    public NonBlockCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public boolean add(Base model) {
        boolean rst = false;

        if (!cache.containsKey(model.getId())) {
            cache.put(model.getId(), model);
            rst = true;
        }

        return rst;
    }

    public boolean update(Base model) {
        boolean rst = false;

        if (cache.computeIfPresent(model.getId(), (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                        throw new OptimisticException("versions do not match");
                    } else {
                        model.setVersion(model.getVersion() + 1);
                        return model;
                    }
                }
        ) != null) {
            rst = true;
        }

        return rst;
    }

    public boolean delete(Base model) {
        boolean rst = false;

        if (cache.containsKey(model.getId())) {
            cache.remove(model.getId());
            rst = true;
        }

        return rst;
    }

    public Integer size() {
        return cache.size();
    }

    public Base getBase(int key) {
        return cache.get(key);
    }

}
