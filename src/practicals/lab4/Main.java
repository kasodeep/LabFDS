package practicals.lab4;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        int choice = 0;

        while(choice != 10) {
            System.out.println("1. First element " + " 2. Last element " + "3. Add first "
                    + " 4. Add last " + " 5. Add before " + " 6. Add after "
                    + " 7. Remove " + " 8. Set " + " 9. Display " + " 10. Exit ");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    if (list.isEmpty()) System.out.println("Positional list is empty");
                    else System.out.println("First element is: " + list.first().getElement());
                    break;
                }
                case 2: {
                    if (list.isEmpty()) System.out.println("Positional list is empty");
                    else System.out.println("Last element is: " + list.last().getElement());
                    break;
                }
                case 3: {
                    System.out.println("Enter the element: ");
                    String temp = sc.next();
                    list.addFirst(temp);
                    break;
                }
                case 4: {
                    System.out.println("Enter the element: ");
                    String temp = sc.next();
                    list.addLast(temp);
                    break;
                }
                case 5: {
                    System.out.println("Enter the element: ");
                    String element = sc.next();
                    System.out.println("Enter the position: ");
                    int position = sc.nextInt();

                    if (position < 0 || position >= list.size()) {
                        System.out.println("Invalid position");
                    } else {
                        Position<String> p = list.first();
                        for (int i = 0; i < position; i++) {
                            p = list.after(p);
                        }
                        list.addBefore(p, element);
                    }
                    break;
                }
                case 6: {
                    System.out.println("Enter the element: ");
                    String element = sc.next();
                    System.out.println("Enter the position: ");
                    int position = sc.nextInt();

                    if (position < 0 || position >= list.size()) {
                        System.out.println("Invalid position");
                    } else {
                        Position<String> p = list.first();
                        for (int i = 0; i < position; i++) p = list.after(p);
                        list.addAfter(p, element);
                    }
                    break;
                }
                case 7: {
                    System.out.println("Enter the position: ");
                    int pos = sc.nextInt();

                    if (pos < 0 || pos >= list.size()) {
                        System.out.println("Invalid position");
                        break;
                    }

                    Position<String> p = list.first();
                    for (int i = 0; i < pos; i++) {
                        p = list.after(p);
                    }
                    list.remove(p);
                    break;
                }
                case 8: {
                    System.out.println("Enter the position: ");
                    int pos = sc.nextInt();

                    if (pos < 0 || pos >= list.size()) {
                        System.out.println("Invalid position");
                        break;
                    }

                    Position<String> p = list.first();
                    for (int i = 0; i < pos; i++) {
                        p = list.after(p);
                    }

                    System.out.println("Enter the element: ");
                    String element = sc.next();
                    list.set(p, element);
                    break;
                }
                case 9:
                    show(list);
                    break;
                default:
                    System.out.println("Exiting the program ...");
                    System.out.println("Thank you for using the program");
                    break;
            }
        }

        Iterable<Position<String>> positionIter = list.positions();
        for (Position<String> p : positionIter) {
            System.out.print(p.getElement() + " ");
        }
        System.out.println();
    }

    private static <T> void show(LinkedPositionalList<T> posList) {
        if (posList.isEmpty()) {
            System.out.println("Positional list is empty");
        } else {
            Iterator<T> i = posList.iterator();
            while (i.hasNext()) System.out.print(" " + i.next());
            System.out.println("\nSize of the positional list is: " + posList.size());
        }
    }
}
