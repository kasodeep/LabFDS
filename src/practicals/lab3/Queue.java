package practicals.lab3;

/**
 * Interface for implementing abstract data type Queue.
 */
public interface Queue<E> {

    /**
     * Returns the size of the queue.
     */
    int size();

    /**
     * Returns true if queue id empty else false.
     */
    boolean isEmpty();

    /**
     * Pushes an element on the back of the Queue.
     */
    void enqueue(E e);

    /**
     * Returns an element at the front of the Queue.
     */
    E first();

    /**
     * Removes an element from the front of the Queue.
     */
    E dequeue();
}
