package practicals.lab10;

import practicals.lab4.LinkedPositionalList;
import practicals.lab4.Position;

import java.util.Iterator;

/**
 * Implementation of a graph using an adjacency list representation.
 *
 * @param <V> The type of the element associated with vertices.
 * @param <E> The type of the element associated with edges.
 */
public class AdjacencyListGraph<V, E> implements Graph<V, E> {

    private final boolean isDirected;
    private final LinkedPositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private final LinkedPositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    public AdjacencyListGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public Iterator<Vertex<V>> vertices() {
        return vertices.iterator();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Iterator<Edge<E>> edges() {
        return edges.iterator();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        InnerVertex<V> origin = validate(u);
        Iterator<Edge<E>> iterator = origin.getOutgoing().iterator();

        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            if (endVertices(edge)[1].equals(v)) return edge;
        }
        return null;
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();

        if (endpoints[0].equals(v)) return endpoints[1];
        else if (endpoints[1].equals(v)) return endpoints[0];
        else throw new IllegalArgumentException("v is not incident to this edge.");
    }

    @Override
    public int inDegree(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        return vertex.incoming.size();
    }

    @Override
    public int outDegree(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        return vertex.incoming.size();
    }

    @Override
    public Iterator<Edge<E>> outgoingEdges(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        return vertex.outgoing.iterator();
    }

    @Override
    public Iterator<Edge<E>> incomingEdges(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        return vertex.incoming.iterator();
    }

    @Override
    public Vertex<V> insertVertex(V v) {
        InnerVertex<V> vertex = new InnerVertex<>(v, isDirected);
        vertex.setPosition(vertices.addLast(vertex));
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E e) {
        if (getEdge(u, v) == null) {
            InnerEdge<E> edge = new InnerEdge<>(u, v, e);
            edge.setPosition(edges.addLast(edge));

            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().addLast(edge);
            dest.getIncoming().addLast(edge);
            return edge;
        } else {
            throw new IllegalArgumentException("Edge from u to v exists!");
        }
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);

        Iterator<Edge<E>> iterator = vertex.getOutgoing().iterator();
        while (iterator.hasNext()) removeEdge(iterator.next());

        iterator = vertex.getIncoming().iterator();
        while (iterator.hasNext()) removeEdge(iterator.next());
    }

    @Override
    public void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        edges.remove(edge.getPosition());
    }

    public Vertex<V> findVertex(V value) {
        Iterator<Vertex<V>> iterator = vertices.iterator();
        while (iterator.hasNext()) {
            Vertex<V> vertex = iterator.next();
            if (vertex.getVertex().equals(value)) return vertex;
        }
        return null;
    }

    @Override
    public Edge<E> findEdge(E value) {
        Iterator<Edge<E>> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            if (edge.getElement().equals(value)) return edge;
        }
        return null;
    }

    private InnerEdge<E> validate(Edge<E> e) throws IllegalArgumentException {
        if (!(e instanceof InnerEdge))
            throw new IllegalArgumentException("Passed position is invalid");

        InnerEdge<E> edge = (InnerEdge<E>) e;
        if (edge.getElement() == null)
            throw new IllegalArgumentException("The passed node is defunct");
        return edge;
    }

    private InnerVertex<V> validate(Vertex<V> v) throws IllegalArgumentException {
        if (!(v instanceof InnerVertex))
            throw new IllegalArgumentException("Passed position is invalid");

        InnerVertex<V> vertex = (InnerVertex<V>) v;
        if (vertex.getVertex() == null)
            throw new IllegalArgumentException("The passed node is defunct");
        return vertex;
    }

    /**
     * Inner class representing a vertex in the adjacency list graph.
     *
     * @param <V> The type of the element associated with the vertex.
     */
    private class InnerVertex<V> implements Vertex<V> {
        private final V element;
        private final LinkedPositionalList<Edge<E>> outgoing;
        private final LinkedPositionalList<Edge<E>> incoming;
        private Position<Vertex<V>> pos;

        public InnerVertex(V element, boolean isDirected) {
            this.element = element;
            outgoing = new LinkedPositionalList<>();

            if (isDirected) incoming = new LinkedPositionalList<>();
            else incoming = outgoing;
        }

        @Override
        public V getVertex() {
            return element;
        }

        public Position<Vertex<V>> getPosition() {
            return pos;
        }

        public void setPosition(Position<Vertex<V>> pos) {
            this.pos = pos;
        }

        public LinkedPositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }

        public LinkedPositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }

    /**
     * Inner class representing an edge in the adjacency list graph.
     *
     * @param <E> The type of the element associated with the edge.
     */
    private class InnerEdge<E> implements Edge<E> {
        private final E element;
        private final Vertex<V>[] endpoints;
        private Position<Edge<E>> pos;

        @SuppressWarnings("unchecked")
        public InnerEdge(Vertex<V> u, Vertex<V> v, E element) {
            this.element = element;
            endpoints = (Vertex<V>[]) new Vertex[]{u, v};
        }

        @Override
        public E getElement() {
            return element;
        }

        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        public Position<Edge<E>> getPosition() {
            return pos;
        }

        public void setPosition(Position<Edge<E>> pos) {
            this.pos = pos;
        }
    }
}
