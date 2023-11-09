package practicals.lab5;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int root;
        while (true) {
            System.out.println("Enter the value of the root: ");
            try {
                root = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please Enter Integer.");
                scanner.next();
            }
        }

        GenericTree<Integer> tree = new GenericTree<>(root);
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            while (true) {
                try {
                    choice = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please Enter Integer.");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1: {
                    try {
                        System.out.print("Enter the parent value: ");
                        int parentValue = scanner.nextInt();
                        Position<Integer> parentPosition = findNode(tree.root(), parentValue, tree);

                        if (parentPosition != null) {
                            System.out.print("Enter the child value: ");
                            int childValue = scanner.nextInt();

                            tree.addChild(parentPosition, childValue);
                            System.out.println("Child node added successfully.");
                        } else {
                            System.out.println("Parent with value " + parentValue + " not found. Child not added.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please Enter Integer.");
                        scanner.next();
                        break;
                    }
                    break;
                }
                case 2: {
                    System.out.println("Tree structure:");
                    displayTree(tree, tree.root(), 0);
                    break;
                }
                case 3: {
                    Iterator<Edge<Integer>> edgeIterator = tree.listEdges();
                    System.out.println("Edges of the tree:");

                    while (edgeIterator.hasNext()) {
                        Edge<Integer> edge = edgeIterator.next();
                        System.out.println(edge.parent().getElement() + " -> " + edge.child().getElement());
                    }
                    break;
                }
                case 4: {
                    try {
                        System.out.print("Enter the integer value of the node to find the path: ");
                        int targetValue = scanner.nextInt();
                        Position<Integer> targetPosition = findNode(tree.root(), targetValue, tree);

                        if (targetPosition != null) {
                            String path = tree.findPath(targetPosition, " -> ");
                            System.out.println("Path to " + targetValue + ": " + path);
                        } else {
                            System.out.println("Node not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please Enter Integer.");
                        scanner.next();
                        break;
                    }
                    break;
                }
                case 5: {
                    try {
                        System.out.print("Enter the value of the node: ");
                        int value = scanner.nextInt();
                        Position<Integer> position = findNode(tree.root(), value, tree);

                        if (position != null) {
                            Iterator<Position<Integer>> siblings = tree.findSiblings(position);
                            while (siblings.hasNext()) {
                                System.out.print(siblings.next().getElement() + " ");
                            }
                            System.out.println();
                        } else {
                            System.out.println("Node not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please Enter Integer.");
                        scanner.next();
                        break;
                    }
                    break;
                }
                case 6: {
                    Iterator<Position<Integer>> leaves = tree.listLeaves();
                    while (leaves.hasNext()) {
                        System.out.print(leaves.next().getElement() + " ");
                    }
                    System.out.println();
                    break;
                }
                case 7: {
                    Iterator<Position<Integer>> internalNodes = tree.listInternalNodes();
                    while (internalNodes.hasNext()) {
                        System.out.print(internalNodes.next().getElement() + " ");
                    }
                    System.out.println();
                    break;
                }
                case 8: {
                    try {
                        System.out.print("Enter the value of the node: ");
                        int value = scanner.nextInt();
                        Position<Integer> position = findNode(tree.root(), value, tree);

                        if (position != null) {
                            System.out.println(tree.findDepth(position));
                        } else {
                            System.out.println("Node not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please Enter Integer.");
                        scanner.next();
                        break;
                    }
                    break;
                }
                case 9: {
                    System.out.println(tree.findHeight());
                    break;
                }
                case 10: {
                    try {
                        System.out.print("Enter the value of the node: ");
                        int value = scanner.nextInt();
                        Position<Integer> position = findNode(tree.root(), value, tree);

                        if (position != null) {
                            GenericTree<Integer> subtree = tree.findSubtree(position);
                            displayTree(subtree, position, 0);
                        } else {
                            System.out.println("Node not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please Enter Integer.");
                        scanner.next();
                        break;
                    }
                    break;
                }
                case 11: {
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
                }
            }

        } while (choice != 11);

        scanner.close();
    }

    // Helper method to display the tree structure
    private static void displayTree(GenericTree<Integer> tree, Position<Integer> position, int depth) {
        System.out.println("  ".repeat(depth) + position.getElement());
        for (Position<Integer> child : tree.children(position)) {
            displayTree(tree, child, depth + 1);
        }
    }

    // Helper method to find a node by its element
    private static Position<Integer> findNode(Position<Integer> position, int targetValue, GenericTree<Integer> tree) {
        if (position.getElement() == targetValue) {
            return position;
        } else {
            for (Position<Integer> child : tree.children(position)) {
                Position<Integer> foundNode = findNode(child, targetValue, tree);
                if (foundNode != null) {
                    return foundNode;
                }
            }
            return null;
        }
    }

    // Helper method to print the menu
    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add a child node");
        System.out.println("2. Display the tree structure");
        System.out.println("3. List and display the edges of the tree");
        System.out.println("4. Find and display the path to a node");
        System.out.println("5. Siblings of the node");
        System.out.println("6. List the leaves");
        System.out.println("7. List the internal nodes");
        System.out.println("8. Depth Of Node");
        System.out.println("9. Height of Tree");
        System.out.println("10. Subtree of a given Node");
        System.out.println("11. Exit");
    }
}
