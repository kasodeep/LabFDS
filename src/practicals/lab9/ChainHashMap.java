package practicals.lab9;

import java.util.*;

/**
 * ChainHashMap is an implementation of a hash map using separate chaining.
 * It extends the AbstractHashMap class and uses an array of UnsortedTableMap
 * instances as the underlying data structure for handling collisions.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    private UnsortedTableMap<K, V>[] table;

    /**
     * Constructs an empty ChainHashMap with the default capacity and prime number.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Constructs an empty ChainHashMap with the specified capacity and default prime number.
     *
     * @param cap the initial capacity
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Constructs an empty ChainHashMap with the specified capacity and prime number.
     *
     * @param cap the initial capacity
     * @param p   the prime number for hash calculations
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    /**
     * Creates the table, initializing the array of UnsortedTableMap instances.
     */
    @SuppressWarnings("unchecked")
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    /**
     * Retrieves the value associated with the specified key in the specified bucket.
     *
     * @param h the hash value for the key
     * @param k the key whose associated value is to be retrieved
     * @return the value associated with the specified key in the specified bucket
     */
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            return null;
        return bucket.get(k);
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
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            bucket = table[h] = new UnsortedTableMap<>();
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        n = n + (bucket.size() - oldSize);
        return answer;
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
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n = n - (oldSize - bucket.size());
        return answer;
    }

    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (table[h] != null)
                for (Entry<K, V> entry : table[h].entrySet())
                    buffer.add(entry);

        }
        return buffer;
    }

}

