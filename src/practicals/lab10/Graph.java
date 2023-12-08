package practicals.lab10;

import java.util.Iterator;

/**
 * An interface representing a graph.
 *
 * @param <V> The type of the element associated with vertices.
 * @param <E> The type of the element associated with edges.
 */
public interface Graph<V, E> {

    /**
     * Gets the number of vertices in the graph.
     *
     * @return The number of vertices in the graph.
     */
    int numVertices();

    /**
     * Returns an iterator over the vertices in the graph.
     *
     * @return An iterator over the vertices in the graph.
     */
    Iterator<Vertex<V>> vertices();

    /**
     * Gets the number of edges in the graph.
     *
     * @return The number of edges in the graph.
     */
    int numEdges();

    /**
     * Returns an iterator over the edges in the graph.
     *
     * @return An iterator over the edges in the graph.
     */
    Iterator<Edge<E>> edges();

    /**
     * Gets the edge between vertices u and v, if it exists.
     *
     * @param u One vertex.
     * @param v Another vertex.
     * @return The edge between vertices u and v, or null if no such edge exists.
     */
    Edge<E> getEdge(Vertex<V> u, Vertex<V> v);

    /**
     * Returns an array containing the two endpoints of edge e.
     *
     * @param e The edge.
     * @return An array containing the two endpoints of edge e.
     */
    Vertex<V>[] endVertices(Edge<E> e);

    /**
     * Returns the vertex that is opposite vertex v on edge e.
     *
     * @param v The vertex.
     * @param e The edge.
     * @return The vertex that is opposite vertex v on edge e.
     * @throws IllegalArgumentException if v is not incident to e.
     */
    Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;

    /**
     * Gets the in-degree of a vertex v.
     *
     * @param v The vertex.
     * @return The in-degree of vertex v.
     */
    int inDegree(Vertex<V> v);

    /**
     * Gets the out-degree of a vertex v.
     *
     * @param v The vertex.
     * @return The out-degree of vertex v.
     */
    int outDegree(Vertex<V> v);

    /**
     * Returns an iterator over the outgoing edges of vertex v.
     *
     * @param v The vertex.
     * @return An iterator over the outgoing edges of vertex v.
     */
    Iterator<Edge<E>> outgoingEdges(Vertex<V> v);

    /**
     * Returns an iterator over the incoming edges of vertex v.
     *
     * @param v The vertex.
     * @return An iterator over the incoming edges of vertex v.
     */
    Iterator<Edge<E>> incomingEdges(Vertex<V> v);

    /**
     * Inserts a new vertex with element v into the graph.
     *
     * @param v The element associated with the new vertex.
     * @return The newly inserted vertex.
     */
    Vertex<V> insertVertex(V v);

    /**
     * Inserts a new edge with element e between vertices u and v into the graph.
     *
     * @param v One endpoint of the edge.
     * @param u Another endpoint of the edge.
     * @param e The element associated with the new edge.
     * @return The newly inserted edge.
     * @throws IllegalArgumentException if an edge already exists between u and v.
     */
    Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E e) throws IllegalArgumentException;

    /**
     * Removes a vertex and all its incident edges from the graph.
     *
     * @param v The vertex to be removed.
     */
    void removeVertex(Vertex<V> v);

    /**
     * Removes an edge from the graph.
     *
     * @param e The edge to be removed.
     */
    void removeEdge(Edge<E> e);

    /**
     * Finds and returns the vertex with the specified value in the graph.
     *
     * @param v the value to search for in the vertices
     * @return the vertex with the specified value, or null if not found
     */
    Vertex<V> findVertex(V v);

    /**
     * Finds and returns the edge with the specified value in the graph.
     *
     * @param e the value to search for in the edges
     * @return the edge with the specified value, or null if not found
     */
    Edge<E> findEdge(E e);
}
