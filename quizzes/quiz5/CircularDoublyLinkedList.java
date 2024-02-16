/**
 * Implementation of a Circular Doubly Linked List.
 *
 * @param <T> the type of elements stored in the list
 */
public class CircularDoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    /**
     * Inner class representing a node in the linked list.
     *
     * @param <T> the type of data stored in the node
     */
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
    /**
     * Inserts an element at the beginning of the list.
     *
     * @param data the data to be inserted
     */
    public void insertAtBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }
    /**
     * Inserts an element at the end of the list.
     *
     * @param data the data to be inserted
     */
    public void insertAtEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    /**
     * Removes and returns the element at the beginning of the list.
     *
     * @return the removed element
     * @throws IllegalStateException if the list is empty
     */
    public T removeAtBeginning() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        T data = head.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        size--;
        return data;
    }
    /**
     * Removes and returns the element at the end of the list.
     *
     * @return the removed element
     * @throws IllegalStateException if the list is empty
     */
    public T removeAtEnd() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        T data = tail.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        size--;
        return data;
    }
    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }
}
