package practicals.lab8;

/**
 * A generic interface representing a priority queue, which is a collection
 * of key-value pairs with associated priorities. The priority determines the
 * order in which elements are processed.
 *
 * @param <K> The type of keys in the priority queue.
 * @param <V> The type of values associated with the keys.
 */
public interface PriorityQueue<K, V> {

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return The number of elements in the priority queue.
     */
    int size();

    /**
     * Checks whether the priority queue is empty.
     *
     * @return {@code true} if the priority queue is empty, {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Inserts a new key-value pair with the specified priority into the priority queue.
     *
     * @param key   The key of the new entry.
     * @param value The value associated with the key.
     * @return An entry representing the newly inserted key-value pair.
     * @throws IllegalArgumentException If the provided key is invalid.
     */
    Entry<K, V> insert(K key, V value) throws IllegalArgumentException;

    /**
     * Returns the entry with the minimum priority in the priority queue.
     *
     * @return The entry with the minimum priority, or {@code null} if the priority queue is empty.
     */
    Entry<K, V> min();

    /**
     * Removes and returns the entry with the minimum priority from the priority queue.
     *
     * @return The entry with the minimum priority, or {@code null} if the priority queue is empty.
     */
    Entry<K, V> removeMin();
}
