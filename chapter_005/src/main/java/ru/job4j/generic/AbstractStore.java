package ru.job4j.generic;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.04.2019
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    private final SimpleArray<T> storage;

    public AbstractStore(int size) {
        this.storage = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        this.storage.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int position = this.findIndexByStringId(id);
        if (position != -1) {
            this.storage.set(position, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int position = this.findIndexByStringId(id);
        if (position != -1) {
            this.storage.remove(position);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int position = this.findIndexByStringId(id);
        if (position != -1) {
            result = this.storage.get(position);
        }
        return result;
    }

    public int size() {
        return this.storage.size();
    }

    private int findIndexByStringId(String id) {
        int result = -1;
        for (int index = 0; index != this.storage.size(); index++) {
            if (this.storage.get(index).getId().equals(id)) {
                result = index;
                break;
            }
        }
        return result;
    }
}
