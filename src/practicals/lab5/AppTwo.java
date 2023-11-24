package practicals.lab5;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * The {@code AppTwo} class is an application that generates and prints a hierarchical tree structure
 * representing the file system structure of a given directory. It utilizes a generic tree data structure
 * to store information about each file and directory in the specified path.
 */
public class AppTwo {

    // Simple Date Formatter.
    private static final DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");

    /**
     * Generates a generic tree structure representing the file system structure of the given directory.
     *
     * @param file the root directory or file.
     * @return a generic tree representing the file system structure.
     */
    public static GenericTree<Node> generate(File file) {
        Node node = new Node(file.getName(), file.isDirectory(), getFileSizeKiloBytes(file), dateFormat.format(file.lastModified()));
        GenericTree<Node> tree = new GenericTree<>(node);

        generateTree(file, tree.root(), tree);
        return tree;
    }

    /**
     * Recursive function to generate the tree structure of the file system.
     *
     * @param file the current file or directory.
     * @param root the root position of the tree.
     * @param tree the generic tree being constructed.
     */
    public static void generateTree(File file, Position<Node> root, GenericTree<Node> tree) {
        try {
            File[] files = file.listFiles();
            for (File value : files) {
                Node node = new Node(value.getName(), value.isDirectory(), getFileSizeKiloBytes(value), dateFormat.format(value.lastModified()));
                Position<Node> position = tree.addChild(root, node);
                if (value.isDirectory()) {
                    generateTree(value, position, tree);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to get the size of a file in kilobytes.
     *
     * @param file the file for which to determine the size.
     * @return the size of the file in kilobytes.
     */
    private static double getFileSizeKiloBytes(File file) {
        return (double) file.length() / 1024;
    }

    // Main Function.
    public static void main(String[] args) {
        // Getting Source Path.
        try {
            Path currRelativePath = Paths.get("");
            String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();
            File file = new File(currAbsolutePathString + "/src");

            GenericTree<Node> tree = generate(file);
            printTreeWithDepth(tree, tree.root(), 0, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printTreeWithDepth(GenericTree<Node> tree, Position<Node> node, int depth, String parentIndex) {
        if (node == null) return;

        String depthPrefix = " ".repeat(depth);
        String currentIndex = parentIndex.isEmpty() ? "" : parentIndex + ".";
        System.out.println(depthPrefix + currentIndex + node.getElement().toString());

        Iterator<Position<Node>> children = tree.children(node).iterator();
        int i = 0;
        while (children.hasNext()) {
            String childIndex = currentIndex + (i + 1);
            printTreeWithDepth(tree, children.next(), depth + 1, childIndex);
            i++;
        }
    }

    /**
     * The {@code Node} class represents a node that stores data of a file or directory.
     * It includes information such as name, type (file or directory), size, and date.
     */
    static class Node {
        private String name;
        private boolean isDirectory;
        private double size;
        private String date;

        public Node(String name, boolean isDirectory, double size, String date) {
            this.name = name;
            this.isDirectory = isDirectory;
            this.size = size;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isDirectory() {
            return isDirectory;
        }

        public void setDirectory(boolean isDirectory) {
            this.isDirectory = isDirectory;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return date + "\t" + (isDirectory ? "<DIR>   " : "\t") + size + "\t" + name;
        }
    }
}

