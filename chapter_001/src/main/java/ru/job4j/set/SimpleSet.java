package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArrayList<E> list;

    public SimpleSet() {
        this.list = new SimpleArrayList<>();
    }

    public void add(E e) {
        if (!list.contains(e)) {
            list.add(e);
        }
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
