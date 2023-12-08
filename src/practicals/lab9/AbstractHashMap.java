package practicals.lab9;

import java.util.*;

/**
 * AbstractHashMap is an abstract class that provides a basic framework for hash map
 * implementations. It extends AbstractMap and includes methods for common hash map
 * operations such as put, get, and remove. Subclasses must implement specific methods
 * for creating the hash table, retrieving, putting, and removing elements in a bucket.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    /**
     * The prime number used in the hash function.
     */
    private final int prime;

    /**
     * The scale factor used in the hash function.
     */
    private final long scale;

    /**
     * The shift factor used in the hash function.
     */
    private final long shift;

    /**
     * The current number of entries in the hash map.
     */
    protected int n = 0;

    /**
     * The current capacity of the hash map.
     */
    protected int capacity;

    /**
     * Constructs an AbstractHashMap with the specified initial capacity and prime number.
     *
     * @param cap the initial capacity
     * @param p   the prime number for hash calculations
     */
    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = p;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    /**
     * Constructs an AbstractHashMap with the specified initial capacity and default prime number.
     *
     * @param cap the initial capacity
     */
    public AbstractHashMap(int cap) {
        this(cap, 109345121);
    }

    /**
     * Constructs an AbstractHashMap with default initial capacity and prime number.
     */
    public AbstractHashMap() {
        this(17);
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return n;
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this map
     * contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this map
     * contains no mapping for the key
     */
    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with the specified key, or {@code null} if there
     * was no mapping for the key
     */
    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the specified key, or {@code null} if there
     * was no mapping for the key
     */
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) {
            resize(2 * capacity - 1);
        }
        return answer;
    }

    /**
     * Returns the hash value for the given key using the hash function.
     *
     * @param key the key for which to calculate the hash value
     * @return the hash value for the given key
     */
    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /**
     * Resizes the hash map to the specified new capacity and rehashes the entries.
     *
     * @param newCap the new capacity for the hash map
     */
    private void resize(int newCap) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
        for (Entry<K, V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable();

        n = 0;
        for (Entry<K, V> e : buffer)
            put(e.getKey(), e.getValue());
    }

    protected abstract void createTable();

    protected abstract V bucketGet(int h, K k);

    protected abstract V bucketPut(int h, K k, V v);

    protected abstract V bucketRemove(int h, K k);
}