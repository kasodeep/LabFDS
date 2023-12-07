package practicals.lab10;

import java.util.Iterator;

public interface Graph<V, E> {

    int numVertices();
    Iterator<Vertex<V>> vertices();
    int numEdges();
    Iterator<Edge<E>> edges();
    Edge<E> getEdge(Vertex<V> u, Vertex<V> v);
    Vertex<V>[] endVertices(Edge<E> e);
    Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;
    int inDegree(Vertex<V> v);
    int outDegree(Vertex<V> v);
    Iterator<Edge<E>> outgoingEdges(Vertex<V> v);
    Iterator<Edge<E>> incomingEdges(Vertex<V> v);
    Vertex<V> insertVertex(V v);
    Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E e);
    void removeVertex(Vertex<V> v);
    void removeEdge(Edge<E> e);
}
