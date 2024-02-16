/**
 * Implementation of a Stack using a Circular Doubly Linked List.
 *
 * @param <T> the type of elements stored in the stack
 */
public class Stack<T> {
    private CircularDoublyLinkedList<T> list;
    /**
     * Constructs an empty stack.
     */
    public Stack() {
        list = new CircularDoublyLinkedList<>();
    }
    /**
     * Pushes an element onto the top of the stack.
     *
     * @param data the data to be pushed
     */
    public void push(T data) {
        list.insertAtEnd(data);
    }
    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the removed element
     * @throws IllegalStateException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.removeAtEnd();
    }
    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.removeAtEnd();
    }
    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
    /**
     * Returns the size of the stack.
     *
     * @return the size of the stack
     */
    public int size() {
        return list.size();
    }
}
