package practicals.lab5;

import java.util.Iterator;

/**
 * A generic tree structure that represents a hierarchical relationship between elements.
 *
 * @param <E> The type of elements stored in the tree.
 */
public interface Tree<E> extends Iterable<E> {

    /**
     * Returns the root position of the tree.
     *
     * @return The root position of the tree.
     */
    Position<E> root();

    /**
     * Returns the parent position of the given position.
     *
     * @param p The position for which to find the parent.
     * @return The parent position of the given position.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns an iterable collection of positions representing the children of the given position.
     *
     * @param p The position for which to find the children.
     * @return An iterable collection of positions representing the children of the given position.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the number of children of the given position.
     *
     * @param p The position for which to find the number of children.
     * @return The number of children of the given position.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    int numChildren(Position<E> p) throws IllegalArgumentException;

    /**
     * Checks if the given position is internal, meaning it has one or more children.
     *
     * @param p The position to check for internal status.
     * @return {@code true} if the position is internal, {@code false} otherwise.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Checks if the given position is external, meaning it has no children.
     *
     * @param p The position to check for external status.
     * @return {@code true} if the position is external, {@code false} otherwise.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Checks if the given position is the root of the tree.
     *
     * @param p The position to check for being the root.
     * @return {@code true} if the position is the root, {@code false} otherwise.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the total number of positions (nodes) in the tree.
     *
     * @return The total number of positions in the tree.
     */
    int size();

    /**
     * Checks if the tree is empty, i.e., it contains no positions.
     *
     * @return {@code true} if the tree is empty, {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Returns an iterator over the elements of the tree.
     *
     * @return An iterator over the elements of the tree.
     */
    @Override
    Iterator<E> iterator();

    /**
     * Returns an iterable collection of all positions in the tree.
     *
     * @return An iterable collection of all positions in the tree.
     */
    Iterable<Position<E>> positions();
}