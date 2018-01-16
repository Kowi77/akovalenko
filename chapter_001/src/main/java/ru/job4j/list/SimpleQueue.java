package ru.job4j.list;

public class SimpleQueue<T> {

    private SimpleLinkedList<T> linkedList;

    public SimpleQueue() {
        this.linkedList = new SimpleLinkedList<>();
    }

    public void push(T value) {
        linkedList.add(value);
    }

    //In interface Queue: "@return the head of this queue, or {@code null} if this queue is empty"
    public <T> T poll() {
        return (T) linkedList.removeFirst();
    }

    public int size() {
        return linkedList.size();
    }
}
