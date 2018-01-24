package ru.job4j.map;

import java.util.*;

public class SimpleHashMap<K, V> {

    private Node<K, V>[] table;

    private int size;

    private int modCount;

    private float loadFactor;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public SimpleHashMap() {
        loadFactor = 0.75f;
        table = new Node[11];
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return (hash & 0x7FFFFFFF) % table.length;
    }

    // return false, if key exist
    public boolean insert (K key, V value) {
        int index = indexFor(hash(key));
        Node<K, V> node = table[index];
        Node<K, V> currentNode;
        if (node == null) {
            System.out.println(key + "in cell " + index);
            table[index] = new Node<>(hash(key), key, value, null);
            size++;
            modCount++;
            return true;
        } else {
            currentNode = node;
            while (currentNode != null) {
                if (currentNode.hash == hash(key) && currentNode.key.equals(key)) {
                    return false;
                }
                currentNode = currentNode.next;
            }
        }
        System.out.println(key + "in cell " + index);
        currentNode = new Node<>(hash(key), key, value, node);
        size++;
        modCount++;
        table[index] = currentNode;

        if (size >= table.length * loadFactor) {
            resizeTable();
        }

        return true;
    }

    // return false, if map don't contains key
    public boolean delete (K key) {
        int index = indexFor(hash(key));
        Node<K, V> node = table[index];
        Node<K, V> newNode;
        if (node == null) {
            return false;
        }
        if (node.hash == hash(key) && node.key.equals(key)) {
            System.out.println(key);
            table[index] = node.next;
            size--;
            modCount++;
            return true;
        }
        newNode = node;
        while (node.next != null) {
            if (node.next.hash == hash(key) && node.next.key.equals(key)) {
                System.out.println(key);
                newNode.next = node.next.next;
                table[index] = newNode;
                size--;
                modCount++;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public V get(K key) {
        int index = indexFor(hash(key));
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.hash == hash(key) && node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private void resizeTable() {
        System.out.println("RESIZE!");
        int newCapacity = Math.min(MAX_ARRAY_SIZE, ((table.length << 1) + 1));
        if (newCapacity <= table.length) {
            throw new OutOfMemoryError();
        }
        Node [] oldMap = Arrays.copyOf(table, table.length);
        for (Node node : oldMap) {
            System.out.println(node);
        }
        table = new Node[newCapacity];
        size = 0;
        for (int i = 0; i < oldMap.length; i++) {
            Node<K, V> node = oldMap[i];
            while (node != null) {
                insert(node.key, node.value);
                node = node.next;
            }
        }
        System.out.println(size);
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            int cursor = -1;
            int count = 0;
            int expectedModCount = modCount;
            Node<K, V> currentNode;

            public boolean hasNext() {
                return count < size;
            }

            public Map.Entry<K, V> next() {
                checkForComodification();
                if (count >= size) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result;
                if (currentNode != null) {
                    result = currentNode;
                    currentNode = currentNode.next;
                    count++;
                    return result;
                } else {
                    cursor++;
                    while (cursor < table.length && table[cursor] == null) {
                        cursor++;
                    }
                }
                result = table[cursor];
                currentNode = result.next;
                count++;
                return result;
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /*@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Object o : table) {
            String name = o == null ? "" : ((E) o).toString();
            builder.append(i++ + " " + name + "\n");
        }
        return builder.toString();
    }*/
}
