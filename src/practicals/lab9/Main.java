package practicals.lab9;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractHashMap<String, String> map;
        int choice;

        System.out.println("Enter C for Chain And L for Linear");
        String ch = scanner.next();

        if (ch.charAt(0) == 'C') {
            map = new ChainHashMap<>();
        } else {
            map = new LinearProbeHashMap<>();
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Put key/value");
            System.out.println("2. Get value");
            System.out.println("3. Remove key");
            System.out.println("4. Display map");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Enter key: ");
                    String key = scanner.next();
                    System.out.print("Enter value: ");
                    String value = scanner.next();

                    map.put(key, value);
                    break;
                }
                case 2: {
                    System.out.print("Enter key to get value: ");
                    String key = scanner.next();

                    System.out.println("Value: " + map.get(key));
                    break;
                }
                case 3: {
                    System.out.print("Enter key to remove: ");
                    String key = scanner.next();
                    map.remove(key);

                    System.out.println("Key removed.");
                    break;
                }
                case 4: {
                    for (Entry<String, String> entry : map.entrySet()) {
                        System.out.println(entry.getKey() + " => " + entry.getValue());
                    }
                    break;
                }
                case 5: {
                    System.out.println("Exiting...");
                    return;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
