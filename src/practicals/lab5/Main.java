package practicals.lab5;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        GenericTree<Integer> tree = new GenericTree<>(10);
        Position<Integer> root = tree.root();

        Position<Integer> child1 = tree.addChild(root, 20);
        Position<Integer> child2 = tree.addChild(root, 30);

        Position<Integer> child3 = tree.addChild(child1, 40);
        Position<Integer> child4 = tree.addChild(child1, 50);
        Position<Integer> child5 = tree.addChild(child1, 60);

        Position<Integer> child6 = tree.addChild(child2, 70);
        Position<Integer> child7 = tree.addChild(child2, 80);

        Iterator<Integer> iterator = tree.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        Iterator<Position<Integer>> siblings = tree.findSiblings(child3);
        while (siblings.hasNext()) {
            System.out.print(siblings.next().getElement() + " ");
        }
        System.out.println();

        Iterator<Position<Integer>> leaves = tree.listLeaves();
        while (leaves.hasNext()) {
            System.out.print(leaves.next().getElement() + " ");
        }
        System.out.println();

        Iterator<Position<Integer>> internalNodes = tree.listInternalNodes();
        while (internalNodes.hasNext()) {
            System.out.print(internalNodes.next().getElement() + " ");
        }
        System.out.println();

        System.out.println(tree.findHeight());
        System.out.println(tree.findDepth(child1));

        Tree<Integer> subtree = tree.findSubtree(child1);
        Iterator<Integer> positions = subtree.iterator();
        while (positions.hasNext()) {
            System.out.print(positions.next() + " ");
        }
        System.out.println();

        System.out.println(tree.findPath(child3, "->"));

        Iterator<Edge<Integer>> edges = tree.listEdges();
        while (edges.hasNext()) {
            Edge<Integer> edge = edges.next();
            System.out.println(edge.getParent().getElement() + "->" + edge.getChild().getElement());
        }
    }
}
