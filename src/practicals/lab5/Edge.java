package practicals.lab5;

/**
 * Represents an edge in a tree, connecting a parent position to a child position.
 *
 * @param <E> The type of elements stored in the positions.
 */
public record Edge<E>(Position<E> parent, Position<E> child) {

    /**
     * Constructs an edge connecting the given parent and child positions.
     *
     * @param parent The parent position of the edge.
     * @param child  The child position of the edge.
     */
    public Edge(Position<E> parent, Position<E> child) {
        this.parent = parent;
        this.child = child;
    }

    /**
     * Returns the parent position of the edge.
     *
     * @return The parent position of the edge.
     */
    public Position<E> getParent() {
        return parent;
    }

    /**
     * Returns the child position of the edge.
     *
     * @return The child position of the edge.
     */
    public Position<E> getChild() {
        return child;
    }
}


