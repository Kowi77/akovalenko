package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            private Iterator<Integer> current = it.next();

            @Override
            public boolean hasNext() {

                if (current.hasNext()) {
                    return true;
                } else {
                    current = nextIterator(it);
                }
                return  (current.hasNext());
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return current.next();
                }
                throw new NoSuchElementException();
            }
        };
    }

    private Iterator<Integer> nextIterator(Iterator<Iterator<Integer>> iterator) {

        Iterator<Integer> it = new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };

        while (iterator.hasNext()) {
            it = iterator.next();
            if (it.hasNext()) {
                break;
            }
        }
        return it;
    }


}
