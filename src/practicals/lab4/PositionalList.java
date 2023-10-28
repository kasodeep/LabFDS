package practicals.lab4;

/**
 * An interface for a positional list, which is a list where each element has a position.
 *
 * @param <E> The type of element held in the list
 */
public interface PositionalList<E> {

    /**
     * Returns the number of elements in the list.
     *
     * @return - The number of elements in the list
     */
    int size();

    /**
     * Returns whether the list is empty.
     *
     * @return - true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the first position in the list.
     *
     * @return - The first position in the list
     */
    Position<E> first();

    /**
     * Returns the last position in the list.
     *
     * @return - The last position in the list
     */
    Position<E> last();

    /**
     * Returns the position before the given position.
     *
     * @param p - The position to find the predecessor of
     * @return - The position before the given position
     * @throws IllegalArgumentException if the position is not valid
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the position after the given position.
     *
     * @param p - The position to find the successor of
     * @return - The position after the given position
     * @throws IllegalArgumentException if the position is not valid
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Inserts an element at the beginning of the list.
     *
     * @param e - The element to insert
     * @return - The position of the newly inserted element
     */
    Position<E> addFirst(E e);

    /**
     * Inserts an element at the end of the list.
     *
     * @param e - The element to insert
     * @return - The position of the newly inserted element
     */
    Position<E> addLast(E e);

    /**
     * Inserts an element before the given position.
     *
     * @param p - The position to insert the element before
     * @param e - The element to insert
     * @return - The position of the newly inserted element
     * @throws IllegalArgumentException if the position is not valid
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Inserts an element after the given position.
     *
     * @param p - The position to insert the element after
     * @param e - The element to insert
     * @return - The position of the newly inserted element
     * @throws IllegalArgumentException if the position is not valid
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element at the given position with the given element.
     *
     * @param p - The position of the element to replace
     * @param e the new element to store at the given position
     * @return - The old element that was replaced
     * @throws IllegalArgumentException if the position is not valid
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Removes the element at the given position.
     *
     * @param p - The position of the element to remove
     * @return - The element that was removed
     * @throws IllegalArgumentException if the position is not valid
     */
    E remove(Position<E> p) throws IllegalArgumentException;
}

