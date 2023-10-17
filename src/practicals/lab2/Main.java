package practicals.lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please Enter the size of the array:");
        int size = sc.nextInt();
        MyLongArray array = new MyLongArray(size);

        boolean ans = true;
        while (ans) {

            // Menu for doing the operation.
            System.out.println("Insert : 1, Delete : 2, Find: 3, GetElement : 4, Display : 5, DuplicateDelete : 6," +
                    " InsertAt : 7, DeleteAt : 8, Random Init : 9, Bubble Sort : 10, Selection Sort : 11, Insertion Sort : 12, Quit : 13");
            int num = sc.nextInt();

            switch (num) {
                case 1 -> {
                    System.out.println("Enter the value to be inserted:");
                    long value = sc.nextLong();
                    array.insert(value);
                }
                case 2 -> {
                    System.out.println("Enter the value to be Deleted:");
                    long value = sc.nextLong();
                    if (array.delete(value)) {
                        System.out.println("Element Deleted");
                    } else {
                        System.out.println("Element Not Found");
                    }
                }
                case 3 -> {
                    System.out.println("Enter the value to be found:");
                    long value = sc.nextLong();
                    System.out.println(array.find(value));
                }
                case 4 -> {
                    System.out.println("Enter the index:");
                    int index = sc.nextInt();
                    System.out.println(array.getElement(index));
                }
                case 5 -> array.display();

                case 6 -> {
                    System.out.println("Enter the duplicate value to be deleted:");
                    long value = sc.nextLong();
                    System.out.println("The number of Duplicate elements deleted: " + array.dupDelete(value));
                }
                case 7 -> {
                    System.out.println("Enter the value to be inserted:");
                    long value = sc.nextLong();
                    System.out.println("Enter the index:");
                    int index = sc.nextInt();
                    array.insert(index, value);
                }
                case 8 -> {
                    System.out.println("Enter the index:");
                    int index = sc.nextInt();
                    System.out.println(array.deleteAt(index));
                }
                case 9 -> {
                    long[] initArray = array.initArray(array.getCurrentIndex());
                    System.out.println("Successfully Initialized");
                    printArray(initArray);
                }
                case 10 -> {
                    long[] sorted = array.bubbleSort(array.getArr(), array.getCurrentIndex());
                    System.out.println("Successfully sorted using Bubble Sort");
                    printArray(sorted);
                }
                case 11 -> {
                    long[] sorted = array.selectionSort(array.getArr(), array.getCurrentIndex());
                    System.out.println("Successfully sorted using Selection Sort");
                    printArray(sorted);
                }
                case 12 -> {
                    long[] sorted = array.insertionSort(array.getArr(), array.getCurrentIndex());
                    System.out.println("Successfully sorted using Insertion Sort");
                    printArray(sorted);
                }
                case 13 -> ans = false;
                default -> System.out.println("Please enter valid option.");
            }
        }
    }

    public static void printArray(long[] arr) {
        for (long l : arr) {
            System.out.print(l + " ");
        }
        System.out.println();
    }
}
