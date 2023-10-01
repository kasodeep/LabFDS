package practicals.lab3;

/**
 * An implementation of a queue data structure using a circular array.
 *
 * <p>This ArrayQueue allows for the storage and retrieval of elements in a
 * first-in, first-out (FIFO) manner. It is designed to efficiently manage a
 * fixed capacity queue and offers methods for adding elements to the rear,
 * retrieving the front element, and removing elements from the front of the queue.
 * The circular array structure allows for efficient utilization of available
 * storage space and minimizes memory wastage.</p>
 *
 * @see #enqueue(Object)
 * @see #first()
 * @see #dequeue()
 * @see #size()
 * @see #isEmpty()
 *
 * @author - Kasodariya Deep
 * @version - 1.0
 * @param <E> The type of elements to be stored in the queue.
 */
public class ArrayQueue<E> implements  Queue<E>{

    // The array to store elements.
    private final E[] data;

    // Index of the front element.
    private int front = 0;

    // Index of the rear element.
    private int rear = -1;

    // The current size of the queue.
    private int size = 0;

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * Returns the current number of elements in the queue.
     *
     * @return The size of the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param e The element to be added to the queue.
     * @throws IllegalStateException If the queue is already full.
     */
    @Override
    public void enqueue(E e) throws IllegalStateException{
        if(size() == data.length) throw new IllegalStateException("Queue is Full");
        rear = (rear + 1) % data.length;
        data[rear] = e;
        size++;
    }

    /**
     * Retrieves the element at the front of the queue without removing it.
     *
     * @return The element at the front of the queue, or null if the queue is empty.
     */
    @Override
    public E first() {
        if(isEmpty()) return null;
        return data[front];
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return The element removed from the front of the queue, or null if the queue is empty.
     */
    @Override
    public E dequeue() {
        if(isEmpty()) return null;
        E dequeued = data[front];
        data[front] = null;

        front = (front + 1) % data.length;
        size--;
        return dequeued;
    }
}
