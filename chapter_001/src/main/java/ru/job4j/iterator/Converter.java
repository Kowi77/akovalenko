package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        if (it == null) {
            return null;
        }

        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator  = it.next();

            @Override
            public boolean hasNext() {
                if (currentIterator == null) {
                    return false;
                }

                if (currentIterator.hasNext()) {
                    return true;
                }
                currentIterator = nextIterator(it);

                if (currentIterator != null && currentIterator.hasNext()) {
                    return true;
                }
                return false;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return currentIterator.next();
                }
                throw new NoSuchElementException();
            }
        };
    }

    private Iterator<Integer> nextIterator(Iterator<Iterator<Integer>> iterator) {
        Iterator<Integer> newIterator = null;
        while (iterator.hasNext()) {
            newIterator = iterator.next();
            if (newIterator.hasNext()) {
                break;
            }
        }
        return newIterator;
    }


}
