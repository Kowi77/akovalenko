package ru.job4j.generic;

import java.util.NoSuchElementException;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    SimpleArray<T> array;
    Integer size;

    public AbstractStore(Integer size) {
        this.size = size;
        array = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = true;
        try {
            array.update(model, getIndex(id));
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = true;
        try {
            array.delete(getIndex(id));
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        return array.get(getIndex(id));
    }

    private Integer getIndex(String id) {
        for (int i = 0; i < size; i++) {
            if (array.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }


}
