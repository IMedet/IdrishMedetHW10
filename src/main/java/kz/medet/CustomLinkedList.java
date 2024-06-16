package kz.medet;

import java.util.List;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        this.size = 0;
    }

    public CustomLinkedList(List<T> values) {
        this();
        for (T value : values) {
            add(value);
        }
    }

    public void print() {
        if (head != null) {
            Node<T> currentNode = head;
            System.out.print("[");
            while (currentNode.getNextNode() != null) {
                System.out.print(currentNode.getData() + ", ");
                currentNode = currentNode.getNextNode();
            }
            System.out.println(currentNode.getData() + "]");
        } else {
            System.out.println("[]");
        }
    }

    public void add(int index, T data) {
        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            newNode.setNextNode(head);
            head = newNode;
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }

        size++;
    }

    public void add(T data) {
        Node<T> node = new Node<>(data);

        if (this.head == null) {
            this.head = node;
        } else {
            Node<T> currentNode = head;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(node);
        }

        size++;
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }

        if (head.getData().equals(data)) {
            head = head.getNextNode();
            size--;
            return;
        }

        Node<T> currentNode = head;
        while (currentNode.getNextNode() != null && !currentNode.getNextNode().getData().equals(data)) {
            currentNode = currentNode.getNextNode();
        }

        if (currentNode.getNextNode() != null) {
            currentNode.setNextNode(currentNode.getNextNode().getNextNode());
            size--;
        }
    }

    public void clear() {
        Node<T> currentNode = head;

        while (currentNode != null) {
            Node<T> nextNode = currentNode.getNextNode();
            currentNode.setNextNode(null);
            currentNode = nextNode;
        }

        head = null;
        size = 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong interval");
        }

        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getData();
    }

    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("List is Empty");
        }

        return head.getData();
    }

    public T getLast() {
        if (head == null) {
            throw new IllegalStateException("List is Empty");
        }

        Node<T> currentNode = head;
        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode.getData();
    }

    public boolean contains(T data) {
        if (head == null) {
            throw new IllegalStateException("List is Empty");
        }

        Node<T> currentNode = head;

        if (currentNode.getData().equals(data)) {
            return true;
        }

        while (currentNode.getNextNode() != null && !currentNode.getNextNode().getData().equals(data)) {
            currentNode = currentNode.getNextNode();
        }

        if (currentNode.getNextNode() != null && currentNode.getNextNode().getData().equals(data)) {
            return true;
        }

        return false;
    }

    public int indexOf(T data) {
        if (head == null) {
            throw new IllegalStateException("List is Empty");
        }

        int index = 0;
        Node<T> currentNode = head;

        while (currentNode != null && !currentNode.getData().equals(data)) {
            Node<T> nextNode = currentNode.getNextNode();
            currentNode = nextNode;
            index++;
        }

        if (currentNode != null) {
            return index;
        } else {
            throw new IllegalStateException("No such Element");
        }
    }

    public void removeByIndex(int index) {
        if (head == null) {
            throw new IllegalStateException("List is Empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            head = head.getNextNode();
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }

            Node<T> nodeToRemove = currentNode.getNextNode();
            currentNode.setNextNode(nodeToRemove.getNextNode());
        }

        size--;
    }

    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is Empty");
        }

        head = head.getNextNode();
        size--;
    }

    public void removeLast() {
        if (head == null) {
            throw new IllegalStateException("List is Empty");
        }

        if (head.getNextNode() == null) {
            head = null;
        } else {
            Node<T> currentNode = head;
            while (currentNode.getNextNode().getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(null);
        }

        size--;
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }

        currentNode.setData(data);
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            array[index++] = currentNode.getData();
            currentNode = currentNode.getNextNode();
        }
        return array;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}
