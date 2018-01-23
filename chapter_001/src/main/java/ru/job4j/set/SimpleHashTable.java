package ru.job4j.set;


import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class SimpleHashTable<E> {

    private Object [] table;

    private int count;

    private float loadFactor;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public SimpleHashTable() {
        loadFactor = 0.75f;
        table = new Object[11];
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return (hash & 0x7FFFFFFF) % table.length;
    }

    // return false, if collision
    public boolean add (E e) {
        int index = indexFor(hash(e));
        Object o = table[index];
        boolean isNew = (o == null);
        if (isNew) {
            count++;
        } else if (o.equals(e)) {
            return true;
        }
        table[index] = e;
        if (count >= table.length * loadFactor) {
            resizeTable();
        }
        return isNew;
    }

    // return false, if table don't contains element
    public boolean remove (E e) {
        if (!contains(e)) {
            return false;
        }
        int index = indexFor(hash(e));
        table[index] = null;
        count--;
        return true;

    }

    public boolean contains (E e) {
        int index = indexFor(hash(e));
        return e.equals((E) table[index]);
    }

    public int size() {
        return count;
    }

    private void resizeTable() {
        int newCapacity = Math.min(MAX_ARRAY_SIZE, ((table.length << 1) + 1));
        if (newCapacity <= table.length) {
            throw new OutOfMemoryError();
        }
        Object [] tab = Arrays.copyOf(table, table.length);
        table = new Object[newCapacity];
        count = 0;
        for (Object o : tab) {
            if (o != null) {
                add((E) o);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Object o : table) {
            String name = o == null ? "" : ((E) o).toString();
            builder.append(i++ + " " + name + "\n");
        }
        return builder.toString();
    }
}
