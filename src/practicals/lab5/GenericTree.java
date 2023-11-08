package practicals.lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GenericTree<E> extends AbstractTree<E> {

    private final Node<E> root;
    private int size;

    public GenericTree(E rootElement) {
        root = new Node<>(rootElement, null);
        size = 1;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    public Position<E> addChild(Position<E> parentPosition, E childElement) {
        Node<E> parent = validate(parentPosition);
        Node<E> child = new Node<>(childElement, parent);
        parent.addChild(child);
        size++;
        return child;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return new ArrayList<>(node.getChildren());
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return numChildren(node) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return numChildren(node) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node == root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> positions = new ArrayList<>();
        preorderPositions(root, positions);
        return positions;
    }

    // Helper method for in-order traversal of positions
    private void preorderPositions(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> child : children(p)) {
            preorderPositions(child, snapshot);
        }
    }

    public Iterator<Position<E>> findSiblings(Position<E> position) {
        Node<E> currentNode = validate(position);

        // Find the parent of the given position
        Node<E> parent = currentNode.getParent();

        if (parent != null) {
            List<Position<E>> siblings = new ArrayList<>();
            // Iterate through the children of the parent and add siblings to the list
            for (Node<E> child : parent.getChildren()) {
                if (child != currentNode) {
                    siblings.add(child);
                }
            }
            return siblings.iterator();
        }

        // If there are no siblings, return an empty iterator
        return Collections.emptyIterator();
    }

    public Iterator<Position<E>> listLeaves() {
        List<Position<E>> leaves = new ArrayList<>();
        listLeaves(root, leaves);
        return leaves.iterator();
    }

    private void listLeaves(Position<E> position, List<Position<E>> leaves) {
        if (isExternal(position)) {
            leaves.add(position);
        } else {
            for (Position<E> child : children(position)) {
                listLeaves(child, leaves);
            }
        }
    }

    public Iterator<Position<E>> listInternalNodes() {
        List<Position<E>> internalNodes = new ArrayList<>();
        listInternal(root, internalNodes);
        return internalNodes.iterator();
    }

    private void listInternal(Position<E> position, List<Position<E>> internalNodes) {
        if (isInternal(position)) {
            internalNodes.add(position);
        }
        for (Position<E> child : children(position)) {
            listInternal(child, internalNodes);
        }
    }

    public int findHeight() {
        return findHeight(root);
    }

    private int findHeight(Position<E> currentPosition) {
        if (isExternal(currentPosition)) {
            return 0; // Leaf nodes have a height of 0
        }

        int maxHeight = 0;
        for (Position<E> child : children(currentPosition)) {
            int childHeight = findHeight(child);
            maxHeight = Math.max(maxHeight, childHeight);
        }

        return maxHeight + 1; // Add 1 for the current level
    }

    public int findDepth(Position<E> target) {
        return findDepth(root, target, 0);
    }

    private int findDepth(Position<E> currentPosition, Position<E> target, int currentDepth) {
        if (currentPosition == target) {
            return currentDepth;
        }
        for (Position<E> child : children(currentPosition)) {
            int depth = findDepth(child, target, currentDepth + 1);
            if (depth != -1) {
                return depth;
            }
        }
        return -1; // Node not found
    }

    public Tree<E> findSubtree(Position<E> rootPosition) {
        GenericTree<E> subtree = new GenericTree<>(rootPosition.getElement());
        copySubtree(rootPosition, subtree.root(), subtree);
        return subtree;
    }

    private void copySubtree(Position<E> sourcePosition, Position<E> destinationPosition, GenericTree<E> destinationTree) {
        for (Position<E> child : children(sourcePosition)) {
            Position<E> newChild = destinationTree.addChild(destinationPosition, child.getElement());
            copySubtree(child, newChild, destinationTree);
        }
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E> node)) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (node.getParent() == node) {
            throw new IllegalArgumentException("The node is no longer in the tree");
        }
        return node;
    }

    private boolean findPath(Position<E> currentPosition, Position<E> target, List<E> pathElements) {
        pathElements.add(currentPosition.getElement());

        if (currentPosition == target) {
            return true;  // Node found
        }

        for (Position<E> child : children(currentPosition)) {
            if (findPath(child, target, pathElements)) {
                return true;  // Node found in this subtree
            }
        }

        pathElements.remove(pathElements.size() - 1);
        return false;  // Node not found in this subtree
    }

    public String findPath(Position<E> target, String delimiter) {
        List<E> pathElements = new ArrayList<>();
        findPath(root, target, pathElements);

        StringBuilder pathBuilder = new StringBuilder();
        for (E element : pathElements) {
            if (pathBuilder.length() > 0) {
                pathBuilder.append(delimiter);
            }
            pathBuilder.append(element);
        }

        return pathBuilder.toString();
    }

    public Iterator<Edge<E>> listEdges() {
        List<Edge<E>> edges = new ArrayList<>();
        listEdges(root, edges);
        return edges.iterator();
    }

    private void listEdges(Position<E> position, List<Edge<E>> edges) {
        for (Position<E> child : children(position)) {
            edges.add(new Edge<>(position, child));
            listEdges(child, edges);
        }
    }



    // Inner Node class
    private static class Node<E> implements Position<E> {
        private final E element;
        private final Node<E> parent;
        private final List<Node<E>> children;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        @Override
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public List<Node<E>> getChildren() {
            return children;
        }

        public void addChild(Node<E> child) {
            children.add(child);
        }
    }

    // Inner iterator class
    private class ElementIterator implements Iterator<E> {
        private final Iterator<Position<E>> positionIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }
    }
}
