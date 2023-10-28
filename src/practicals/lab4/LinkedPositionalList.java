package practicals.lab4;

import java.util.*;

/**
 * This class implements the PositionalListInterface and provides a linked list implementation of it.
 * It contains a private static class Node which implements the Position interface and is used to represent each element in the list.
 * The class also contains methods to add, remove, and modify elements in the list, as well as iterators to traverse the list.
 *
 * @author Kasodariya Deep
 * @version 1.0
 */
public class LinkedPositionalList<E> implements PositionalList<E> {
    private final Node<E> header;
    private final Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Validates that the given position is a valid node in the list.
     *
     * @param p - The position to be validated
     * @return - The node corresponding to the given position
     * @throws IllegalArgumentException if the position is not a valid node in the list
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E> node)) throw new IllegalArgumentException("Invalid p");

        if (node.getNext() == null)
            throw new IllegalArgumentException("p  is no longer in the list");
        return node;
    }

    /**
     * Returns the position corresponding to the given node, or null if the node is the header or trailer.
     *
     * @param node - The node to be converted to a position
     * @return - The position corresponding to the given node, or null if the node is the header or trailer
     */
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return - The number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the list is empty, false otherwise.
     *
     * @return - true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the position of the first element in the list, or null if the list is empty.
     *
     * @return - The position of the first element in the list, or null if the list is empty
     */
    public Position<E> first() {
        return position(header.getNext());
    }

    /**
     * Returns the position of the last element in the list, or null if the list is empty.
     *
     * @return - The position of the last element in the list, or null if the list is empty
     */
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /**
     * Returns the position of the element immediately before the given position.
     *
     * @param p - The position whose predecessor is to be returned
     * @return - The position of the element immediately before the given position
     * @throws IllegalArgumentException if the given position is not a valid node in the list
     */
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /**
     * Returns the position of the element immediately after the given position.
     *
     * @param p - The position whose successor is to be returned
     * @return - The position of the element immediately after the given position
     * @throws IllegalArgumentException if the given position is not a valid node in the list
     */
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    /**
     * Adds a new node with the given element between the given predecessor and successor nodes.
     *
     * @param e    - The element to be stored in the new node
     * @param pred - The node that will be the predecessor of the new node
     * @param succ - The node that will be the successor of the new node
     * @return the position of the newly added node
     */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    /**
     * Adds a new element to the beginning of the list.
     *
     * @param e - The element to be added
     * @return - The position of the newly added element
     */
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /**
     * Adds a new element to the end of the list.
     *
     * @param e - The element to be added
     * @return - The position of the newly added element
     */
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    /**
     * Removes the element stored at the given position from the list.
     *
     * @param p - The position of the element to be removed
     * @return - The element that was removed
     * @throws IllegalArgumentException if the given position is not a valid node in the list
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> pred = node.getPrev();
        Node<E> succ = node.getNext();

        pred.setNext(succ);
        succ.setPrev(pred);
        size--;

        E answer = node.getElement();
        node.setElement(null);
        node.setPrev(null);
        node.setNext(null);

        return answer;

    }

    public Iterator<Position<E>> iterator() {
        return new PositionIterator();
    }

    /**
     * A node in the doubly linked list.
     *
     * @param <E> the type of element held by this node
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            this.element = e;
            this.prev = p;
            this.next = n;
        }

        public E getElement() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("Position is no longer Valid");
            }
            return element;
        }

        public void setElement(E e) {
            element = e;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    /**
     * Returns an iterable representation of the list's positions.
     * Position Iterator is a private class that implements the Iterator interface and is used to iterate over the positions in the list.
     */
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();
        private Position<E> recent = null;

        public boolean hasNext() {
            return cursor != null;
        }

        public Position<E> next() throws IllegalStateException {
            if (cursor == null) throw new IllegalStateException("Nothing Left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("Nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }
}
