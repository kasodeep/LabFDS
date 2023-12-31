package practicals.lab2;

/**
 * MyLongArray is a class designed to manage an array of long integers.
 * It provides methods for various array operations such as insertion, deletion,
 * searching, sorting, and more.
 *
 * <p>The class maintains an internal long array and keeps track of the current
 * number of elements in the array. It offers functionality to perform common
 * array operations efficiently.</p>
 *
 * <p>This class serves as a versatile tool for handling long integer arrays with
 * the following features:</p>
 *
 * <ul>
 *   <li>Insertion of elements at the last available index.</li>
 *   <li>Deletion of specified elements.</li>
 *   <li>Searching for elements by value.</li>
 *   <li>Retrieval of elements by index.</li>
 *   <li>Displaying the elements in the array.</li>
 *   <li>Deletion of duplicate occurrences of a specified element.</li>
 *   <li>Insertion of elements at a specified index.</li>
 *   <li>Deletion and retrieval of elements at a specified index.</li>
 * </ul>
 *
 * <p>Instances of this class can be created with a specified initial size, and
 * additional elements can be inserted or removed dynamically.</p>
 *
 * <p>MyLongArray provides a simple and versatile way to work with long integer
 * arrays, making it suitable for various applications where array management
 * is required.</p>
 *
 * @author - Kasodariya Deep
 * @version - 0.0.1
 * @see MyLongArray#arr
 * @see MyLongArray#currentIndex
 */
public class MyLongArray extends ExtraMethods {

    /**
     * The internal long array that stores the elements of this MyLongArray instance.
     */
    private final long[] arr;

    /**
     * The current number of elements in the array.
     * It keeps track of the last available index for insertion.
     */
    private int currentIndex;

    public MyLongArray(int size) {
        arr = new long[size];
        currentIndex = 0;
    }

    public long[] getArr() {
        return arr;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * Searches for the specified element in the array.
     *
     * @param searchKey The element to search for in the array.
     * @return The index of the element if found, or -1 if not found.
     */
    public int find(long searchKey) {
        for (int i = 0; i < currentIndex; i++) {
            if (arr[i] == searchKey) return i;
        }
        return -1;
    }

    /**
     * Inserts the specified value at the last available index in the array.
     *
     * @param value The value to be added to the array.
     */
    public void insert(long value) {
        if (currentIndex == arr.length) {
            System.out.println("Array Size is Full");
            return;
        }
        arr[currentIndex++] = value;
    }

    /**
     * Retrieves the element at the specified index in the array.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, or -1 if the index is out of bounds.
     */
    public long getElement(int index) {
        if (index < 0 || index >= arr.length) return -1;
        return arr[index];
    }

    /**
     * Deletes the first occurrence of the specified element from the array.
     *
     * @param value The value to be deleted from the array if found.
     * @return true if the element is deleted, false if it is not found.
     */
    public boolean delete(long value) {
        int i = find(value);
        if (i == -1) return false;

        for (; i < currentIndex - 1; i++) {
            arr[i] = arr[i + 1];
        }
        currentIndex--;
        return true;
    }

    /**
     * Function to display the array elements.
     */
    public void display() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Deletes duplicate occurrences of the specified element in the array.
     *
     * @param value The element whose duplicates are to be deleted.
     * @return The number of duplicate elements deleted.
     */
    public int dupDelete(long value) {
        int count = 0;
        for (int index = 0; index < currentIndex; index++) {
            if (arr[index] == value) {
                count++;
                if (count > 1) {
                    deleteAt(index);
                    index--;
                }
            }
        }
        return count - 1;
    }

    /**
     * Inserts the specified value at the specified index in the array.
     *
     * @param index The index at which the element is to be inserted.
     * @param value The value to be inserted at the index.
     */
    public void insert(int index, long value) {
        if (index < 0 || index > currentIndex) {
            System.out.println("The array is filled till index: " + (currentIndex - 1));
            return;
        }

        if (currentIndex == arr.length) {
            System.out.println("Array is already Full");
            return;
        }

        for (int i = index; i < currentIndex; i++) {
            arr[i + 1] = arr[i];
        }
        arr[index] = value;
        currentIndex++;
    }

    /**
     * Deletes and retrieves the element at the specified index in the array.
     *
     * @param index The index at which the element is to be deleted.
     * @return The deleted element.
     */
    public long deleteAt(int index) {
        if (index < 0 || index >= arr.length) {
            System.out.println("Enter Proper Index");
            return -1;
        }

        long temp = arr[index];
        for (; index < currentIndex - 1; index++) {
            arr[index] = arr[index + 1];
        }

        currentIndex--;
        return temp;
    }
}

