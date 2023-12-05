package practicals.lab9;

/**
 * The {@code Map} interface represents a collection of key-value pairs, where each
 * key is associated with exactly one value. Duplicate keys are not allowed.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface Map<K, V> {

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if
     * this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if
     * this map contains no mapping for the key
     */
    V get(K key);

    /**
     * Associates the specified value with the specified key in this map. If the map
     * previously contained a mapping for the key, the old value is replaced by the
     * specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or {@code null} if there
     * was no mapping for the key
     */
    V put(K key, V value);

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with the key, or {@code null} if there
     * was no mapping for the key
     */
    V remove(K key);

    /**
     * Returns an iterable collection of the keys contained in this map.
     *
     * @return an iterable collection of the keys contained in this map
     */
    Iterable<K> keySet();

    /**
     * Returns an iterable collection of the values contained in this map.
     *
     * @return an iterable collection of the values contained in this map
     */
    Iterable<V> values();

    /**
     * Returns an iterable collection of the key-value mappings contained in this
     * map.
     *
     * @return an iterable collection of the key-value mappings contained in this
     * map
     */
    Iterable<Entry<K, V>> entrySet();
}
