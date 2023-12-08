package practicals.lab9;

import java.util.*;

/**
 * LinearProbeHashMap is an implementation of a hash map using linear probing for handling
 * collisions. It extends the AbstractHashMap class and uses an array of MapEntry instances
 * as the underlying data structure with a special DEFUNCT marker to represent deleted entries.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class LinearProbeHashMap<K, V> extends AbstractHashMap<K, V> {

    /**
     * A special marker to represent a deleted entry.
     */
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    /**
     * The array of MapEntry instances used for storing key-value mappings.
     */
    private MapEntry<K, V>[] table;

    /**
     * Constructs an empty LinearProbeHashMap with the default capacity and prime number.
     */
    public LinearProbeHashMap() {
        super();
    }

    /**
     * Constructs an empty LinearProbeHashMap with the specified capacity and default prime number.
     *
     * @param cap the initial capacity
     */
    public LinearProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Constructs an empty LinearProbeHashMap with the specified capacity and prime number.
     *
     * @param cap the initial capacity
     * @param p   the prime number for hash calculations
     */
    public LinearProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    /**
     * Creates the table, initializing the array of MapEntry instances.
     */
    @SuppressWarnings("unchecked")
    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity];
    }

    /**
     * Checks if the entry at the specified index is available (not occupied by a key-value pair).
     *
     * @param j the index to check
     * @return true if the entry is available, false otherwise
     */
    protected boolean isAvailable(int j) {
        return (table[j] == DEFUNCT || table[j] == null);
    }

    /**
     * Finds the next available slot for the given hash value and key.
     *
     * @param h the hash value for the key
     * @param k the key to find a slot for
     * @return the index of the next available slot
     */
    private int findSlot(int h, K k) {
        int avail = -1;
        int j = h;
        do {
            if (isAvailable(j)) {
                if (avail == -1)
                    avail = j;
                if (table[j] == null)
                    break;
                else if (table[j].getKey().equals(k))
                    return j;
            }
            j = (j + 1) % capacity;
        } while (j != h);

        return (avail + 1);

    }

    /**
     * Retrieves the value associated with the specified key in the specified bucket.
     *
     * @param h the hash value for the key
     * @param k the key whose associated value is to be retrieved
     * @return the value associated with the specified key in the specified bucket
     */
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0)
            return null;
        System.out.println("Here");

        return table[j].getValue();
    }

    /**
     * Associates the specified value with the specified key in the specified bucket.
     *
     * @param h the hash value for the key
     * @param k the key with which the specified value is to be associated
     * @param v the value to be associated with the specified key
     * @return the previous value associated with the specified key, or {@code null} if there
     * was no mapping for the key
     */
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);

        if (j >= 0) {
            table[j] = new MapEntry<>(k, v);
            n++;
            return table[j].setValue(v);
        }
        return null;
    }

    /**
     * Removes the mapping for the specified key from the specified bucket if present.
     *
     * @param h the hash value for the key
     * @param k the key whose mapping is to be removed from the bucket
     * @return the previous value associated with the specified key, or {@code null} if there
     * was no mapping for the key
     */
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0)
            return null;
        V answer = table[j].getValue();
        table[j] = DEFUNCT;
        n--;
        return answer;
    }

    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (!isAvailable(h))
                buffer.add(table[h]);
        }
        return buffer;
    }
}
