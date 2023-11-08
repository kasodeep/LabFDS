package practicals.lab5;

public class Edge<E> {
    private Position<E> parent;
    private Position<E> child;

    public Edge(Position<E> parent, Position<E> child) {
        this.parent = parent;
        this.child = child;
    }

    public Position<E> getParent() {
        return parent;
    }

    public Position<E> getChild() {
        return child;
    }
}

