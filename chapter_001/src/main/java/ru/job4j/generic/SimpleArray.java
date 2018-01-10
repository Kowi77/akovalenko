package ru.job4j.generic;

public class SimpleArray<E> {

    Object[] objects;
    int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(E value) {
        isValidPosition(index);
        this.objects[index++] = value;
    }

    public E get(int position) {
        isValidPosition(position);
        return (E) this.objects[position];
    }

    public void delete(int position) {
        isValidPosition(position);
        if (position < index) {
            index--;
            for (int i = position; i < index; i++) {
                update(get(i + 1), i);
            }
        }
    }

    public void update(E value, int position) {
        isValidPosition(position);
        if (position <= index) {
            this.objects[position] = value;
        }
    }

    private void isValidPosition(int position) {
        if (position < 0 || position >= objects.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
