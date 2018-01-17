package ru.job4j.list;

public class Node<T> {

    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public static boolean hasCycle(Node node) {
        SimpleArrayList<Node> nodes = new SimpleArrayList<>();
        Node currentNode = node;
        while (currentNode != null && currentNode.next != null) {
            nodes.add(currentNode);
            currentNode = currentNode.next;
            for (Node n : nodes) {
                if (n.equals(currentNode)) {
                    return true;
                }
            }
        }
        return false;
    }


}
