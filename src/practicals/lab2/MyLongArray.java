package practicals.lab2;

public class MyLongArray {

    private final long[] arr;
    private int currentIndex;

    /**
     * @param size - The size of the array with which is to be initialized.
     */
    public MyLongArray(int size) {
        arr = new long[size];
        currentIndex = 0;
    }

    /**
     * @param searchKey - It is the element to be looked for in the array.
     * @return - Returns the index of the element, if the element is found else returns -1.
     */
    public int find(long searchKey) {
        for (int i = 0; i < currentIndex; i++) {
            if (arr[i] == searchKey) return i;
        }
        return -1;
    }

    /**
     * Function to insert the element at the last available index.
     *
     * @param value - It is the value to be added in the array.
     */
    public void insert(long value) {
        if (currentIndex == arr.length) {
            System.out.println("Array Size is Full");
            return;
        }
        arr[currentIndex++] = value;
    }

    /**
     * @param index - It is the index of the element to be returned.
     * @return - Returns the element at index and if index is wrong returns -1.
     */
    public long getElement(int index) {
        if (index < 0 || index >= arr.length) return -1;
        return arr[index];
    }

    /**
     * @param value - It is the value to be deleted in the array, if it is present.
     * @return - Returns true if the element is deleted, and false if it is not found.
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
     * Function to delete the duplicate elements present in the array.
     *
     * @param value - It is the element whose duplicates is to be deleted.
     * @return - Returns the number of duplicate elements deleted.
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
     * Function to insert the element at a specified index.
     *
     * @param index - The index at which the element is to be inserted.
     * @param value - The value to be inserted at the index.
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
     * Function to delete and retrieve the element at index.
     *
     * @param index - The index at which the element is to be deleted.
     * @return - The deleted element is returned.
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

