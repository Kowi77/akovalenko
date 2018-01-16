package ru.job4j.list;

import java.util.EmptyStackException;

public class SimpleStack<T> {

    private SimpleLinkedList<T> linkedList;

    public SimpleStack() {
        this.linkedList = new SimpleLinkedList<>();
    }

    public void push(T value) {
        linkedList.add(value);
    }

    public <T> T poll() {
        if (size() < 1) {
            throw new EmptyStackException();
        }
        return (T) linkedList.removeLast();
    }

    public int size() {
        return linkedList.size();
    }
}
