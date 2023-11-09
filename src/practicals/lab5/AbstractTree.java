package practicals.lab5;

/**
 * An abstract implementation of the {@link Tree} interface providing common functionality.
 *
 * @param <E> The type of elements stored in the tree.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    /**
     * Checks if the given position is external, meaning it has no children.
     *
     * @param p The position to check for external status.
     * @return {@code true} if the position is external, {@code false} otherwise.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    /**
     * Checks if the given position is internal, meaning it has one or more children.
     *
     * @param p The position to check for internal status.
     * @return {@code true} if the position is internal, {@code false} otherwise.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    /**
     * Checks if the given position is the root of the tree.
     *
     * @param p The position to check for being the root.
     * @return {@code true} if the position is the root, {@code false} otherwise.
     * @throws IllegalArgumentException If the provided position is invalid or null.
     */
    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    /**
     * Checks if the tree is empty, i.e., it contains no positions.
     *
     * @return {@code true} if the tree is empty, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}