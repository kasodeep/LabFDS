package practicals.lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * The {@code AppOne} class is an application that reads a file containing a hierarchical tree structure
 * and uses it to create and print a generic tree. The file is expected to follow a specific format,
 * where each line represents a node in the tree and its children.
 */
public class AppOne {

    /**
     * Reads a file, extracts the tree structure, and generates a generic tree.
     */
    public static void main(String[] args) {
        // Contents.
        StringBuilder text = new StringBuilder();

        try {
            // Getting the Folder Path.
            Path currRelativePath = Paths.get("");
            String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();

            // Opening a File.
            File file_create = new File(currAbsolutePathString + "/src/practicals/lab5/one.txt");

            // Reading a File.
            BufferedReader br = new BufferedReader(new FileReader(file_create));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Getting the root.
        int i = 0;
        while (text.charAt(i) != '\n') i++;
        GenericTree<String> tree = new GenericTree<>(text.substring(0, i));

        // Getting the lines.
        String remaining = text.substring(i + 1);
        String[] lines = remaining.split("\n");

        // Queue for Help.
        Queue<Position<String>> queue = new LinkedList<>();
        queue.add(tree.root());
        int j = 0;

        // Going over each line.
        while (j < lines.length) {
            String[] allChildren = lines[j].split(" ");
            j++;

            int count = 0;
            int n = queue.size();

            while (count < allChildren.length) {
                Position<String> curr = queue.poll();
                if (!allChildren[count].equals(" ")) {
                    String[] children = allChildren[count].split(",");
                    for (String s : children) {
                        Position<String> child = tree.addChild(curr, s);
                        queue.add(child);
                    }
                }
                count++;
            }
            while (count < n) {
                queue.poll();
                count++;
            }
        }
        printTreeWithDepth(tree, tree.root(), 0, "");
    }

    private static void printTreeWithDepth(GenericTree<String> tree, Position<String> node, int depth, String parentIndex) {
        if (node == null) return;

        StringBuilder depthPrefix = new StringBuilder();
        depthPrefix.append(" ".repeat(depth));

        String currentIndex = parentIndex.isEmpty() ? "" : parentIndex + ".";
        if (!Objects.equals(node.getElement(), ""))
            System.out.println(depthPrefix + currentIndex + node.getElement());
        else
            return;

        Iterator<Position<String>> children = tree.children(node).iterator();
        int i = 0;
        while (children.hasNext()) {
            String childIndex = currentIndex + (i + 1);
            printTreeWithDepth(tree, children.next(), depth + 1, childIndex);
            i++;
        }
    }
}

