/**
 * Implementation of a Queue using a Circular Doubly Linked List.
 *
 * @param <T> the type of elements stored in the queue
 */

public class Queue<T> {
    private CircularDoublyLinkedList<T> list;
    /**
     * Constructs an empty queue.
     */
    public Queue() {
        list = new CircularDoublyLinkedList<>();
    }
    /**
     * Adds an element to the end of the queue.
     *
     * @param data the data to be added
     */
    public void enqueue(T data) {
        list.insertAtEnd(data);
    }
    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the removed element
     * @throws IllegalStateException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.removeAtBeginning();
    }
    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.removeAtBeginning();
    }
    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
    /**
     * Returns the size of the queue.
     *
     * @return the size of the queue
     */
    public int size() {
        return list.size();
    }
}
