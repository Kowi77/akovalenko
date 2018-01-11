package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<E> implements Iterable<E>{

    Object[] container;
    int size = 0;
    int modCount;
    public static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public SimpleArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public void add(E value) {
        if (container.length <= size) {
            growCapacity();
        }
        this.container[size++]= value;
        modCount++;
    }

    public E get(int position) {
        isValidPosition(position);
        return (E) this.container[position];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;
            int expectedModCount = modCount;

            public boolean hasNext() {
                return cursor != size;
            }

            public E next() {
                checkForComodification();
                if (cursor >= size)
                    throw new NoSuchElementException();
                return (E) container[cursor++];
            }
            final void checkForComodification() {
                if (modCount != expectedModCount)
                    throw new ConcurrentModificationException();
            }
        };
    }

    private void isValidPosition(int position) {
        if (position < 0 || position >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void growCapacity() {
        if (size >= MAX_ARRAY_SIZE) {
            throw new OutOfMemoryError();
        }
        int newCapacity = container.length + (container.length >> 1);
        newCapacity = Math.min(newCapacity, MAX_ARRAY_SIZE);
        container = Arrays.copyOf(container, newCapacity);
    }
}
