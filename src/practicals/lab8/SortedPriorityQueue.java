package practicals.lab8;

import practicals.lab4.LinkedPositionalList;
import practicals.lab4.Position;

import java.util.Comparator;
import java.util.Iterator;

/**
 * An implementation of the {@link PriorityQueue} interface that maintains
 * a sorted order of key-value pairs based on their priorities.
 *
 * @param <K> The type of keys in the priority queue.
 * @param <V> The type of values associated with the keys.
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private final LinkedPositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Constructs an empty priority queue using the natural ordering of keys.
     */
    public SortedPriorityQueue() {
        super();
    }

    /**
     * Constructs an empty priority queue using the given comparator for comparing keys.
     *
     * @param comp The comparator to determine the order of keys in the priority queue.
     */
    public SortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return The number of elements in the priority queue.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Inserts a new key-value pair with the specified priority into the priority queue.
     *
     * @param key   The key of the new entry.
     * @param value The value associated with the key.
     * @return An entry representing the newly inserted key-value pair.
     * @throws IllegalArgumentException If the provided key is invalid.
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newEntry = new PQEntry<>(key, value);
        Position<Entry<K, V>> walk = list.last();

        while (walk != null && compare(newEntry, walk.getElement()) <= 0)
            walk = list.before(walk);

        if (walk == null) list.addFirst(newEntry);
        else list.addAfter(walk, newEntry);
        return newEntry;
    }

    /**
     * Returns the entry with the minimum priority in the priority queue.
     *
     * @return The entry with the minimum priority, or {@code null} if the priority queue is empty.
     */
    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    /**
     * Removes and returns the entry with the minimum priority from the priority queue.
     *
     * @return The entry with the minimum priority, or {@code null} if the priority queue is empty.
     */
    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }

    /**
     * Prints the contents of the priority queue.
     * If the priority queue is empty, it prints a corresponding message.
     */
    public void printQueueContents() {
        if (list.isEmpty()) {
            System.out.println("Priority queue is empty.");
            return;
        }

        System.out.println("Priority Queue Contents:");
        Iterator<Entry<K, V>> iterator = list.iterator();

        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            System.out.println("(" + entry.getKey() + ", " + entry.getValue() + ")");
        }
    }
}
