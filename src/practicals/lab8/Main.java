package practicals.lab8;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SortedPriorityQueue<Integer, String> priorityQueue = new SortedPriorityQueue<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Insert");
            System.out.println("2. Get Minimum");
            System.out.println("3. Remove Minimum");
            System.out.println("4. Size");
            System.out.println("5. is Empty");
            System.out.println("6. Print Queue");
            System.out.println("7. Exit");

            int choice = 8;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.next();
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter key and value to insert:");
                    try {
                        int key = scanner.nextInt();
                        String value = scanner.next();

                        priorityQueue.insert(key, value);
                        System.out.println("Inserted: (" + key + ", " + value + ")");
                    } catch (InputMismatchException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    Entry<Integer, String> minEntry = priorityQueue.min();
                    if (minEntry != null) {
                        System.out.println("Minimum: (" + minEntry.getKey() + ", " + minEntry.getValue() + ")");
                    } else {
                        System.out.println("Priority Queue is empty.");
                    }
                }
                case 3 -> {
                    Entry<Integer, String> removedEntry = priorityQueue.removeMin();
                    if (removedEntry != null) {
                        System.out.println("Removed Minimum: (" + removedEntry.getKey() + ", " + removedEntry.getValue() + ")");
                    } else {
                        System.out.println("Priority Queue is empty.");
                    }
                }

                case 4 -> System.out.println("Size of Priority Queue: " + priorityQueue.size());
                case 5 -> {
                    if (priorityQueue.isEmpty()) {
                        System.out.println("Priority Queue is Empty.");
                    } else {
                        System.out.println("Priority Queue is not Empty.");
                    }
                }

                case 6 -> priorityQueue.printQueueContents();
                case 7 -> {
                    System.out.println("Exiting program.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
