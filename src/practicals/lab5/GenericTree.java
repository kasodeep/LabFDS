package practicals.lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of a generic tree structure with various utility methods.
 *
 * @param <E> The type of elements stored in the tree.
 */
public class GenericTree<E> extends AbstractTree<E> {

    private final Node<E> root;
    private int size;

    /**
     * Constructs a generic tree with the given root element.
     *
     * @param rootElement The element for the root of the tree.
     */
    public GenericTree(E rootElement) {
        root = new Node<>(rootElement, null);
        size = 1;
    }

    /**
     * Returns the root position of the tree.
     *
     * @return The root position of the tree.
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Adds a child with the specified element to the given parent position.
     *
     * @param parentPosition The parent position to which the child will be added.
     * @param childElement   The element for the child.
     * @return The position of the newly added child.
     */
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

    /**
     * Finds and returns an iterator over the siblings of the given position.
     *
     * @param position The position to find siblings for.
     * @return An iterator over the siblings of the given position.
     */
    public Iterator<Position<E>> findSiblings(Position<E> position) {
        Node<E> currentNode = validate(position);
        Node<E> parent = currentNode.getParent();

        if (parent != null) {
            List<Position<E>> siblings = new ArrayList<>();
            for (Node<E> child : parent.getChildren()) {
                if (child != currentNode) {
                    siblings.add(child);
                }
            }
            return siblings.iterator();
        }
        return Collections.emptyIterator();
    }

    /**
     * Finds and returns an iterator over the leaves of the tree.
     *
     * @return An iterator over the leaves of the tree.
     */
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

    /**
     * Finds and returns an iterator over the internal nodes of the tree.
     *
     * @return An iterator over the internal nodes of the tree.
     */
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

    /**
     * Finds and returns the height of the tree.
     *
     * @return The height of the tree.
     */
    public int findHeight() {
        return findHeight(root);
    }

    private int findHeight(Position<E> currentPosition) {
        if (isExternal(currentPosition)) {
            return 0;
        }

        int maxHeight = 0;
        for (Position<E> child : children(currentPosition)) {
            int childHeight = findHeight(child);
            maxHeight = Math.max(maxHeight, childHeight);
        }

        return maxHeight + 1;
    }

    /**
     * Finds and returns the depth of the given position in the tree.
     *
     * @param target The position for which to find the depth.
     * @return The depth of the given position in the tree.
     */
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
        return -1;
    }

    /**
     * Finds and returns a subtree rooted at the given position.
     *
     * @param rootPosition The root position of the subtree.
     * @return A subtree rooted at the given position.
     */
    public GenericTree<E> findSubtree(Position<E> rootPosition) {
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

    private boolean findPath(Position<E> currentPosition, Position<E> target, List<E> pathElements) {
        pathElements.add(currentPosition.getElement());
        if (currentPosition == target) {
            return true;
        }

        for (Position<E> child : children(currentPosition)) {
            if (findPath(child, target, pathElements)) {
                return true;
            }
        }

        pathElements.remove(pathElements.size() - 1);
        return false;  // Node not found in this subtree
    }

    /**
     * Finds and returns a path from the root to the given position as a string.
     *
     * @param target    The target position for which to find the path.
     * @param delimiter The delimiter to use between elements in the path.
     * @return A path from the root to the given position as a string.
     */
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

    /**
     * Finds and returns an iterator over the edges of the tree.
     */
    private void listEdges(Position<E> position, List<Edge<E>> edges) {
        for (Position<E> child : children(position)) {
            edges.add(new Edge<>(position, child));
            listEdges(child, edges);
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

    /**
     * Helper method for in-order traversal of positions
     */
    private void preorderPositions(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> child : children(p)) {
            preorderPositions(child, snapshot);
        }
    }

    /**
     * Inner Node class representing positions in the tree.
     */
    private static class Node<E> implements Position<E> {
        private final E element;
        private final Node<E> parent;
        private final List<Node<E>> children;

        /**
         * Constructs a node with the given element and parent.
         *
         * @param element The element stored in the node.
         * @param parent  The parent node of this node.
         */
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        /**
         * Returns the element stored in the node.
         *
         * @return The element stored in the node.
         */
        @Override
        public E getElement() {
            return element;
        }

        /**
         * Returns the parent node of this node.
         *
         * @return The parent node of this node.
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Returns a list of child nodes of this node.
         *
         * @return A list of child nodes of this node.
         */
        public List<Node<E>> getChildren() {
            return children;
        }

        /**
         * Adds a child node to the list of children.
         *
         * @param child The child node to be added.
         */
        public void addChild(Node<E> child) {
            children.add(child);
        }
    }

    /**
     * Inner iterator class for iterating over the elements of the tree.
     */
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
