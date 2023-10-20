package practicals.lab3;

/**
 * Interface for implementing abstract data type Stack.
 */
public interface Stack<E> {

    /**
     * Returns the size of the stack.
     */
    int size();

    /**
     * Returns true if stack id empty else false.
     */
    boolean isEmpty();

    /**
     * Pushes an element on the top of Stack.
     */
    void push(E e);

    /**
     * Returns an element at the top of the Stack.
     */
    E top();

    /**
     * Pops an element from the top of Stack.
     */
    E pop();
}
