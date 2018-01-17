package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;

public class SimpleLinkedSet<E> implements Iterable<E> {

    private SimpleLinkedList<E> list;

    public SimpleLinkedSet() {
        this.list = new SimpleLinkedList<>();
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
