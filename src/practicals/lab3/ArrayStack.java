package practicals.lab3;

/**
 * An implementation of a stack data structure using an array.
 *
 * <p>This ArrayStack allows for the storage and retrieval of elements in a
 * last-in, first-out (LIFO) manner. It is designed to efficiently manage a
 * fixed capacity stack and offers methods for adding elements to the top,
 * retrieving the top element, and removing elements from the top of the stack. </p>
 *
 * @see #push(Object) - Push an element on top of stack.
 * @see #top() - Get the top element of the stack.
 * @see #pop() - Pop the top stack element.
 * @see #size() - To get the size of stack.
 * @see #isEmpty() - To check if the stack is empty or not.
 *
 * @author - Kasodariya Deep
 * @version - 1.0
 * @param <E> The type of elements to be stored in the stack.
 */
public class ArrayStack<E> implements Stack<E>{

    // The array to store elements.
    private final E[] data;

    // Index of the top element.
    private int t = -1;

    /**
     * Constructs a new ArrayStack with the specified capacity.
     *
     * @param capacity The maximum number of elements that can be stored in the stack.
     */
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * Returns the current number of elements in the stack.
     *
     * @return The size of the stack.
     */
    @Override
    public int size() {
        return t + 1;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    /**
     * Pushes an element to the top of the stack.
     *
     * @param e The element to be pushed onto the top of the stack.
     * @throws StackOverflowError If the stack is already full.
     */
    @Override
    public void push(E e) throws StackOverflowError {
       if(size() == data.length){
           throw new StackOverflowError();
       }
       data[++t] = e;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return The element at the top of the stack, or null if the stack is empty.
     */
    @Override
    public E top() {
        if(isEmpty()) return null;
        return data[t];
    }

    /**
     * Pops and returns the element at the top of the stack.
     *
     * @return The element removed from the top of the stack, or null if the stack is empty.
     */
    @Override
    public E pop() {
        if(isEmpty()) return null;
        E popped = data[t];
        data[t--] = null;
        return popped;
    }
}
