package practicals.lab2;

import java.util.Random;

/**
 * <p>The `ExtraMethods` class provides a set of utility methods for working with arrays, specifically for sorting operations.
 * It includes implementations of bubble sort, selection sort, and insertion sort, as well as methods for initializing arrays
 * with random values and making a copy of an array. </p>
 * <p>The list of methods is as follows: </p>
 * <ul>
 *  <li>Creating a random array.</li>
 *  <li>Sorting Array using Bubble Sort.</li>
 *  <li>Sorting Array using Insertion Sort.</li>
 *  <li>Sorting Array using Selection Sort.</li>
 *  </ul>
 */
public class ExtraMethods {

    /**
     * Initializes an array of a specified size with random long integers ranging from 0 to 99.
     *
     * @param size The size of the array to be initialized.
     * @return An array of long integers filled with random values.
     */
    public long[] initArray(int size) {
        long[] arr = new long[size];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    /**
     * Sorts an array of long integers using the Bubble Sort technique.
     *
     * @param temp The original array to be sorted.
     * @param size The size of the array.
     * @return A new array containing the sorted elements.
     */
    public long[] bubbleSort(long[] temp, int size) {
        long[] arr = dupArray(temp, size);

        for (int i = 0; i < size; i++) {
            boolean flag = false;

            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    swap(arr, j, j + 1);
                }
            }
            if (!flag) break;
        }
        return arr;
    }

    /**
     * Sorts an array of long integers using the Selection Sort technique.
     *
     * @param temp The original array to be sorted.
     * @param size The size of the array.
     * @return A new array containing the sorted elements.
     */
    public long[] selectionSort(long[] temp, int size) {
        long[] arr = dupArray(temp, size);

        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
        return arr;
    }

    /**
     * Sorts an array of long integers using the Insertion Sort technique.
     *
     * @param temp The original array to be sorted.
     * @param size The size of the array.
     * @return A new array containing the sorted elements.
     */
    public long[] insertionSort(long[] temp, int size) {
        long[] arr = dupArray(temp, size);

        for (int i = 1; i < size; i++) {
            long min = arr[i];
            int j = i;
            while (j > 0 && min <= arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = min;
        }
        return arr;
    }

    /**
     * Private helper method for swapping elements within an array.
     *
     * @param arr The array in which elements are to be swapped.
     * @param i   The index of the first element to be swapped.
     * @param j   The index of the second element to be swapped.
     */
    private void swap(long[] arr, int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Private method to create a copy of an array.
     *
     * @param arr  The original array to be duplicated.
     * @param size The size of the array.
     * @return A new array that is a copy of the original array.
     */
    private long[] dupArray(long[] arr, int size) {
        long[] temp = new long[size];
        System.arraycopy(arr, 0, temp, 0, size);
        return temp;
    }
}
