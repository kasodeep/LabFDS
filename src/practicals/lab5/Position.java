package practicals.lab5;

public interface Position<E> {

    /**
     * Returns the element stored at this position.
     *
     * @return - The stored element at this position.
     * @throws IllegalStateException if position no longer valid.
     */
    E getElement() throws IllegalStateException;
}
