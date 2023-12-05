package practicals.lab9;

/**
 * An interface representing a key-value pair, typically used in data structures
 * such as maps or priority queues.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public interface Entry<K, V> {

    /**
     * Returns the key associated with this entry.
     *
     * @return The key of the entry.
     */
    K getKey();

    /**
     * Returns the value associated with this entry.
     *
     * @return The value of the entry.
     */
    V getValue();
}
