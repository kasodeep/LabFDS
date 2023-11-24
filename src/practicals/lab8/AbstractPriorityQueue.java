package practicals.lab8;

import java.util.Comparator;

/**
 * An abstract class implementing the {@link PriorityQueue} interface
 * with common functionality for comparing keys and checking their validity.
 *
 * @param <K> The type of keys in the priority queue.
 * @param <V> The type of values associated with the keys.
 */
public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

    private final Comparator<K> comp;

    /**
     * Constructs a priority queue with the given comparator for comparing keys.
     *
     * @param comp The comparator to determine the order of keys in the priority queue.
     */
    protected AbstractPriorityQueue(Comparator<K> comp) {
        this.comp = comp;
    }

    /**
     * Constructs a priority queue with a default comparator for natural ordering of keys.
     */
    protected AbstractPriorityQueue() {
        this(new DefaultComparator<>());
    }

    /**
     * Compares two entries based on their keys using the comparator.
     *
     * @param a The first entry to be compared.
     * @param b The second entry to be compared.
     * @return A negative integer, zero, or a positive integer as the first key
     *         is less than, equal to, or greater than the second key.
     */
    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    /**
     * Checks the validity of a key based on its ability to be compared with itself.
     *
     * @param key The key to be checked.
     * @return {@code true} if the key is valid, {@code false} otherwise.
     * @throws IllegalArgumentException If the key is found to be incompatible.
     */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return comp.compare(key, key) == 0;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible Key!");
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }
}
