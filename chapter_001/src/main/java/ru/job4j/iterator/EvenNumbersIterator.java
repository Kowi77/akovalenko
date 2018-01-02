package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {

    private final int[] array;
    private int index = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return hasEven();
    }

    @Override
    public Object next() {
        if (hasEven()) {
            return array[index++];
        }
        throw new NoSuchElementException();
    }

    private boolean hasEven() {
        for (int i = index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                index = i;
                return true;
            }
        }
        return false;
    }
}
