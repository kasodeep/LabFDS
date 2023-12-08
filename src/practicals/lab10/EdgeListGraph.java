package practicals.lab10;

import practicals.lab4.LinkedPositionalList;
import practicals.lab4.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of a graph using an edge list representation.
 *
 * @param <V> The type of the element associated with vertices.
 * @param <E> The type of the element associated with edges.
 */
public class EdgeListGraph<V, E> implements Graph<V, E> {
    private final LinkedPositionalList<Vertex<V>> vertices;
    private final LinkedPositionalList<Edge<E>> edges;

    public EdgeListGraph() {
        this.vertices = new LinkedPositionalList<>();
        this.edges = new LinkedPositionalList<>();
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
        InnerVertex<V> vertex = validate(v);
        Vertex<V>[] endpoints = edge.getEndpoints();

        if (endpoints[0].equals(vertex)) return endpoints[1];
        else if (endpoints[1].equals(vertex)) return endpoints[0];
        else throw new IllegalArgumentException("v is not incident to this edge.");
    }

    @Override
    public int inDegree(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        int count = 0;
        Iterator<Edge<E>> iterator = edges.iterator();

        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            InnerEdge<E> validate = validate(edge);
            if (validate.getEndpoints()[1] == vertex) count++;
        }
        return count;
    }

    @Override
    public int outDegree(Vertex<V> v) {
        int count = 0;
        InnerVertex<V> vertex = validate(v);
        Iterator<Edge<E>> iterator = edges.iterator();

        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            InnerEdge<E> validate = validate(edge);
            if (validate.getEndpoints()[0] == vertex) count++;
        }
        return count;
    }

    @Override
    public Iterator<Edge<E>> outgoingEdges(Vertex<V> v) {
        List<Edge<E>> outgoingEdges = new ArrayList<>();
        InnerVertex<V> vertex = validate(v);
        Iterator<Edge<E>> iterator = edges.iterator();

        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            InnerEdge<E> validate = validate(edge);
            if (validate.getEndpoints()[0] == vertex) outgoingEdges.add(edge);
        }
        return outgoingEdges.iterator();
    }

    @Override
    public Iterator<Edge<E>> incomingEdges(Vertex<V> v) {
        List<Edge<E>> incomingEdges = new ArrayList<>();
        InnerVertex<V> vertex = validate(v);
        Iterator<Edge<E>> iterator = edges.iterator();

        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            InnerEdge<E> validate = validate(edge);
            if (validate.getEndpoints()[1] == vertex) incomingEdges.add(edge);
        }
        return incomingEdges.iterator();
    }

    @Override
    public Vertex<V> insertVertex(V v) {
        InnerVertex<V> vertex = new InnerVertex<>(v);
        vertices.addLast(vertex);
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E e) {
        InnerEdge<E> edge = new InnerEdge<>(v, u, e);
        edges.addLast(edge);
        return edge;
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        Iterator<Edge<E>> iterator = edges.iterator();

        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            if (validate(edge).getEndpoints()[0].equals(vertex) || validate(edge).getEndpoints()[1].equals(vertex))
                removeEdge(edge);
        }
    }

    @Override
    public void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        edges.remove(edge.getPosition());
    }

    @Override
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
    private static class InnerVertex<V> implements Vertex<V> {
        private final V element;
        private Position<Vertex<V>> pos;

        public InnerVertex(V element) {
            this.element = element;
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
