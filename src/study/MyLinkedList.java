package study;

public class MyLinkedList<E> {

    private static class Node<E> {

        private final E element;

        private Node<E> next;

        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> head = null;

    private Node<E> tail = null;

    private int size = 0;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){
        head = new Node<>(e, head);
        if(size == 0)tail = head;
        size++;
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e, null);
        if(size == 0) head = newNode;
        else tail.setNext(newNode);

        tail = newNode;
        size++;
    }

    public E removeFirst(){
        if(size == 0) return null;

        E node = head.getElement();
        head = head.getNext();
        size--;

        if(size == 0) tail = null;
        return node;
    }
}
