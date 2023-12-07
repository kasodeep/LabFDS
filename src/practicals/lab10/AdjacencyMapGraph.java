package practicals.lab10;

import practicals.lab4.LinkedPositionalList;
import practicals.lab4.Position;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AdjacencyMapGraph<V, E> implements Graph<V, E> {

    private final boolean isDirected;
    private final LinkedPositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private final LinkedPositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    public AdjacencyMapGraph(boolean isDirected) {
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
        return origin.getOutgoing().get(v);
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

        if (endpoints[0] == v) return endpoints[1];
        else if (endpoints[1] == v) return endpoints[0];
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
        return vertex.outgoing.values().iterator();
    }

    @Override
    public Iterator<Edge<E>> incomingEdges(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        return vertex.incoming.values().iterator();
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
            origin.getOutgoing().put(v, edge);
            dest.getIncoming().put(u, edge);
            return edge;
        } else {
            throw new IllegalArgumentException("Edge from u to v exists!");
        }
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        for (Edge<E> e : vertex.getOutgoing().values())
            removeEdge(e);
        for (Edge<E> e : vertex.getIncoming().values())
            removeEdge(e);
        vertices.remove(vertex.getPosition());
    }

    @Override
    public void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        edges.remove(edge.getPosition());
    }

    private InnerEdge<E> validate(Edge<E> e) throws IllegalArgumentException {
        if (!(e instanceof InnerEdge<E>))
            throw new IllegalArgumentException("Passed position is invalid");

        InnerEdge<E> edge = (InnerEdge<E>) e;
        if (edge.getElement() == null)
            throw new IllegalArgumentException("The passed node is defunct");
        return edge;
    }

    private InnerVertex<V> validate(Vertex<V> v) throws IllegalArgumentException {
        if (!(v instanceof InnerVertex<V>))
            throw new IllegalArgumentException("Passed position is invalid");

        InnerVertex<V> vertex = (InnerVertex<V>) v;
        if (vertex.getVertex() == null)
            throw new IllegalArgumentException("The passed node is defunct");
        return vertex;
    }

    private class InnerVertex<V> implements Vertex<V> {
        private final V element;
        private Position<Vertex<V>> pos;
        private final Map<Vertex<V>, Edge<E>> outgoing;
        private final Map<Vertex<V>, Edge<E>> incoming;

        public InnerVertex(V element, boolean isDirected) {
            this.element = element;
            outgoing = new HashMap<>();

            if (isDirected) incoming = new HashMap<>();
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

        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }

    private class InnerEdge<E> implements Edge<E> {
        private final E element;
        private Position<Edge<E>> pos;
        private final Vertex<V>[] endpoints;

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
