package practicals.lab10;

import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph<String, Integer> graph = null;

        System.out.println("Choose the graph implementation:");
        System.out.println("1. Edge List");
        System.out.println("2. Adjacency List");
        System.out.println("3. Adjacency Map");
        System.out.println("4. Adjacency Matrix");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> graph = new EdgeListGraph<>();
            case 2 -> graph = new AdjacencyListGraph<>(true);
            case 3 -> graph = new AdjacencyMapGraph<>(true);
            case 4 -> graph = new AdjacencyMatrixGraph<>(true);
            default -> {
                System.out.println("Invalid choice. Exiting...");
                System.exit(0);
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Number of Vertices");
            System.out.println("2. Number of Edges");
            System.out.println("3. Get Edge from u to v");
            System.out.println("4. End Vertices of an Edge");
            System.out.println("5. Opposite Vertex from Edge and Vertex");
            System.out.println("6. Out Degree of a Vertex");
            System.out.println("7. In Degree of a Vertex");
            System.out.println("8. Outgoing Edges of a Vertex");
            System.out.println("9. Incoming Edges of a Vertex");
            System.out.println("10. Insert a Vertex");
            System.out.println("11. Insert an Edge");

            int operation = scanner.nextInt();
            switch (operation) {
                case 1 -> System.out.println("Number of Vertices: " + graph.numVertices());
                case 2 -> System.out.println("Number of Edges: " + graph.numEdges());
                case 3 -> {
                    System.out.println("Enter source vertex u:");
                    String u = scanner.next();
                    System.out.println("Enter destination vertex v:");
                    String v = scanner.next();

                    Vertex<String> vertexU = graph.findVertex(u);
                    Vertex<String> vertexV = graph.findVertex(v);
                    if (vertexU == null || vertexV == null) {
                        System.out.println("Invalid Vertices!");
                        break;
                    }

                    Edge<Integer> edgeUV = graph.getEdge(vertexU, vertexV);
                    if (edgeUV != null) System.out.println("Edge from " + u + " to " + v + " found.");
                    else System.out.println("Edge from " + u + " to " + v + " not found.");
                }
                case 4 -> {
                    System.out.println("Enter edge ID:");
                    int edgeID = scanner.nextInt();
                    Edge<Integer> edge = graph.findEdge(edgeID);

                    if (edge != null) {
                        Vertex<String>[] endpoints = graph.endVertices(edge);
                        System.out.println("End vertices of edge " + edgeID + ": " + endpoints[0].getVertex() + " and " + endpoints[1].getVertex());
                    } else
                        System.out.println("Edge with ID " + edgeID + " not found.");
                }

                case 5 -> {
                    System.out.println("Enter source vertex:");
                    String sourceVertex = scanner.next();
                    System.out.println("Enter edge ID:");
                    int edgeID = scanner.nextInt();

                    Vertex<String> vertex = graph.findVertex(sourceVertex);
                    Edge<Integer> edge = graph.findEdge(edgeID);

                    if (edge != null || vertex == null) {
                        Vertex<String> oppositeVertex = graph.opposite(vertex, edge);
                        if (oppositeVertex != null)
                            System.out.println("Opposite vertex of vertex " + sourceVertex + " across edge " + edgeID + ": " + oppositeVertex.getVertex());
                        else System.out.println("Vertex " + sourceVertex + " is not incident to edge " + edgeID);
                    } else {
                        System.out.println("Edge with ID " + edgeID + " not found.");
                    }
                }
                case 6 -> {
                    System.out.println("Enter vertex ID:");
                    String vertex = scanner.next();
                    Vertex<String> found = graph.findVertex(vertex);

                    if (found == null) System.out.println("Invalid Vertex");
                    else {
                        int outDegree = graph.outDegree(found);
                        System.out.println("Out degree of vertex " + vertex + ": " + outDegree);
                    }
                }
                case 7 -> {
                    System.out.println("Enter vertex ID:");
                    String vertex = scanner.next();
                    Vertex<String> found = graph.findVertex(vertex);

                    if (found == null) System.out.println("Invalid Vertex");
                    else {
                        int inDegree = graph.inDegree(found);
                        System.out.println("In degree of vertex " + vertex + ": " + inDegree);
                    }
                }
                case 8 -> {
                    System.out.println("Enter vertex ID:");
                    String vertex = scanner.next();
                    Vertex<String> found = graph.findVertex(vertex);

                    if (found == null) System.out.println("Invalid Vertex");
                    else {
                        Iterator<Edge<Integer>> outgoingEdges = graph.outgoingEdges(found);
                        System.out.println("Outgoing edges of vertex " + vertex + ":");
                        while (outgoingEdges.hasNext()) {
                            System.out.println(outgoingEdges.next().getElement());
                        }
                    }
                }
                case 9 -> {
                    System.out.println("Enter vertex ID:");
                    String vertex = scanner.next();
                    Vertex<String> found = graph.findVertex(vertex);

                    if (found == null) System.out.println("Invalid Vertex");
                    else {
                        Iterator<Edge<Integer>> incomingEdges = graph.incomingEdges(found);
                        System.out.println("Incoming edges of vertex " + vertex + ":");
                        while (incomingEdges.hasNext()) {
                            System.out.println(incomingEdges.next().getElement());
                        }
                    }
                }
                case 10 -> {
                    System.out.println("Enter element for the new vertex:");
                    String newElement = scanner.next();

                    Vertex<String> newVertex = graph.insertVertex(newElement);
                    System.out.println("New vertex inserted with element: " + newVertex.getVertex());
                }
                case 11 -> {
                    System.out.println("Enter source vertex ID:");
                    String sourceVertexID = scanner.next();
                    System.out.println("Enter destination vertex ID:");
                    String destinationVertexID = scanner.next();

                    Vertex<String> vertexU = graph.findVertex(sourceVertexID);
                    Vertex<String> vertexV = graph.findVertex(destinationVertexID);
                    if (vertexV == null || vertexU == null) {
                        System.out.println("Invalid Vertices!");
                        break;
                    }
                    System.out.println("Enter element for the new edge:");
                    Integer newEdgeElement = scanner.nextInt();

                    Edge<Integer> newEdge = graph.insertEdge(vertexU, vertexV, newEdgeElement);
                    System.out.println("New edge inserted with element: " + newEdge.getElement());
                }
                default -> System.out.println("Invalid operation.");
            }

            System.out.println("Do you want to perform another operation? (Y/N)");
            String continueChoice = scanner.next();
            running = continueChoice.equalsIgnoreCase("Y");
        }
    }
}
