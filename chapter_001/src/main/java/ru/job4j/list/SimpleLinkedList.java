package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    private int modCount = 0;

    public SimpleLinkedList() {
    }

    public void add(E value) {
        final Node<E> lastNode = last;
        final Node<E> newNode = new Node<>(lastNode, value, null);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
       if (index < 0 || index >= size) {
           throw new ArrayIndexOutOfBoundsException();
       }
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.item;
    }

    public E removeFirst() {
        if (first == null) {
            return null;
        }
        final E element = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    public E removeLast() {
        if (last == null) {
            return null;
        }
        final E element = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    public boolean contains(Object o) {
        for (Node<E> x = first; x != null; x = x.next) {
            if ((o == null && x.item == null) || o.equals(x.item)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = first;
            int cursor = 0;
            int expectedModCount = modCount;

            public boolean hasNext() {
                return cursor < size;
            }

            public E next() {
                checkForComodification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E item = current.item;
                current = current.next;
                cursor++;
                return item;
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };

    }
}
