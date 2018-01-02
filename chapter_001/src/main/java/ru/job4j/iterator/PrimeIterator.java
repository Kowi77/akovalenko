package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {

    private final int[] array;
    private int index = 0;

    public PrimeIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return hasPrime();
    }

    @Override
    public Integer next() {
        if (hasPrime()) {
            return array[index++];
        }
        throw new NoSuchElementException();
    }

    private boolean hasPrime() {
        outer:  for (int i = index; i < array.length; i++) {
            if (array[i] == 2) {
                index = i;
                return true;
            }
            for (int j = 2; j <= array[i] - 1; j++) {
                if (array[i] % j == 0) {
                    continue outer;
                }
                index = i;
                return true;
            }
        }
        return false;
    }
}
